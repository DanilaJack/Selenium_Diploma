package ui.helperUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NavigationHelper {

    @Step("Нажатие клавиши {keys}")
    public NavigationHelper pressKeyboardButton(Keys keys) {
        Selenide.actions()
                .sendKeys(keys).perform();
        return this;
    }

    @Step("Зажатие клавиши {keys}")
    public void clampingKeyboardButton(Keys keys) {
        Selenide.actions()
                .keyDown(keys).perform();
    }

    @Step("Отжатие клавиши {keys}")
    public void releaseKeyboardButton(Keys keys) {
        Selenide.actions()
                .keyUp(keys).build().perform();
    }

    @Step("Клик по координатам {xOffset}, {yOffset}")
    public NavigationHelper mouseClickByOffsetFromViewport(int xOffset, int yOffset, String ObjXpathByID) throws InterruptedException {
        Thread.sleep(500);
        SelenideElement canvas = $(By.id(ObjXpathByID));
        Selenide.actions()
                .moveToElement(canvas, xOffset, yOffset)
                .click()
                .build()
                .perform();
        return this;
    }

    @Step("Зум на сцене")
    public NavigationHelper zoomCanvas(int zoomOut) {
        String script = "const event = new WheelEvent('wheel', {deltaY: " + (zoomOut) + "}); arguments[0].dispatchEvent(event);";
//        String script = "const event = new WheelEvent('wheel', {deltaY: " + (zoomIn ? -100 : 100) + "}); arguments[0].dispatchEvent(event);";
        Selenide.executeJavaScript(script, $x("//canvas[@id='canvas']"));
        return this;
    }
}
