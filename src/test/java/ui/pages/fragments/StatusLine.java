package ui.pages.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class StatusLine extends BasePage {

    private final By coordinates = By.xpath("//span[contains(@class, 'TextStatusBar_label')]//following-sibling::span");

    public StatusLine(WebDriver driver) {
        super(driver);
    }

    @Step("Получение координат привязки/курсора")
    public List<String> getCoordinates() {
        ElementsCollection elements = $$(coordinates);
        List<String> coordinates = new ArrayList<>();

        for (SelenideElement e: elements) {
            coordinates.add(e.getText());
        }
        return coordinates;
    }
}
