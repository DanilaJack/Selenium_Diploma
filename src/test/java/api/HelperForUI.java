package api;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import ui.driver.config.Config;
import utility.ProjectSettings;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static ui.driver.config.Config.stand;

public class HelperForUI {

    public static String getAccessToken(String username, String password, String stand) {
        RestAssured.reset();

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        return given()
                .spec(
                        new RequestSpecBuilder()
                                .setContentType(ContentType.JSON)
                                .setRelaxedHTTPSValidation()
                                .build()
                )
                .body(body)
                .when()
                .post("https://authorise."+stand+".ggis.iccdev.ru/ggis/api/auth")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(5000L)) //--> time in milliseconds / time response from server
                .extract().body().jsonPath().getString("access_token");
    }

    public static void deleteAllSessions(){
        given()
                .log().all()
                .when()
                .delete("https://objects."+stand +".ggis.iccdev.ru/ggis/api/sessions")
                .then().statusCode(200);
    }
}
