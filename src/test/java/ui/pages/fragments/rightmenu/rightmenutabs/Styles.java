package ui.pages.fragments.rightmenu.rightmenutabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class Styles extends BasePage {

    String openPopOver = "//div[contains(@class, 'ant-popover ') and not(contains(@class, 'ant-popover-hidden'))]";


    private final SelenideElement textMarkBtn = $x("//div[contains(@class, 'TextSettingGroupElement_button')]/button");
    private final SelenideElement markTextArea = $x(openPopOver+"//textarea");
    private final SelenideElement acceptMarkTextBtn = $x(openPopOver+"//button[contains(@class, 'accept')]");
    private final SelenideElement markEye = $x("//div[contains(@class, 'ElementOfStylePanel')]/button");


    public Styles(WebDriver driver) {
        super(driver);
    }

    @Step("Изменить/Установить текст текстовой метки")
    public Styles setMarkText(String markText) {
        waitLoading(1);
        textMarkBtn.shouldBe(Condition.visible, Duration.ofSeconds(2)).click();

        waitLoading(1);
        actions()
                .click(markTextArea)
                .sendKeys(Keys.CONTROL + "A")
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(markText)
                .build()
                .perform();

        acceptMarkTextBtn.shouldBe(Condition.visible, Duration.ofSeconds(2)).click();
        return this;
    }

    @Step("Изменить видимость текстовой метки")
    public Styles changeMarkVisibility(){
        markEye.shouldBe(Condition.clickable, Duration.ofSeconds(2)).click();
        return this;
    }
}
