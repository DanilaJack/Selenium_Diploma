package ui.pages.fragments.panelmenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class PanelMenu extends BasePage {

    private final SpatialDataImport spatialDataImport;


    private final SelenideElement fileBut = $(By.id("docMenu")); // кнопка Файл (после нажатия на бургер)
    private final SelenideElement moveToCanvasBtn = $("#enter-to-canvas");
    private final SelenideElement moveToSceneBtn = $x("//button[@id='editing']/preceding-sibling::button[1]");

    private final SelenideElement exportFromCanvas = $x("//div[contains(@class, 'NavMenuContentItem_navContent') and text()='Экспорт объектов холста в *.dxf']");

    private final SelenideElement saveSettingsBtn = $(By.id("save"));


    public PanelMenu(WebDriver driver) {
        super(driver);
        spatialDataImport = new SpatialDataImport(driver);
    }

    @Step("Нажатие на кнопку Файл")
    public PanelMenu pressFileBut() {
        fileBut.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .shouldBe(Condition.clickable, Duration.ofSeconds(10))
                .click();
        waitLoading(1);
        return this;
    }

    @Step("Нажатие на кнопку 'Перейти в Холст со сцены 3d'")
    public PanelMenu pressMoveToCanvas(){
        moveToCanvasBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на кнопку 'Перейти на сцену 3d с Холста'")
    public PanelMenu pressMoveToScene(){
        moveToSceneBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на экспорт объектов с холста")
    public PanelMenu pressExportFromCanvas(){
        exportFromCanvas.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку 'Сохранить настройки сессии'")
    public PanelMenu pressSaveSettings(){
        saveSettingsBtn.shouldBe(visible).click();
        return this;
    }
}
