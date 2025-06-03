package ui.core;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;
import ui.helperUI.NavigationHelper;
import utility.ProjectSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {

    RemoteWebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = (RemoteWebDriver) driver;
    }

    protected void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }

    public void compareImages() throws IOException {

        int x = 0;
        int y = 0;
        int width = 1700;
        int height = 800;

        Coords area = new Coords(x,y,width,height);

        AShot aShot = new AShot().imageCropper(new IndentCropper().addIndentFilter(coords -> coords));
        Screenshot screenshot = aShot.takeScreenshot(driver);

        BufferedImage croppedImage = screenshot.getImage().getSubimage(area.x, area.y, width, height);

        File currentScreen = new File("src/test/resources/CurrentScreenShot/Scenario_test_cur.png");
        ImageIO.write(croppedImage, "PNG", currentScreen);

        File expectedImageFile = new File("src/test/resources/ExpectedScreenShot/Scenario_test_expected.png");
        if (!expectedImageFile.exists()) {
            ImageIO.write(croppedImage, "PNG", expectedImageFile);
        }
        BufferedImage expectedImage = ImageIO.read(expectedImageFile);

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage, croppedImage);

        if (diff.hasDiff()){
            System.out.println("Difference found");
            ImageIO.write(diff.getMarkedImage(), "PNG", new File("src/test/resources/ExpectedScreenShot/Scenario_test_diff.png"));
        }
        else {
            System.out.println("Difference not found");
        }

    }

    @Step("Ожидание загрузки в секундах")
    //todo "Selenide.sleep(timeOutSec * 1000L);"
    public void waitLoading(int timeOutSec) {
        for (int i = 0; i < timeOutSec; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Подтягивание файлов из файловой системы")
    protected void sendFiles(String name, By locator) {
        if (ProjectSettings.FILE_DETECTOR_INCLUDED) {
            driver.setFileDetector(new LocalFileDetector());
            (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions
                    .presenceOfElementLocated(locator)).sendKeys(name);
        } else {
            (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions
                    .presenceOfElementLocated(locator)).sendKeys(name);
        }
    }

    @Step("Нажатие клавиши {keys}")
    public BasePage pressKeyboardButton(Keys keys) {
        Selenide.actions()
                .sendKeys(keys).perform();
        return this;
    }

    @Step("Нажатие клавиши {bnt}")
    public BasePage pressKeyboardButton(Keys modifierKey, String bnt) {
        Selenide.actions()
                .keyDown(modifierKey)        // Эмулируем нажатие модификатора (например, CTRL)
                .sendKeys(bnt)               // Эмулируем нажатие самой клавиши
                .keyUp(modifierKey)          // Отпускаем модификатор
                .perform();
        return this;
    }

    @Step("Получение последнего загруженного (экспортированного) файла")
    public File getLatestDownloadedFile(String downloadDir){

        File dir = new File(downloadDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0){
            File latestFile = files[0];
            for (File file: files){
                if (file.lastModified() > latestFile.lastModified()){
                    latestFile = file;
                }
            }
            return latestFile;
        }
        return null;
    }

    @Step("Зажатие клавиши {clamping}, нажатие клавиши {press}")
    public void clampingAndPressKeyboardButtons(Keys clamping, String press) throws InterruptedException {
        Thread.sleep(500);
        Selenide.actions()
                .keyDown(clamping)
                .sendKeys(press)
                .keyUp(clamping)
                .build().perform();
    }

}
