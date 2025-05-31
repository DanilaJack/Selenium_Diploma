package ui.pages.fragments.panelmenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

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
    public SpatialDataImport importSpatialData(String basePath, String... filesNames) {

        waitLoading(1);

        //подготовка строки с путями к необходимым для загрузки файлам
        StringBuilder builder = new StringBuilder();
        int uploadedFiles = 0;
        for (String name: filesNames) {
            uploadedFiles++;
            builder.append(basePath).append(name);
            builder.append("\n");
        }
        String paths = builder.toString().trim();
        System.out.println("Путь: " + paths);
        //загрузка файлов
        sendFiles(paths, importInput);
        while (uploadedFiles != readyToImportFiles.size()) {
            waitLoading(1);
        }
        viewArea.shouldBe(enabled, Duration.ofSeconds(50));
        return this;
    }

    @Step("Клик на нужный файл в каталоге данных")
    public SpatialDataImport clickOnFileInCatalog(String fileName){
        SelenideElement file = $x("//div[contains(@class, 'DataDirectory_list')]//div[contains(@class, 'FileItem_name') and contains(@title, '"+fileName+"')]");
        file.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на кнопку - Импортировать")
    public SpatialDataImport pressImportButton() {
        SelenideElement convertDataLoader = $x("//div[contains(@class, 'ProgressBarWrapper_loadingFileContent')]");
        if (convertDataLoader.isDisplayed()){
            convertDataLoader.should(disappear, Duration.ofSeconds(15));
        }

        importBut.shouldBe(enabled, Duration.ofSeconds(5)).click();
        waitLoading(2);
        $x("//span[text()='Каталог данных']").shouldNotBe(visible, Duration.ofSeconds(100));

        $("[data-qa='mainLoader']").shouldBe(Condition.disappear, Duration.ofSeconds(20));
        $x("//div[contains(@class,'loadingFileContent')]").shouldBe(Condition.hidden, Duration.ofSeconds(60));

        return this;
    }
}
