package ui.core;

import api.HelperForUI;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import ui.driver.config.Config;
import ui.driver.factory.DriverManager;
import ui.pages.MainPage;
import utility.ProjectSettings;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class TestBase {

    String token;
    protected MainPage page;
    String currentTime;

    @BeforeMethod(alwaysRun = true)
//    @Parameters({"stand", "baseUrl", "browser", "headless", "timeout", "maximized"})
//    public void setUp(String stand, String baseUrl, String browser, Boolean headless, int timeout, boolean maximized) {

    public void setUp() {

        Configuration.browser = "chrome";
        Configuration.headless = false; // true — если хочешь без GUI

        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        Configuration.remote = remoteUrl; // например, http://localhost:4444/wd/hub
        Configuration.browser = "chrome";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

        // если нужен headless:
        // options.addArguments("--headless");

//        Configuration.browserCapabilities = options;
//
//        step("Конфигурация Selenium", () -> {
//            Config.baseUrl = String.format(baseUrl, stand);
//            Config.browser = browser;
//            Config.headless = headless;
//            Config.maximized = maximized;
//            Config.implicitWaitTimeout = timeout;
//            Config.stand = stand;
//        });
//        Configuration.fastSetValue = true;
//        Configuration.pageLoadStrategy = String.valueOf(PageLoadStrategy.EAGER);
//
//        String token = HelperForUI.getAccessToken(ProjectSettings.USERNAME, ProjectSettings.PASSWORD, stand);
//
//        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .addHeader("Authorization", "Bearer " + token)
//                .setRelaxedHTTPSValidation()
//                .build();
//        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(GSON));
//
//        HelperForUI.deleteAllSessions();

        currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Allure.addAttachment("Время запуска теста", currentTime);
        //WebDriverRunner.setWebDriver(DriverManager.getDriver(Config.browser));
        //Selenide.open(Config.baseUrl);
        Selenide.open("https://www.google.com");

//        $("#username").shouldBe(Condition.interactable, Duration.ofSeconds(20));
//        $("#username").sendKeys(ProjectSettings.USERNAME);
//        $("#password").sendKeys(ProjectSettings.PASSWORD);
//        $("#kc-login").click();

        page = new MainPage(WebDriverRunner.getWebDriver());

//        $("[data-qa='loaderSession']").shouldBe(Condition.visible, Duration.ofSeconds(20)).shouldBe(Condition.disappear, Duration.ofSeconds(10));
//        $("#PanelMenu_Burger").shouldBe(Condition.clickable, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        takeScreenshot();
        step("Потушить драйвер", WebDriverRunner.getWebDriver()::quit);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

}
