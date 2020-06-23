package pageObjects;

import Constants.UiConstants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QAAgilityTechnologiesHomePage {

    @FindBy(xpath="//nav[@id=\"site-navigation\"]//img[@title=\""+ UiConstants.qaAgilityTechTitleName+"\"]")
    public WebElement qaAgileTechLogo;

    @FindBy(xpath="//a[contains(@href,\""+UiConstants.twitterTitle+"\")]")
    public WebElement twitterButton;

    @FindBy(xpath="//div[@class=\"copyright-bar\"]")
    public WebElement copyRightBar;

    public void findSizeOfQAAgilityLogo() {
        Dimension size = qaAgileTechLogo.getSize();
        System.out.println("Width of the element is : "+size.width);
        System.out.println("Height of the element is : "+size.height);
    }
}
