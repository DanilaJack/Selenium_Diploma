package ui.pages.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TableView extends BasePage {

    private final SelenideElement backToSceneModeBut = $x("//button/div[text()='Вернуться на сцену']");
    private final SelenideElement table = $x("//article[contains(@class, 'TableContainer')]");
    private final SelenideElement exportBtn = $x("//div[contains(@class, 'table-buttons')]/button");


    public TableView(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие нужной вкладки мультиобъекта в табличном представление")
    public TableView  clickOnNeededTabOfMultiobject(String entity){
        $x("//div[contains(@class, 'ant-radio-group')]//span[text()='"+entity+"']").shouldBe(visible, Duration.ofSeconds(20)).click();
        return this;
    }

    @Step("Воспользоваться глобальным поиском в табличном представление")
    public TableView globalSearch(String value){
        $x("//div[contains(@class, 'GlobalFilterComponent')]/button").shouldBe(interactable, Duration.ofSeconds(2)).click();
        waitLoading(1);
        $x("//div[contains(@class, 'GlobalFilterComponent')]//input").shouldBe(interactable, Duration.ofSeconds(2)).sendKeys(value);
        pressKeyboardButton(Keys.ENTER);
        waitLoading(1);
        return this;
    }

    @Step("Редактировать ячейку таблицы по имени ГО")
    public TableView editCellInTable(String nameGO, String column, String value){
        SelenideElement selector = $x("//td[@data-type='Имя ГО']/span[contains(text(),'"+nameGO+"')]/../../td[@data-type='"+column+"']");

        actions().moveToElement(selector).doubleClick().perform();
        waitLoading(1);
        pressKeyboardButton(Keys.CONTROL, "a");
        pressKeyboardButton(Keys.BACK_SPACE);

        Selenide.actions()
                .sendKeys(value).perform();
        pressKeyboardButton(Keys.ENTER);
        return this;
    }

    @Step("Возвращение в режим сцены из табличного представления")
    public TableView clickOnBackToSceneModeBut(){
        backToSceneModeBut.shouldBe(visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Получение общее количество элементов во всех геометриях в МО")
    public int getFullAmountOfElements(){

        int current_amount = 0;
        List<String> current_numbers = new ArrayList<>();

        ElementsCollection accessed_rows = $$x("//tbody//tr/td[@data-type='row_select']//div[@data-value='row-number']");

        current_numbers = accessed_rows.stream().map(SelenideElement::getText).toList();
        current_amount = current_numbers.size();
        waitLoading(4);

        do{
            executeJavaScript("arguments[0].scrollTop += 500;", table);
            sleep(1000);

            int finalCurrent_amount = current_amount;

            current_numbers = accessed_rows.stream()
                    .map(SelenideElement::getText)
                    .filter(x -> Integer.parseInt(x) > finalCurrent_amount)
                    .toList();

            current_amount += current_numbers.size();
        } while (!current_numbers.isEmpty());

        return current_amount;
    }

    @Step("Экспорт данных из таблицы в xlsx")
    public TableView exportDataFromTable(){
        exportBtn.shouldBe(Condition.enabled, Duration.ofSeconds(10)).click();
        waitLoading(2);
        return this;
    }
}
