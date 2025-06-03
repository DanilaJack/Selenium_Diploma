package ui.pages.fragments.panelmenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.core.BasePage;
import utility.NetworkMonitor;

import java.net.MalformedURLException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SpatialDataImport extends BasePage {

    private final By importInput = By.xpath("//input[@id='points']"); // открыть пространнственных данных
    private final SelenideElement viewArea = $x("//div[contains(@class, 'ViewArea_viewArea')]");
    private final ElementsCollection readyToImportFiles = $$x("//div[contains(@class, 'FileItem_tag')]");
    private final SelenideElement importBut = $x("//span[text()='Импортировать']");


    public SpatialDataImport(WebDriver driver) {
        super(driver);
    }

    @Step("Импорт пространственных данных {filesNames}")
    public SpatialDataImport importSpatialData(String basePath, String... filesNames) throws InterruptedException {

//        waitLoading(1);
//
//        //подготовка строки с путями к необходимым для загрузки файлам
//        StringBuilder builder = new StringBuilder();
//        int uploadedFiles = 0;
//        for (String name: filesNames) {
//            uploadedFiles++;
//            builder.append(basePath).append(name);
//            builder.append("\n");
//        }
//        String paths = builder.toString().trim();
//        System.out.println("Путь: " + paths);
//        //загрузка файлов
//        sendFiles(paths, importInput);
//        while (uploadedFiles != readyToImportFiles.size()) {
//            waitLoading(1);
//        }
//        viewArea.shouldBe(enabled, Duration.ofSeconds(50));
        Thread.sleep(3000);
        return this;
    }

    @Step("Клик на нужный файл в каталоге данных")
    public SpatialDataImport clickOnFileInCatalog(String fileName) throws InterruptedException {
//        SelenideElement file = $x("//div[contains(@class, 'DataDirectory_list')]//div[contains(@class, 'FileItem_name') and contains(@title, '"+fileName+"')]");
//        file.shouldBe(visible).click();
        Thread.sleep(2000);
        return this;
    }

    @Step("Нажатие на кнопку - Импортировать")
    public SpatialDataImport pressImportButton() {
//        SelenideElement convertDataLoader = $x("//div[contains(@class, 'ProgressBarWrapper_loadingFileContent')]");
//        if (convertDataLoader.isDisplayed()){
//            convertDataLoader.should(disappear, Duration.ofSeconds(15));
//        }
//
//        importBut.shouldBe(enabled, Duration.ofSeconds(5)).click();
//        waitLoading(2);
//        $x("//span[text()='Каталог данных']").shouldNotBe(visible, Duration.ofSeconds(100));
//
//        $("[data-qa='mainLoader']").shouldBe(Condition.disappear, Duration.ofSeconds(20));
//        $x("//div[contains(@class,'loadingFileContent')]").shouldBe(Condition.hidden, Duration.ofSeconds(60));
        sleep(10000);
        return this;
    }

    @Step("Нажатие на кнопку - Импортировать")
    public SpatialDataImport pressImportButton(String url, int statusCode) throws MalformedURLException, InterruptedException {
//        SelenideElement convertDataLoader = $x("//div[contains(@class, 'ProgressBarWrapper_loadingFileContent')]");
//        if (convertDataLoader.isDisplayed()){
//            convertDataLoader.should(disappear, Duration.ofSeconds(15));
//        }
//
//        NetworkMonitor.startMonitoring();
//        importBut.shouldBe(enabled, Duration.ofSeconds(5)).click();
//        waitLoading(2);
//
//        int status = NetworkMonitor.waitForResponse(url, statusCode);
//        System.out.println("NetworkMonitor : "+status);
//        Assert.assertEquals(status, statusCode);
//        NetworkMonitor.stopMonitoring();
//        $x("//span[text()='Каталог данных']").shouldNotBe(visible, Duration.ofSeconds(100));
//
//        $("[data-qa='mainLoader']").shouldBe(Condition.disappear, Duration.ofSeconds(20));
//        $("[data-qa='loaderSession']").shouldBe(Condition.disappear, Duration.ofSeconds(10));
//        $("#docMenu").shouldBe(Condition.clickable, Duration.ofSeconds(10));
        Thread.sleep(10000);
        return this;
    }

    // Способ сохранения объекта
    @Step("Дописать в объект")
    public SpatialDataImport selectAddToObject(){
        //$x("//div[contains(@class,'ImportSaveOptionBlock')]/label[2]").click();
        sleep(200);
        return this;
    }

    @Step("Поле - Введите объект, при выборе - Дописать в объект")
    public SpatialDataImport selectInputObject(String item){
//        $x("//div[contains(@class,'ImportSaveOptionBlock')]/span[contains(@class,'ImportSaveOptionBlock_input')]").click();
//        $x("//div[contains(@class,'ImportSelect_option') and text()='"+ item +"']").click();
        sleep(500);
        return this;
    }
}
