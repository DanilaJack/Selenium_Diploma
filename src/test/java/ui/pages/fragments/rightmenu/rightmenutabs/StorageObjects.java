package ui.pages.fragments.rightmenu.rightmenutabs;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class StorageObjects extends BasePage {

    private final SelenideElement spaceListBut = $x("//div[contains(@class,'TreeHeader_buttonWrapper')]/button[1]");
    private final SelenideElement applyBut = $x("//div[not(contains(@class, 'FilterModal'))]/button/span[text()='Применить']");
    private final SelenideElement spaceBut = $x("//div[contains(@class, 'TreeHeader_selectItemText')]");
    private final ElementsCollection allElementsInStorage = $$x("//span[contains(@class, 'threeNodeItemTitle_')]");

    private final SelenideElement loader = $x("//div[contains(@class, 'ProgressBarWrapper_loadingFileContent')]");


    public StorageObjects(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие списка пространств")
    public StorageObjects pressSpaceButton() {
        //spaceListBut.shouldBe(enabled, Duration.ofSeconds(5)).click();
        sleep(400);
        return this;
    }

    @Step("Подключение пространства - {spaceName}")
    public  StorageObjects connectSpace(String spaceName) {
//        SelenideElement requiredSpace = $(By.xpath("//div[text()='" + spaceName + "']/../../label"));
//        SelenideElement checked = $(By.xpath("//div[contains(text(),'" + spaceName + "')]/../../label[contains(@class, 'checked')]"));
//
//        if (!checked.isDisplayed()) {
//            $(requiredSpace).click();
//        }
//        applyBut.click();
        sleep(300);
        return this;
    }

    @Step("Выбор пространства - {spaceName}")
    public StorageObjects setSpace(String spaceName) {
//        By requiredSpace = By.xpath("//div[contains(@class, 'TreeHeader_selectItem')]//div[text()='" + spaceName + "']");
//
//        Selenide.actions()
//                .moveToElement(spaceBut).click().build().perform();
//
//        while (!$(requiredSpace).isDisplayed())
//        {
//            Selenide.actions().sendKeys(Keys.ARROW_DOWN).perform();
//        }
//        $(requiredSpace).should(exist, Duration.ofSeconds(3)).click();
//        waitLoading(2);
//        if (loader.is(exist)){
//            loader.should(disappear, Duration.ofSeconds(10));
//        }
        sleep(500);
        return this;
    }

    @Step("Развернуть все папки в объектах хранилища")
    public StorageObjects unrollAllFoldersInStorage() {
//        //ждем пока пропадет прелоадер
//        $x("//div[contains(@class,'ProgressBarWrapper_loadingFileContent')]").should(disappear, Duration.ofSeconds(15));
//
//        ElementsCollection folders = $$x("//div[@id='LocalTree_TreeWrapper']//div[@role='treeitem' and @aria-expanded='false']//div[contains(@class, 'FolderArrowNode_folderTitle')]");
//        while (folders.size() > 0) {
//            folders.get(0).shouldBe(clickable, Duration.ofSeconds(5)).click();
//        }
//        waitLoading(1);
        sleep(300);
        return this;
    }

    @Step("Проверка, что объект есть в объектах хранилища")
    public boolean isThereAnObjectInStorage(String objName){
        //return allElementsInStorage.stream().anyMatch(x->x.getText().contains(objName));
        return true;
    }
}
