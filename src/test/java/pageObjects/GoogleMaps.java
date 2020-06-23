package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMaps {

    @FindBy(xpath="//span[@class=\"section-star-display\"]")
    public WebElement ratings;

    @FindBy(xpath="//span[@class=\"section-rating-term\"]//button[@jsaction=\"pane.rating.moreReviews\"]")
    public WebElement noOfReviews;

    @FindBy(xpath="//input[@id=\"searchboxinput\"]")
    public WebElement searchBox;

    @FindBy(xpath="//button[@id=\"searchbox-searchbutton\"]")
    public WebElement searchButton;

    public void enterTextInSearchBoxAndClickOnSearch(String text) {
        searchBox.sendKeys(text);
        searchButton.click();
        System.out.println("Entered the text in search box and clicked on Search button");
    }

    public boolean verifyTextIsPresent(WebDriver wd , String text) {
        WebElement textInLeftFrame = wd.findElement(By.xpath("//span[contains(text(),\""+text+"\")]"));
        return textInLeftFrame.isDisplayed();
    }

    public By getSearchHeaderTextInLeftFrame(String text) {
        return By.xpath("//span[contains(text(),\""+text+"\")]");
    }
}
