package ui.tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.core.TestBase;
import utility.NetworkMonitor;
import utility.ProjectSettings;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static utility.TxtFileProcessing.fileReaderIntoDoubleArray;

public class Scenario_2_8 extends TestBase {

    @Test
    public void businessScenario() throws InterruptedException, IOException {

        open("https://google.com");

//        //todo: Костылямба
//        page.navigation.mouseClickByOffsetFromViewport(100, 0, "canvas");
//        page.navigation.zoomCanvas(-100);
//
//        // Объекты для импорта
//        String[] resources = {"Поверхность основания склада", "Точки съемки склада породы"};
//
//        // Импортировать объекты на сцену
//        page.scene
//                .panelMenu
//                .pressFileBut()
//                .getSpatialDataImport()
//                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, "/scenario_2_8/"+resources[0]+".tridb", "/scenario_2_8/"+resources[1]+".DAT")
//                .clickOnFileInCatalog(resources[0]+".tridb")
//                .pressImportButton();
//
//        NetworkMonitor.startMonitoring();
//        int status = NetworkMonitor.waitForResponse("/ggis/api/style/objects-styles/get-by-mo-uuids-with-template-legends", 200);
//        System.out.println("NetworkMonitor : "+status);
//        NetworkMonitor.stopMonitoring();
//
//        page.scene
//                .rightMenu
//                .openObjectsInSession()
//                .getSceneObjects()
//                .unrollAllFoldersInObjects();
//
//        // Проверка, что импортированная поверхность основания склада пришла в объекты сессии
//        Assert.assertTrue(page.scene
//                .rightMenu
//                .getSceneObjects()
//                .isThereMOInSceneObjects(resources[0]));
//
//        // Проверка, что точки съемки склада породы пришли в объекты сессии
//        Assert.assertTrue(page.scene
//                .rightMenu
//                .getSceneObjects()
//                .isThereMOInSceneObjects(resources[1]));
//
//        // Выделить поверхность основания склада в дереве объектов сессии
//        page.scene
//                .rightMenu
//                .getSceneObjects()
//                .selectObject(resources[0]);
//
//        // Привязать выделенный объект к ШБО
//        page.scene
//                .rightMenu
//                .openProperties()
//                .getProperties()
//                .openBind();
//
//        page.scene
//                .rightMenu
//                .getBindBusinessObject()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Поверхности_складов")
//                .pressCreateBut();
//
//        // Выделить объект Точки съемки склада породы в дереве объектов сессии
//        page.scene
//                .rightMenu
//                .getSceneObjects()
//                .selectObject(resources[1]);
//
//        // Привязать выделенный объект к ШБО
//        page.scene
//                .rightMenu
//                .getProperties()
//                .openBind();
//
//        page.scene
//                .rightMenu
//                .getBindBusinessObject()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Точки_съемки_поверхностей_складов")
//                .pressCreateBut();
//
//        // Выбрать пространство в объектах хранилища
//        page.scene
//                .rightMenu
//                .closeObjectsInSession()
//                .closeProperties()
//                .openObjectsInStorage()
//                .getStorageObjects()
//                .pressSpaceButton()
//                .connectSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .setSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .unrollAllFoldersInStorage();
//
//        // Проверка на существование объектов в пространстве
//        Assert.assertTrue(page.scene
//                .rightMenu
//                .getStorageObjects()
//                .isThereAnObjectInStorage(resources[0]));
//
//        Assert.assertTrue(page.scene
//                .rightMenu
//                .getStorageObjects()
//                .isThereAnObjectInStorage(resources[1]));
//
//        // Скрыть видимость Поверхности основания склада на сцене для удобства
//        page.scene
//                .rightMenu
//                .closeObjectsInStorage()
//                .openObjectsInSession()
//                .getSceneObjects()
//                .unrollAllFoldersInObjects()
//                .hoverAndChangeVisibility(resources[0]+", "+resources[0]);
//
//        // Выбрать объект в дереве объектов сессии - Точки съемки склада породы
//        page.scene
//                .rightMenu
//                .getSceneObjects()
//                .selectObject(resources[1]);
//
//        // В левой панели инструментов выбрать создание цифровой поверхности модели
//        page.scene
//                .leftToolMenu
//                .createDigitalSurface();
//
//        // Выйти из режима редактирования МО - Цифровая поверхность (она остается выделенной)
//        page.navigation.pressKeyboardButton(Keys.ESCAPE);
//
//        // Привязать сформировавшийся каркас к ШБО - каркасы складов
//        page.scene
//                .rightMenu
//                .openProperties()
//                .getProperties()
//                .openBind();
//
//        page.scene
//                .rightMenu
//                .getBindBusinessObject()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Каркасы_складов")
//                .pressCreateBut();
//
//        // Скрыть со сцены видимость Точек и вернуть видимость поверхности основания склада
//        page.scene
//                .rightMenu
//                .closeProperties()
//                .getSceneObjects()
//                .unrollFolderForName("Новые полигональные модели")
//                .hoverAndChangeVisibility(resources[1]+".DAT")
//                .hoverAndChangeVisibility(resources[0]+", "+resources[0]);
//
//        // Установить вид сверху
//        page.scene
//                .viewPanel
//                .settingCameraInPlaneTopAndBottom("Вид сверху");
//
//        // Свернуть панель объектов сессии в правом меню
//        page.scene
//                .rightMenu
//                .closeObjectsInSession();
//
//        // Выделить через Control две поверхности
//        page.navigation.clampingKeyboardButton(Keys.CONTROL);
//
//        page.scene
//                .rightMenu
//                .openObjectsInSession()
//                .getSceneObjects()
//                .unrollAllFoldersInObjects()
//                .selectObject(resources[0])
//                .selectObject("Полигональные модели 1");
//
//        page.navigation.releaseKeyboardButton(Keys.CONTROL);
//
//        // Расчитать объем между поверхностями
//        page.scene
//                .contextPanel
//                .pressVolumeBetweenSurfaces();
//
//        // Нажать на esc - выйти из режима редактирования МО - Полигональные модели 2
//        page.navigation.pressKeyboardButton(Keys.ESCAPE);
//
//        // Добавить числовое свойство рассчитанному объему между поверхностями
//        page.scene
//                .rightMenu
//                .openProperties()
//                .getProperties()
//                .switchToOtherTabForFilling()
//                .clickAddNewGroupOfPropsForPM()
//                .giveNameToGroupOfProps("Группа свойств склада")
//                .addNewPropToGroupOfProps("Группа свойств склада","Число", "Плотность", "2.85");
//
//        page.navigation.pressKeyboardButton(Keys.ESCAPE);
//
//        // Перейти в представление Холста
//        sleep(2000);
//        page.scene
//                .panelMenu
//                .pressMoveToCanvas();
//
//        // Выбрать добавление нового Листа на Холсте
//        page.scene
//                .leftToolMenu
//                .addNewListToCanvas("Произвольный");
//
//        // Добавить новый Лист на Холст
//        page.navigation.mouseClickByOffsetFromViewport(-300,-300,"canvas");
//        page.navigation.mouseClickByOffsetFromViewport(-300,300,"canvas");
//        page.navigation.mouseClickByOffsetFromViewport(400,300,"canvas");
//
//        // Выбрать добавление нового фрагмента на Холсте
//        page.scene
//                .leftToolMenu
//                .addNewFragmentToCanvas();
//
//        // Добавить новый фрагмент на Лист Холста
//        page.navigation.mouseClickByOffsetFromViewport(-250, -250, "canvas");
//        page.navigation.mouseClickByOffsetFromViewport(-250, 0, "canvas");
//        page.navigation.mouseClickByOffsetFromViewport(100, 0, "canvas");
//
//        // Вернуться в представление 3d сцены
//        page.scene
//                .panelMenu
//                .pressMoveToScene();
//
//        // Выбрать инструмент проектирования разреза
//        page.scene
//                .leftToolMenu
//                .createCut();
//
//        // Спроектировать разрез
//        page.navigation.mouseClickByOffsetFromViewport(-150, 150, "canvas");
//        page.navigation.mouseClickByOffsetFromViewport(200, -200, "canvas");
//        sleep(1000);
//        page.navigation.pressKeyboardButton(Keys.SPACE);
//
//        // Перейти в вид с ограничениями
//        page.scene
//                .contextPanel
//                .moveToCutView();
//
//        // Установить вид 2d срез
//        page.scene
//                .viewPanel
//                .set2D_Slice();
//
//        // Вновь перейти в представление Холста
//        page.scene
//                .panelMenu
//                .pressMoveToCanvas();
//
//        // Выбрать инструмент проектирования фрагмента
//        page.scene
//                .leftToolMenu
//                .addNewFragmentToCanvas();
//
//        // Спроектировать фрагмент
//        page.navigation.mouseClickByOffsetFromViewport(-250, 0, "canvas");
//        page.navigation.mouseClickByOffsetFromViewport(-250, 100, "canvas");
//        page.navigation.mouseClickByOffsetFromViewport(100, 100, "canvas");
//
//        // Открыть стили предварительно для заполнения полей таблицы
//        page.scene
//                .rightMenu
//                .openStyles();
//
//        int x = 40, y = 24, k = 14;
//        for (int i = 0; i < 2; i++){
//
//            // Выбрать инструмент проектирования прямоугольника на Холсте
//            page.scene
//                    .leftToolMenu
//                    .createRectangle();
//
//            // Задать стартовую точку
//            page.scene
//                    .parameterizedDesign
//                    .setAxisX(String.valueOf(x))
//                    .setAxisY(String.valueOf(y))
//                    .pressKeyboardButton(Keys.ENTER);
//
//            // Задать угол и длину первой стороны
//            page.scene
//                    .parameterizedDesign
//                    .setAngle("270")
//                    .setLength("3")
//                    .pressKeyboardButton(Keys.ENTER);
//
//            // Задать длину второй стороны
//            page.scene
//                    .parameterizedDesign
//                    .setLength("14")
//                    .pressKeyboardButton(Keys.ENTER)
//                    .pressKeyboardButton(Keys.ESCAPE)
//                    .pressKeyboardButton(Keys.SPACE);
//
//            // Добавляю метку в прямоугольник
//            page.scene
//                    .rightMenu
//                    .getStyles()
//                    .setMarkText("Метка")
//                    .changeMarkVisibility();
//
//            // Логика на смену координат
//            if (i % 2 != 0){
//                y -= 3;
//            }
//            x -= k;
//            k = -k;
//        }
//
//        List<List<String>> data = fileReaderIntoDoubleArray(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, "/scenario_2_8/Coordinates_scenario_2_8.txt");
//
//        for (List<String> row : data) {
//            page.scene
//                    .leftToolMenu
//                    .createRectangle();
//
//            // Задать стартовую точку
//            page.scene
//                    .parameterizedDesign
//                    .setAxisX(row.get(0))
//                    .setAxisY(row.get(1))
//                    .pressKeyboardButton(Keys.ENTER);
//
//            // Задать угол и длину первой стороны
//            page.scene
//                    .parameterizedDesign
//                    .setAngle(row.get(2))
//                    .setLength(row.get(3))
//                    .pressKeyboardButton(Keys.ENTER);
//
//            // Задать длину второй стороны
//            page.scene
//                    .parameterizedDesign
//                    .setLength(row.get(4))
//                    .pressKeyboardButton(Keys.ENTER)
//                    .pressKeyboardButton(Keys.ESCAPE)
//                    .pressKeyboardButton(Keys.SPACE);
//
//            // Добавляю метку в прямоугольник
//            page.scene
//                    .rightMenu
//                    .getStyles()
//                    .setMarkText(row.get(5))
//                    .changeMarkVisibility();
//        }
//
//        // Закрыть панель стилей в правом меню
//        page.scene
//                .rightMenu
//                .closeStyles();
//
//        page.navigation.mouseClickByOffsetFromViewport(-200, -200, "canvas");
//        //imageComparison(ProjectSettings.RESOURCES_PATH_EXP_SCREENSHOTS, "scenario_2_8\\scenario_2_8(2)_exp.png", ProjectSettings.RESOURCES_PATH_CUR_SCREENSHOTS, "scenario_2_8(2)_cur.png");
//
//        // Получить текущее время
//        String currentDateTime1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm"));
//
//        // Далее идет экспорт холста в dxf
//        page.scene
//                .panelMenu
//                .pressFileBut()
//                .pressExportFromCanvas();
//
//        // 2 секунды на размышление
//        sleep(2000);
//
//        // Получить последний экспортируемый файл
//        File exportedFile = page.scene
//                .panelMenu
//                .getLatestDownloadedFile(ProjectSettings.DOWNLOADS_PATH);
//
//        // Получить текущее время
//        String currentDateTime2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm"));
//
//        // Проверка, что файл пришел в хранилище с импользованием проверку на время
//        Assert.assertTrue(exportedFile.getName().contains(currentDateTime2) || exportedFile.getName().contains(currentDateTime1));

    }

}
