package ui.pages.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class ViewPanel extends BasePage {

    private final SelenideElement performanceBut = $(By.id("PointsOfView"));
    private final SelenideElement planeTopBut = $(By.id("PointOfView_Top"));
    private final SelenideElement planeBottomBut = $(By.id("PointOfView_Bot"));
    private final SelenideElement toNorthEastBut = $(By.id("PointOfView_NE"));
    private final SelenideElement toNorth = $(By.id("PointOfView_North"));

    private final SelenideElement visualSettingBut = $(By.xpath("//button[@id='VisualSetting']/../span")); // стрелка раскрыть меню кнопки Режим просмотра
    private final SelenideElement settingSliceBut = $(By.id("VisualSetting_slice"));

    public ViewPanel(WebDriver driver) {
        super(driver);
    }

    @Step("Выбор представления на сцене: {namePlane}")
    public ViewPanel settingCameraInPlaneTopAndBottom(String namePlane) {
        performanceBut.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        switch (namePlane) {
            case "Вид сверху":
                planeTopBut.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
                break;
            case "Вид снизу":
                planeBottomBut.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
                break;
            case "На Северо-Восток":
                toNorthEastBut.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
                break;
            case "На Север":
                toNorth.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
                break;
        }
        return this;
    }

    @Step("Установка вида - срез 2D")
    public ViewPanel set2D_Slice() {
        visualSettingBut.shouldBe(clickable, Duration.ofSeconds(2)).click();
        waitLoading(1);
        settingSliceBut.click();
        return this;
    }
}
