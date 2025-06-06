package ui.pages.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ContextPanel extends BasePage {

    private final SelenideElement polyhedralOperationsBtn = $(By.id("polyhedral_surface_operation_button"));
    private final SelenideElement volumeBetweenSurfacesBtn = $(By.id("volume_surfaces_button"));

    private final SelenideElement moveToCutViewBut = $(By.id("section_enter_view"));

    private final SelenideElement enterToEditModeMO = $(By.id("panel_enterEditMode"));



    public ContextPanel(WebDriver driver) {
        super(driver);
    }

    @Step("Расчет объема между поверхностями")
    public ContextPanel pressVolumeBetweenSurfaces() {
        polyhedralOperationsBtn.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        waitLoading(2);
        volumeBetweenSurfacesBtn.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Перейти в разрез")
    public ContextPanel moveToCutView() {
        moveToCutViewBut.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Войти в режим редактирование мультиобъекта")
    public ContextPanel enterEditModeOfMO() {
//        waitLoading(1);
//        enterToEditModeMO.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        sleep(300);
        return this;
    }
}
