package ui.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ContextMenu extends BasePage {
    public ContextMenu(WebDriver driver) {
        super(driver);
    }

    public ContextMenu clickOnContextMenuOption(String option) throws InterruptedException {
//        Thread.sleep(1000);
//        $(By.xpath("//span[contains(text(), '" + option + "')]")).click();
        sleep(200);
        return this;
    }
}
