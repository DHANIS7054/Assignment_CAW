import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class TestNG {
    WebDriver driver;
    Properties prop;
    @BeforeTest
    public void start() throws IOException {
        //setting properties and initializing driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();  // for maximizing window
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicit wait

        //getting data from data.properties file in "main/resources" folder
        prop = new Properties();
        prop.load(new FileReader("src/main/resources/data.properties"));
    }
    @AfterTest
    public void close() throws InterruptedException {
        Thread.sleep(5000);
//        driver.close();
    }
    @Test
    public void test() throws IOException, InterruptedException, ParseException {
        //1. invoking url
        String url = prop.getProperty("Url");
        driver.get(url);

        //getting required web-elements from homepage class in "main/java"
        HomePage homePage = new HomePage(driver);


        //2. getting data table button and clicking on it
        homePage.dataTableButton().click();


        //3. inserting given data into text box and click on refresh table button

        //getting textbox and removing already filled data using Ctrl+A and Backspace keys
        homePage.textBox().sendKeys(Keys.CONTROL, "A" , Keys.BACK_SPACE);

        // Reading the testData.json file from "main/resources" and converting it into string at same time
        String filePath = prop.getProperty("testData");
        String jsonFile = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //inserting data from json file in the input text box
        homePage.textBox().sendKeys(jsonFile);

        //finding refresh table button and clicking on it
        homePage.refreshButton().click();


        //4. waiting for table to get populated
        Thread.sleep(2000);


        //5. asserting given data with data present on table

        //calling gettingJsonArrayList Method present in "main/java/JsonArrayList" which extracts data from json file and returns arraylist
        JsonArrayList jsonArrayList = new JsonArrayList();
        ArrayList<ArrayList<String>> expectedArray= jsonArrayList.gettingJsonArrayList(filePath);

        //calling gettingWebTableArrayList Method present in "main/java/WebTableArrayList" which extracts data from web-table and returns arraylist
        WebTableArrayList webTableArrayList = new WebTableArrayList();
        ArrayList<ArrayList<String>> actualArray= webTableArrayList.gettingWebTableArrayList(homePage);

        //asserting actual and expected data
        Assert.assertEquals(actualArray, expectedArray);
    }
}
