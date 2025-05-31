package ui.pages.fragments.rightmenu.rightmenutabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.*;

public class SceneObjects extends BasePage {

    private final ElementsCollection rolledElements = $$x("//div[@id='tree_on_stage']//div[contains(@class, 'FolderArrowNode_folderTitle')]");
    private final ElementsCollection allVisibleObjectsInTree = $$x("//div[@id='tree_on_stage']//p[contains(@class, 'Title_threeNodeItemText')]");
    private final SelenideElement panelOfObjects = $x("//div[@id='tree_on_stage']//div[contains(@class, 'TreeObjectsOnStage_tree')]");


    public SceneObjects(WebDriver driver) {
        super(driver);
    }

    @Step("Раскрыть все папки в дереве объетов сессии")
    public SceneObjects unrollAllFoldersInObjects(){
        waitLoading(1);
        rolledElements.forEach(x-> {
            x.shouldBe(Condition.clickable).click();
            sleep(500);
        });
        waitLoading(1);
        return this;
    }

    @Step("Существует ли объект в дереве объектов сессии")
    public boolean isThereMOInSceneObjects(String objName) {
        waitLoading(1);
        return allVisibleObjectsInTree.stream().anyMatch(x->x.getText().contains(objName));
    }

    @Step("Выбрать объект в дереве объектов сессии")
    public SceneObjects selectObject(String objectName) {
        SelenideElement object = $$(By.xpath("//p[contains(@class,'Title_threeNodeItemText') and contains(text(),'"+objectName+"')]")).last();

        int count = 0;
        while (!object.isDisplayed()){
            scrollPanelForSomePixels(100);
            count++;
            if (count > 20){
                throw new TimeoutException("Element not found within 20 iterations");
            }
        }
        object.shouldBe(clickable).click();
        return this;
    }

    @Step("Проскроллить панель объектов сессии на {pixels} пикселей вниз")
    public SceneObjects scrollPanelForSomePixels(int pixels) {
        waitLoading(1);
        executeJavaScript(
                "arguments[0].style.overflowY = 'scroll'; " +
                        "arguments[0].scrollTop += arguments[1]; " +
                        "arguments[0].style.overflowY = 'hidden';",
                panelOfObjects, pixels
        );
        return this;
    }

    @Step("Навести курсор на глазик чтобы изменить видимость")
    public SceneObjects hoverAndChangeVisibility(String objName){
        SelenideElement objRow = $x("//p[contains(@class,'Title_threeNodeItemText') and text()='"+objName+"']");
        SelenideElement eye = $x("//p[contains(@class,'Title_threeNodeItemText') and text()='"+objName+"']/..//button[3]");
        int count = 0;
        while (!eye.exists()){
            scrollPanelForSomePixels(100);
            count++;
            if (count > 20){
                throw new TimeoutException("Element not found within 20 iterations");
            }
        }
        if (!objRow.is(interactable)){
            scrollPanelForSomePixels(100);
        }
        waitLoading(1);
        while (!eye.is(clickable)){
            eye.hover();
        }
        eye.click();
        return this;
    }

    @Step("Раскрыть папку в объектах сессии")
    public SceneObjects unrollFolderForName(String nameFolder) {
        waitLoading(2);
        SelenideElement folder = $x("//div[@id='tree_on_stage']//p[text()='"+nameFolder+"']/../div[contains(@class, 'FolderArrowNode_folderTitle')]");
        if (folder.exists())
            folder.click();
        return this;
    }
}
