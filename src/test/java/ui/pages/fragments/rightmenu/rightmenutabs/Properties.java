package ui.pages.fragments.rightmenu.rightmenutabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Properties extends BasePage {

    private final SelenideElement toggleToFillingGroup = $x("//div[contains(@class, 'PropertyListControlButtons_segmented')]//label[not(contains(@class, 'selected'))]");
    private final By bindBut = By.xpath("//div[contains(@class, 'BindingButtonBlock')]/button[1]");
    private final SelenideElement addNewFillingGroupForPMBtn = $x("//button/span[text()='Добавить группу свойств']");
    private final SelenideElement addNewFillingGroupForTrajectoryBtn = $x("//div[contains(@class, 'PropertyListControlButtons_inner')]/button[1]");

    private final SelenideElement selectObjTypeBtn = $x("//div[contains(@class, 'PropertyMultiObject_selectType')]");
    private final SelenideElement attrNameInput = $x("//input[@placeholder='Введите имя атрибута']");
    private final SelenideElement attrValueInput = $x("//input[@placeholder='Введите значение']");
    private final SelenideElement saveEditAttrBtn = $x("//button[contains(@class, 'PropertyMultiObject_saveBtn')]");

    private final String dropDownEl = "//div[contains(@class, 'ant-select-dropdown ') and not(contains(@class, 'ant-select-dropdown-hidden'))]";

    private final String attributeValueInput = "//div[contains(@class, 'PropertyMultiObject_name')";


    public Properties(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие окна привязки БО в свойствах")
    public Properties openBind() {
        $(bindBut).shouldBe(clickable, Duration.ofSeconds(2)).click();
        return this;
    }

    @Step("Переключиться на другую вкладку -> в группу свойств для наполнения или для объекта")
    public Properties switchToOtherTabForFilling() {
        toggleToFillingGroup.shouldBe(enabled).click();
        return this;
    }

    @Step("Клик на кнопку добавления новой группы свойств для наполнения Полигональной модели")
    public Properties clickAddNewGroupOfPropsForPM() {
        addNewFillingGroupForPMBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Клик на кнопку добавления новой группы свойств для наполнения Траектории")
    public Properties clickAddNewGroupOfPropsForTrajectory() {
        addNewFillingGroupForTrajectoryBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Изменить/присвоить имя группы свойств наполнения")
    public Properties giveNameToGroupOfProps(String groupName) {
        SelenideElement nameField = $x("//span[contains(@class, 'PropertyGroupName') and contains(@class, 'inputSimple')]");
        Selenide.actions().doubleClick(nameField).perform();

        SelenideElement input = nameField.$x("./input");
        input.clear();
        input.sendKeys(groupName);
        pressKeyboardButton(Keys.ENTER);

        return this;
    }

    @Step("Добавить новое свойство в группу свойств наполнения")
    public Properties addNewPropToGroupOfProps(String group, String type, String name, String value) {

        SelenideElement groupSegment = $x("//span[text()='" + group + "']/ancestor::div[contains(@class, 'ant-collapse-item')][1]");
        groupSegment.$x(".//button[contains(@class, 'PropertiesGroupPanel_editBtn')]/span[contains(text(), 'Добавить свойство')]").shouldBe(enabled).click();

        waitLoading(1);
        selectObjTypeBtn.shouldBe(interactable, Duration.ofSeconds(2)).click();

        waitLoading(1);
        $x(dropDownEl + "//span[text()='" + type + "']").shouldBe(clickable, Duration.ofSeconds(2)).click();

        attrNameInput.shouldBe(interactable, Duration.ofSeconds(3)).clear();
        attrNameInput.sendKeys(name);

        waitLoading(1);
        if (!value.isEmpty()) {
            attrValueInput.shouldBe(interactable, Duration.ofSeconds(3)).clear();
            attrValueInput.sendKeys(value);
        }
        saveEditAttrBtn.click();

        return this;
    }

    @Step("Изменить значение атрибута - {attributeName}")
    public Properties changeAttributeValue(String attributeName, String attributeValue) throws InterruptedException {
        SelenideElement input = $(By.xpath(attributeValueInput + "and text()='" + attributeName + "']/../..//input"));

        if (input.has(Condition.attribute("type")) && input.getAttribute("type").equals("checkbox")) {
            actions().click(input).perform();
        } else {
            input.click();
            clampingAndPressKeyboardButtons(Keys.CONTROL, "A");
            pressKeyboardButton(Keys.BACK_SPACE);
            input.sendKeys(attributeValue, Keys.ENTER);
        }
        return this;
    }

    @Step("Получить количество геометрических объектов внутри одного мультиобъекта")
    public int getAamountOfGeoObjects(){
        return Integer.parseInt($x("//span[contains(text(),'Свойства геометрических')]").getText().replaceAll("\\D+", ""));
    }
}
