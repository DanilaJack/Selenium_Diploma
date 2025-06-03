package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.core.TestBase;
import utility.Excel;
import utility.ProjectSettings;

import java.io.File;
import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.sleep;

public class Scenario_2_3 extends TestBase {

    @Test
    public void business_scenario_2_3() throws InterruptedException, MalformedURLException {

        //todo: Костылямба
        page.navigation.mouseClickByOffsetFromViewport(100, 0, "canvas");
        page.navigation.zoomCanvas(-100);

        // Объекты для импорта
        String[] resources = {"ВКРАП_1ПЭ_марк_точки.DAT", "ВКРАП_1ПЭ_марк_точки_новые.DAT", "ПЛАН_ВКРАП_1ПЭ.STR"};
        //Импортировать объекты из файловой системы
        page.scene
                .panelMenu
                .pressFileBut()
                .getSpatialDataImport()
                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, "/scenario_2_3/"+resources[0], "/scenario_2_3/"+resources[2])
                .clickOnFileInCatalog(resources[0])
                .pressImportButton("/ggis/api/style/objects-styles/get-by-mo-uuids-with-template-legends", 200);

        // Раскрыть все папки в объектах сессии
        page.scene
                .rightMenu
                .openObjectsInSession()
                .getSceneObjects()
                .unrollAllFoldersInObjects();

        // Проверка, что все импортированные объекты пришли в дерево объектов сессии

        Assert.assertTrue(
                page.scene
                        .rightMenu
                        .getSceneObjects()
                        .isThereMOInSceneObjects(resources[0]));

        Assert.assertTrue(
                page.scene
                        .rightMenu
                        .getSceneObjects()
                        .isThereMOInSceneObjects(resources[2]));

        // Нажать на иконку Свойств в правом меню
        page.scene
                .rightMenu
                .openProperties();

        // Выбрать объект маркшейдерских точек в объектах сессии
        page.scene
                .rightMenu
                .getSceneObjects()
                .selectObject(resources[0]);

        // Привязать этот объект к шаблону БО
        page.scene
                .rightMenu
                .getProperties()
                .changeAttributeValue("Имя", "МаркТочки")
                .openBind();

        page.scene
                .rightMenu
                .getBindBusinessObject()
                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .chooseTemplate("МаркшейдерскиеТочки")
                .pressCreateBut();

        //Выбрать объект географической карты в объектах сессии
        page.scene
                .rightMenu
                .getSceneObjects()
                .selectObject(resources[2]);

        // Привязать его к шаблону БО
        page.scene
                .rightMenu
                .getProperties()
                .openBind();

        page.scene
                .rightMenu
                .getBindBusinessObject()
                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .chooseTemplate("Гео карта")
                .pressCreateBut();

        // Раскрыть все объекты в соответствующем пространстве в объектах в хранилище
        page.scene
                .rightMenu
                .closeProperties()
                .closeObjectsInSession()
                .openObjectsInStorage()
                .getStorageObjects()
                .pressSpaceButton()
                .connectSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .setSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .unrollAllFoldersInStorage();

        resources[0] = "МаркТочки";

        // Проверка на существование объектов в пространстве
        Assert.assertTrue(page.scene
                .rightMenu
                .getStorageObjects()
                .isThereAnObjectInStorage(resources[0]));

        Assert.assertTrue(page.scene
                .rightMenu
                .getStorageObjects()
                .isThereAnObjectInStorage(resources[2]));

        page.scene
                .panelMenu
                .pressFileBut()
                .getSpatialDataImport()
                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, "/scenario_2_3/"+resources[1])
                .selectAddToObject()
                .selectInputObject(resources[0])
                .pressImportButton();

        sleep(2000);

        // Сфокусироваться на новых точках
        page.scene
                .rightMenu
                .openObjectsInSession()
                .getSceneObjects()
                .unrollAllFoldersInObjects()
                .selectObject(resources[0])
                .clickFocusBut();

        Assert.assertEquals(page.scene
                .rightMenu
                .openProperties()
                .getProperties()
                .getAamountOfGeoObjects(), 75);

        page.scene
                .rightMenu
                .ClickPropertiesMenu();

        page.navigation.moveCursorByPixels(-200, 200);
        page.navigation.zoomCanvas(-100);

        // Войти в режим редактирования МО новые точки
        page.scene
                .contextPanel
                .enterEditModeOfMO();


//        page.navigation.movingByCoordBelow2(31341.7, 29455);
//        page.navigation.mouseClick();

        page.scene
                .rightMenu
                .closeObjectsInSession()
                .closeObjectsInStorage()
                .openProperties()
                .getProperties()
                .changeAttributeValue("Имя ГО", "Участ.1");

        page.navigation.mouseClickByOffsetFromViewport(-200, 0, "canvas");

//        page.navigation.movingByCoordBelow2(31452.5, 29425.3);
//        page.navigation.mouseClick();


        page.scene
                .rightMenu
                .getProperties()
                .changeAttributeValue("Имя ГО", "Участ.2");

        // Кликнуть правой кнопкой мыши для вызова контекстного меню
        page.navigation
                .clickRightButMouse(-200, 0, "canvas");

        // Открыть сцену 3d в виде таблицы
        page.scene
                .contextMenu
                .clickOnContextMenuOption("Открыть в виде таблицы");

        // В табличном представление перейти на вкладку 'Геометрия' и выделить все ГО и вернуться обратно на сцену
        page.scene
                .tableView
                .clickOnNeededTabOfMultiobject("Геометрия")
                .globalSearch("18330")
                .editCellInTable("18330", "Имя ГО", "Участок 10")
                .clickOnNeededTabOfMultiobject("Элементы")
                .globalSearch("18350")
                .editCellInTable("18350", "Y", "29590")
                .clickOnBackToSceneModeBut();

        page.scene
                .panelMenu
                .pressSaveSettings();

        page.navigation.clickRightButMouse(-200, 0, "canvas");

        page.scene
                .contextMenu
                .clickOnContextMenuOption("Открыть в виде таблицы");

        int rowsNum = page.scene
                .tableView
                .clickOnNeededTabOfMultiobject("Элементы")
                .getFullAmountOfElements();

        page.scene
                .tableView
                .exportDataFromTable();

        sleep(5000);

//        File tableOfElements = page.scene
//                .tableView
//                .getLatestDownloadedFile(ProjectSettings.DOWNLOADS_PATH);
//
//        int rowsNum2 = Excel.getRowCount(tableOfElements);
//
//        Assert.assertEquals(rowsNum, rowsNum2);

        int a = 10;
    }
}
