package ui.pages.fragments.rightmenu;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;
import ui.pages.fragments.rightmenu.rightmenutabs.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Getter
public class RightMenu extends BasePage {

    private final SceneObjects sceneObjects;
    private final Properties properties;
    private final BindBusinessObject bindBusinessObject;
    private final StorageObjects storageObjects;
    private final Styles styles;


    private final SelenideElement rightOptionMenuList = $("[data-testid='rightOptionMenu-list']");
    private final SelenideElement objectsOnStorageBut = $(By.id("objectsInTheStorage"));
    private final SelenideElement objectsOnStageBut = $(By.id("objectsOnStage"));
    private final SelenideElement propertiesBut = $(By.id("option"));
    private final SelenideElement parameterBut = $(By.id("settings"));
    private final SelenideElement stylesBut = $(By.id("stylesObject"));
    private final SelenideElement verificationBut = $(By.id("verification"));
    private final SelenideElement panelOfWindowsBut = $(By.id("CircleOutlined"));
    private final SelenideElement hotkeysBut = $(By.id("hotkeys"));
    private final SelenideElement viewsBut = $(By.id("sliceTree"));

    public RightMenu(WebDriver driver) {
        super(driver);
        sceneObjects = new SceneObjects(driver);
        properties = new Properties(driver);
        bindBusinessObject = new BindBusinessObject(driver);
        storageObjects = new StorageObjects(driver);
        styles = new Styles(driver);
    }

    @Step("Открыть панель объектов в хранилище")
    public RightMenu openObjectsInStorage() {
//        if (!objectsOnStorageBut.getAttribute("class").contains("active")){
//            objectsOnStorageBut.shouldBe(visible, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        objectsOnStorageBut.shouldHave(attributeMatching("class", ".*active.*"));
        sleep(300);
        return this;
    }

    @Step("Закрыть панель объектов в хранилище")
    public RightMenu closeObjectsInStorage() {
//        if (objectsOnStorageBut.getAttribute("class").contains("active")){
//            objectsOnStorageBut.shouldBe(visible, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        objectsOnStorageBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        sleep(200);
        return this;
    }

    @Step("Открыть панель объектов сессии")
    public RightMenu openObjectsInSession() throws InterruptedException {
//        if (!objectsOnStageBut.getAttribute("class").contains("active")){
//            objectsOnStageBut.shouldBe(clickable, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        objectsOnStageBut.shouldHave(attributeMatching("class", ".*active.*"));
        Thread.sleep(200);
        return this;
    }

    @Step("Закрыть панель объектов сессии")
    public RightMenu closeObjectsInSession() {
//        if (objectsOnStageBut.getAttribute("class").contains("active")){
//            objectsOnStageBut.shouldBe(clickable, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        objectsOnStageBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        sleep(200);
        return this;
    }

    @Step("Открыть панель свойств")
    public RightMenu openProperties() throws InterruptedException {
//        if (!propertiesBut.getAttribute("class").contains("active")){
//            propertiesBut.shouldBe(visible, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        propertiesBut.shouldHave(attributeMatching("class", ".*active.*"));
        Thread.sleep(100);
        return this;
    }

    @Step("Закрыть панель свойств")
    public RightMenu closeProperties() {
//        if (propertiesBut.getAttribute("class").contains("active")){
//            propertiesBut.shouldBe(visible, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
//        propertiesBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        sleep(200);
        return this;
    }

    @Step("Открыть панель Параметров")
    public RightMenu openParameters() {
        if (!parameterBut.getAttribute("class").contains("active")){
            parameterBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        parameterBut.shouldHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Закрыть панель Параметров")
    public RightMenu closeParameters() {
        if (parameterBut.getAttribute("class").contains("active")){
            parameterBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        parameterBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Открыть панель Стилей")
    public RightMenu openStyles() {
        if (!stylesBut.getAttribute("class").contains("active")){
            stylesBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        stylesBut.shouldHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Закрыть панель Стилей")
    public RightMenu closeStyles() {
        if (stylesBut.getAttribute("class").contains("active")){
            stylesBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        stylesBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Открыть панель Верификации")
    public RightMenu openVerification() {
        if (!verificationBut.getAttribute("class").contains("active")){
            verificationBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        verificationBut.shouldHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Закрыть панель Верификации")
    public RightMenu closeVerification() {
        if (verificationBut.getAttribute("class").contains("active")){
            verificationBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        verificationBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Открыть панель Горячих клавиш")
    public RightMenu openHotkeys() {
        if (!hotkeysBut.getAttribute("class").contains("active")){
            hotkeysBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        hotkeysBut.shouldHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Закрыть панель Горячих клавиш")
    public RightMenu closeHotkeys() {
        if (hotkeysBut.getAttribute("class").contains("active")){
            hotkeysBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        hotkeysBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Открыть панель объектов в хранилище")
    public RightMenu openViews() {
        if (!viewsBut.getAttribute("class").contains("active")){
            viewsBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        viewsBut.shouldHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Закрыть панель объектов в хранилище")
    public RightMenu closeViews() {
        if (viewsBut.getAttribute("class").contains("active")){
            viewsBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        waitLoading(1);
        viewsBut.shouldNotHave(attributeMatching("class", ".*active.*"));
        return this;
    }

    @Step("Открытие/Закрытие правой панели окон")
    public RightMenu ClickPropertiesMenu() {
        //panelOfWindowsBut.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep(200);
        return this;
    }

}
