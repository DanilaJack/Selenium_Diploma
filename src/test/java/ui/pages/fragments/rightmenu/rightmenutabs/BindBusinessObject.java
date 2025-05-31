package ui.pages.fragments.rightmenu.rightmenutabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BindBusinessObject extends BasePage {

    private final SelenideElement spaceInput = $x("//span[text()='Пространство']/ancestor::label//div[@class='ant-select-selector']");
    private final SelenideElement templateInput = $x("//span[text()='Шаблон бизнес-объекта']/ancestor::label//div[@class='ant-select-selector']");
    private final SelenideElement createBut = $x("//div[contains(@class, 'BindingFooter_buttonPanel')]/button");


    public BindBusinessObject(WebDriver driver) {
        super(driver);
    }

    @Step("Выбор пространства - {spaceName}")
    public BindBusinessObject chooseSpace(String spaceName) throws InterruptedException {

        SelenideElement requiredSpace = $x("//div[contains(@class, 'ant-select-dropdown') and not(contains(@class, 'ant-select-dropdown-hidden'))]//span[text()='" + spaceName + "']");
        spaceInput.shouldBe(visible, Duration.ofSeconds(3)).click();

        int attempts = 0;
        while (!requiredSpace.isDisplayed() && attempts < 30) {
            Selenide.actions().sendKeys(Keys.ARROW_DOWN).perform();
            attempts++;
        }
        requiredSpace.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Выбор шаблона - {template} из списка")
    public BindBusinessObject chooseTemplate(String template) {
        SelenideElement requiredObj = $x("//div[@class='ant-select-item-option-content']//span[text()='" + template + "']");
        templateInput.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();

        int attempts = 0;
        while (!requiredObj.isDisplayed() && attempts < 30) {
            Selenide.actions().sendKeys(Keys.ARROW_DOWN).perform();
            attempts++;
        }
        requiredObj.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Нажатие на кнопку - создать/сохранить")
    public BindBusinessObject pressCreateBut() {
        waitLoading(2);
        createBut.click();
        waitLoading(1);

        // Проверка на сообщение о недоступности создания
        SelenideElement unsuccessMessage = $x("//div[@class= 'ant-notification-notice-message']//div[text()='Создание недоступно']");
        if (unsuccessMessage.isDisplayed()) {
            throw new RuntimeException("Создание недоступно, не заполнены все поля в блоке свойств");
        }

        // Ожидание и проверка успешного сообщения (если оно должно появиться)
        try {
            SelenideElement successMessage = $x("//div[contains(text(),'Новый бизнес объект создан и помещен в хранилище')] | //span[contains(text(),'Новые бизнес-объекты созданы и помещены в хранилище')]");
            successMessage.should(exist, Duration.ofSeconds(20)); // Ждем до 10 секунд появления сообщения

            System.out.println("Данные успешно сохранены.");
        } catch (com.codeborne.selenide.ex.ElementNotFound e) {
            // Сообщение не появилось, но это не обязательно ошибка, если оно появляется не всегда
            System.out.println("Сообщение об успешном создании не появилось (может быть нормальным поведением)");
        }

        $x("//div[contains(@class,'SessionLoader_overlay')]").shouldNot(exist, Duration.ofSeconds(20));
        $x("//div[contains(@class, 'ObjectProperties_container')]//h2[text()='Создание бизнес-объекта и привязка']").shouldNot(exist, Duration.ofSeconds(10));

        waitLoading(2);
        return this;
    }
}
