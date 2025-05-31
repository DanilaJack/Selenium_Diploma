package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class SessionPage extends BasePage {

    SelenideElement openNewSessionBtn = $x("//button//span[text()='Открыть новую сессию']");

    public SessionPage(WebDriver driver) {
        super(driver);
    }

    public SessionPage clickOpenNewSessionBtn() {
        openNewSessionBtn.click();
        return this;
    }
}
