import Constants.UiConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.ATAPage;
import pageObjects.GoogleMaps;
import pageObjects.QAAgilityTechnologiesHomePage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeleniumTest1 {
    public WebDriver wd;
    public WebDriverWait wait;
    @BeforeMethod
    public void initSetup() throws IOException {
        wd = WebDriverManager.loadBrowser();
        wd.manage().window().maximize();
        wd.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd,10);
    }

    @Test(dataProvider = "stadiumNames",enabled=true)
    public void searchStadiumInMaps(String stadium) throws IOException {
        GoogleMaps mapsPage = PageFactory.initElements(wd,GoogleMaps.class);
        System.out.println("Open google maps page");
        wd.get(PropertyManager.googleMapsUrl);
        System.out.println("Stadium to search in the Google Maps : "+ stadium);
        mapsPage.enterTextInSearchBoxAndClickOnSearch(stadium);
        //wait until left frame contains the stadium name text

        wait.until(ExpectedConditions.presenceOfElementLocated
                (mapsPage.getSearchHeaderTextInLeftFrame(stadium)));
        // Take screenshot of the google maps page
        CommonUtils.takeScreenShot(wd,stadium);
        // verify the text is present in the search maps page
        Assert.assertTrue(mapsPage.verifyTextIsPresent(wd,stadium)
                ,"Cannot find text "+stadium+" in the left frame");
        // verify title of the google maps page
        System.out.println("page title is : "+ wd.getTitle());
        Assert.assertTrue(wd.getTitle().contains(stadium+" - Google Maps"),"Title is mismatched");
        // print ratings and no of reviews
        System.out.println("No of ratings : "+ mapsPage.ratings.getText());
        System.out.println("No of Reviews : "+ mapsPage.noOfReviews.getText());
    }

    @Test(enabled=true)
    public void verifyQAAgilityPage() {
        System.out.println("Open QAAgility Technologies page");
        wd.get(PropertyManager.qaAgilityPage);
        QAAgilityTechnologiesHomePage qaPage = PageFactory.initElements(wd,QAAgilityTechnologiesHomePage.class);
        System.out.println("Check the title of the Page");
        Assert.assertTrue(wd.getTitle().contains("QAAgility"),"Title does not contain the text as QAAgility");

        System.out.println("get the Logo size");
        qaPage.findSizeOfQAAgilityLogo();

        System.out.println("Check the Twitter button is present in the page");
        Assert.assertTrue(qaPage.twitterButton.isEnabled(),"Twitter Button is enabled in QAAgility Page");

        System.out.println("Check if the copyRight Bar text contains the expected Value");
        String copyRightText = qaPage.copyRightBar.getText();
        Assert.assertEquals(copyRightText, UiConstants.qaAgilityTechPageFooterCopyRightText);
    }

    @Test(dataProvider = "numbersCombination")
    public void calculateATAPage(int firstNo, int secondNo) {
        /*
        automated this in Chrome browser since Firefox is not installed in my company laptop.
         */
        ATAPage ataPage = PageFactory.initElements(wd,ATAPage.class);
        System.out.println("Open ATA Page");
        wd.get(PropertyManager.ataPage);
        System.out.println("First Number : "+firstNo+":: Second no : "+secondNo);
        ataPage.enterFirstNumber(firstNo);
        ataPage.enterSecondNumber(secondNo);
        System.out.println("Click on Euclid(-) option");
        ataPage.clickOnRadioButtonEuclidMinus();
        ataPage.clickOnCalculateButton();
        String actualValue = ataPage.resultTextBox.getAttribute("value");
        System.out.println("Result value is : "+ actualValue);
        int actualResult = Integer.valueOf(actualValue);
        int expectedResult = ataPage.calculateEuclidMinusFormula(firstNo,secondNo);
        System.out.println("Expected value is :"+expectedResult);
        Assert.assertEquals(actualResult,expectedResult);
    }


    @AfterMethod
    public void endSetup() {
        wd.close();
    }

    @DataProvider
    public Object[][] stadiumNames() {
        return new Object[][] {
                {"Wankhede Stadium"},
               // {"Brabourne Stadium"}
        };
    }

    @DataProvider
    public Object[][] numbersCombination() {
        return new Object[][] {
                {7,4}
        };
    }
}
