package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;
import ui.helperUI.NavigationHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

     // от Lombok
    public SessionPage session;
    public ScenePage scene;
    public NavigationHelper navigation;


    SelenideElement objectsInStorageBtn = $(By.id("objectsInTheStorage"));

    public MainPage(WebDriver driver) {
        super(driver);
        session = new SessionPage(driver);
        scene = new ScenePage(driver);
        navigation = new NavigationHelper();
    }

    public MainPage expandObjectsInStorage(){
        objectsInStorageBtn.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
        return this;
    }


}
