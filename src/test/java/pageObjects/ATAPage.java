package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ATAPage {
    @FindBy(id="ID_nameField1")
    WebElement firstNumberInput;

    @FindBy(id="ID_nameField2")
    WebElement secondNumberInput;

    @FindBy(xpath="//input[@type=\"radio\"]/following-sibling::label[contains(text(),\"Euclid(-)\")]")
    WebElement radioButtonEuclidMinus;

    @FindBy(xpath="//button[contains(text(),\"Calculate\")]")
    WebElement calculateButton;

    @FindBy(id="ID_nameField3")
    public WebElement resultTextBox;

    public void enterFirstNumber(int firstNumber) {
        firstNumberInput.sendKeys(Integer.toString(firstNumber));
    }

    public void enterSecondNumber(int secondNumber) {
        secondNumberInput.sendKeys(Integer.toString(secondNumber));
    }

    public void clickOnRadioButtonEuclidMinus() {
        radioButtonEuclidMinus.click();
    }

    public void clickOnCalculateButton() {
        calculateButton.click();
    }

    public int calculateEuclidMinusFormula(int a, int b) {
        return (a*a) - 2*(a*b) + (b*b);
    }
}
