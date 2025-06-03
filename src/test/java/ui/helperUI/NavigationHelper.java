package ui.helperUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ui.pages.fragments.StatusLine;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

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
//        Thread.sleep(500);
//        SelenideElement canvas = $(By.id(ObjXpathByID));
//        Selenide.actions()
//                .moveToElement(canvas, xOffset, yOffset)
//                .click()
//                .build()
//                .perform();
        Thread.sleep(300);
        return this;
    }

    @Step("Зум на сцене")
    public NavigationHelper zoomCanvas(int zoomOut) throws InterruptedException {
//        String script = "const event = new WheelEvent('wheel', {deltaY: " + (zoomOut) + "}); arguments[0].dispatchEvent(event);";
////        String script = "const event = new WheelEvent('wheel', {deltaY: " + (zoomIn ? -100 : 100) + "}); arguments[0].dispatchEvent(event);";
//        Selenide.executeJavaScript(script, $x("//canvas[@id='canvas']"));
        Thread.sleep(300);
        return this;
    }

    @Step("Передвинуть курсор попиксельно {xOffset}, {yOffset}")
    public void moveCursorByPixels(int xOffset,int yOffset){
//        Selenide.actions()
//                .moveByOffset(xOffset,yOffset).perform();
        sleep(100);
    }

    @Step("Перемещаться попиксельно в соответствие с координатами курсора в статусной строке")
    public NavigationHelper movingByCoordBelow2(double X, double Y) {

        // Начальные координаты и шаги
        int largeStep = 25;
        int mediumStep = 10;
        int smallStep = 1;

        testMovingX(X);
        testMovingY(Y);

        // Перемещение курсора в стартовую позицию

        // Инициализация объекта statusLine
        StatusLine statusLine = new StatusLine(WebDriverRunner.getWebDriver());

        // текущие координаты в статусной строке (x,y,z)
        List<String> coords = statusLine.getCoordinates();

        //
        while (Math.abs(Double.parseDouble(coords.get(0)) - X) > 1 || Math.abs(Double.parseDouble(coords.get(1)) - Y) > 1) {

            int stepX = largeStep;
            int stepY = largeStep;

            // Если курсор близко к целевой точке, уменьшаем шаг
            if (Math.abs(Double.parseDouble(coords.get(0)) - X) < largeStep) {
                stepX = mediumStep;
            }
            if (Math.abs(Double.parseDouble(coords.get(1)) - Y) < largeStep) {
                stepY = mediumStep;
            }

            if (Math.abs(Double.parseDouble(coords.get(0)) - X) < mediumStep) {
                stepX = smallStep;
            }
            if (Math.abs(Double.parseDouble(coords.get(1)) - Y) < mediumStep) {
                stepY = smallStep;
            }

            if (Double.parseDouble(coords.get(0)) < X && Math.abs(Double.parseDouble(coords.get(0)) - X) > 0.1){
                Selenide.actions()
                        .moveByOffset(stepX, 0).perform();
            }

            if (Double.parseDouble(coords.get(0)) > X && Math.abs(Double.parseDouble(coords.get(0)) - X) > 0.1){
                Selenide.actions()
                        .moveByOffset(-stepX, 0).perform();
            }

            if (Double.parseDouble(coords.get(1)) < Y && Math.abs(Double.parseDouble(coords.get(1)) - Y) > 0.1) {
                Selenide.actions()
                        .moveByOffset(0, -stepY).perform();
            }

            if (Double.parseDouble(coords.get(1)) > Y && Math.abs(Double.parseDouble(coords.get(1)) - Y) > 0.1) {
                Selenide.actions()
                        .moveByOffset(0, stepY).perform();
            }

            coords = statusLine.getCoordinates();
        }

        sleep(1000);

        return this;
    }

    @Step("Приближение к X координате")
    private void testMovingX(double finish_X){

        // Инициализация объекта statusLine
        StatusLine statusLine = new StatusLine(WebDriverRunner.getWebDriver());

        Selenide.actions()
                .moveByOffset(1, 0).perform();

        // текущие координаты в статусной строке (x,y,z)
        List<String> coords = statusLine.getCoordinates();

        // стартовые координаты
        double start_X = Double.parseDouble(coords.get(0));

        int start_step_X = 20;
        int signX = 1;

        if (start_X < finish_X){
            Selenide.actions()
                    .moveByOffset(start_step_X, 0)
                    .moveByOffset(start_step_X, 0)
                    .perform();
        }
        else if (start_X > finish_X){

            Selenide.actions()
                    .moveByOffset(-start_step_X, 0)
                    .moveByOffset(-start_step_X, 0)
                    .perform();
            signX = -1;
        }
        coords = statusLine.getCoordinates();
        double current_X = Double.parseDouble(coords.get(0));
        double speed = current_X - start_X;
        double distance = finish_X - start_X;

        int step_X = (int) (start_step_X * (distance / speed));

        Selenide.actions()
                .moveByOffset(-signX*start_step_X, 0)
                .moveByOffset(-signX*start_step_X, 0)
                .perform();

        Selenide.actions()
                .moveByOffset(signX*step_X, 0)
                .perform();
    }

    @Step("Приближение к Y координате")
    private void testMovingY(double finish_Y){

        // Инициализация объекта statusLine
        StatusLine statusLine = new StatusLine(WebDriverRunner.getWebDriver());

        // текущие координаты в статусной строке (x,y,z)
        List<String> coords = statusLine.getCoordinates();

        // стартовые координаты
        double start_Y = Double.parseDouble(coords.get(1));

        int start_step_Y = 20;
        int sign = 1;

        if (start_Y < finish_Y) {
            Selenide.actions()
                    .moveByOffset(0, -start_step_Y)
                    .moveByOffset(0, -start_step_Y)
                    .perform();
        }
        else if (start_Y > finish_Y) {
            Selenide.actions()
                    .moveByOffset(0, start_step_Y)
                    .moveByOffset(0, start_step_Y)
                    .perform();
            sign = -1;
        }

        coords = statusLine.getCoordinates();
        double current_Y = Double.parseDouble(coords.get(1));
        double speed = current_Y - start_Y;
        double distance = finish_Y - start_Y;

        int step_Y = (int) (start_step_Y * (distance / speed));

        Selenide.actions()
                .moveByOffset(0, sign*start_step_Y)
                .moveByOffset(0, sign*start_step_Y)
                .perform();

        Selenide.actions()
                .moveByOffset(0, -sign*step_Y)
                .perform();

        Selenide.actions()
                .moveByOffset(0, 0)
                .perform();
    }

    @Step("Клик в текущее место курсора")
    public NavigationHelper mouseClick(){
        Selenide.actions()
                .click()
                .perform();
        return this;
    }

    @Step("Клик ПКМ по координатам {xOffset}, {yOffset}")
    public NavigationHelper clickRightButMouse(int xOffset,int yOffset,String ObjXpathByID){
//
        sleep(300);
        return this;
    }

}
