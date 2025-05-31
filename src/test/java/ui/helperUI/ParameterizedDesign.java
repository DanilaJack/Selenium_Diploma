package ui.helperUI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ParameterizedDesign extends BasePage {

    public ParameterizedDesign(WebDriver driver) {
        super(driver);
    }

    private final SelenideElement axisX = $(By.id("ParameterizationInput_x"));
    private final SelenideElement axisY = $(By.id("ParameterizationInput_y"));
    private final SelenideElement axisZ = $(By.id("ParameterizationInput_z"));

    private final SelenideElement lengthInput = $(By.id("ParameterizationInput_length"));
    private final SelenideElement azimuthInput = $(By.id("ParameterizationInput_azimuth"));
    private final SelenideElement angleInput = $(By.id("ParameterizationInput_angle"));

    @Step("Установка значения координаты X")
    public ParameterizedDesign setAxisX(String axisX) throws InterruptedException {
        this.axisX.shouldBe(Condition.visible).sendKeys(axisX);
        return this;
    }

    @Step("Установка значения координаты Y")
    public ParameterizedDesign setAxisY(String axisY) throws InterruptedException {
        this.axisY.shouldBe(Condition.visible).sendKeys(axisY);
        return this;
    }

    @Step("Установка значения координаты Z")
    public ParameterizedDesign setAxisZ(String axisZ) throws InterruptedException {
        this.axisZ.shouldBe(Condition.visible).sendKeys(axisZ);
        return this;
    }

    @Step("Указать угол для параметра трансформации")
    public ParameterizedDesign setAngle(String angle){
        waitLoading(1);
        angleInput.shouldBe(Condition.visible).sendKeys(angle);
        return this;
    }

    @Step("Указать азимут для параметра трансформации")
    public ParameterizedDesign setAzimuth(String azimuth){
        waitLoading(1);
        azimuthInput.shouldBe(Condition.visible).sendKeys(azimuth);
        return this;
    }

    @Step("Указать длину для параметра трансформации")
    public ParameterizedDesign setLength(String length){
        waitLoading(1);
        lengthInput.shouldBe(Condition.visible).sendKeys(length);
        return this;
    }

}
