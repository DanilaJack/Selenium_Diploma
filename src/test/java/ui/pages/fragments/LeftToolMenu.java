package ui.pages.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class LeftToolMenu extends BasePage {

    private final SelenideElement openTriangularListBtn = $(By.xpath("//div[contains(@class, 'AreaTriangular')]//span[contains(@class, 'ButtonWithPanel_triangleWrapper')]"));
    private final SelenideElement digitalSurfaceBtn = $(By.id("tool_panel_dtm_creator_tool"));

    private final SelenideElement openSheetsListBtn = $(By.id("panel_sheet_canvas"));
    private final ElementsCollection sheetOpts = $$x("//div[contains(@class, 'CanvasSheetButton_option')]/button/following-sibling::span[1]");
    private final SelenideElement addFragmentBtn = $(By.id("panel_fragment_canvas"));

    private final SelenideElement cutBtn = $(By.id("tool_panel_section_drafter_tool"));

    private final SelenideElement openFiguresListBtn = $(By.xpath("//button[@id='toolPanelFigure']/following-sibling::span"));
    private final SelenideElement rectangleBtn = $(By.id("tool_panel_rectangle_creator_tool"));



    public LeftToolMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Выбрать инструмент создания цифровой поверхности модели")
    public LeftToolMenu createDigitalSurface(){
        openTriangularListBtn.shouldBe(clickable, Duration.ofSeconds(5)).click();
        digitalSurfaceBtn.shouldBe(enabled, Duration.ofSeconds(5)).click();
        return this;
    }

    @Step("Выбрать добавление Листа на Холст")
    public LeftToolMenu addNewListToCanvas(String opt){
        openSheetsListBtn.click();
        waitLoading(1);
        sheetOpts.stream().filter(x -> x.getText().equals(opt)).findFirst().orElseThrow(() -> new NoSuchElementException("Элемент не найден")).click();
        return this;
    }

    @Step("Выбрать добавление Фрагмента на Холст")
    public LeftToolMenu addNewFragmentToCanvas(){
        addFragmentBtn.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Выбрать инструмент создания Разреза")
    public LeftToolMenu createCut(){
        cutBtn.shouldBe(enabled, Duration.ofSeconds(5)).click();
        return this;
    }

    @Step("Выбрать инструмент построения Прямоугольник (Полилинии)")
    public LeftToolMenu createRectangle(){
        openFiguresListBtn.shouldBe(clickable, Duration.ofSeconds(5)).click();
        rectangleBtn.shouldBe(enabled, Duration.ofSeconds(5)).click();
        return this;
    }


}
