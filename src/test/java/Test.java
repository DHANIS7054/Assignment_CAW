import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class Test{
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException, IOException, ParseException {

        //setting properties and initializing driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();


        //1. invoking url
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");


        //2. finding xpath and clicking on data table button
        WebElement dataTableButton = driver.findElement(By.xpath("//div[@class='centered']//details//summary"));
        dataTableButton.click();


        //3. finding xpath of textbox and inserting the given data from json file

        //finding xpath of textbox
        WebElement textbox = driver.findElement(By.id("jsondata"));

        // removing already existing data and giving input on textbox
        textbox.sendKeys(Keys.CONTROL, "A" , Keys.BACK_SPACE);

        //converting json file into string and sending it into input text box
        String filePath = "src/main/resources/testData.json";
        String jsonFile = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        textbox.sendKeys(jsonFile);

        //finding xpath of refresh button and clicking on it
        WebElement refreshButton = driver.findElement(By.id("refreshtable"));
        refreshButton.click();


        //4. waiting for table to get refresh
        Thread.sleep(2000);


        //5. asserting given data with data present on table

        //calling JsonArrayList Method present in "main/java" which return arraylist
        JsonArrayList jsonArrayList = new JsonArrayList();
        ArrayList<ArrayList<String>> expectedArray= jsonArrayList.gettingJsonArrayList("src/main/resources/testData.json");

        //calling WebTableArrayList Method present in "main/java" which return arraylist
        WebTableArrayList webTableArrayList = new WebTableArrayList();
        HomePage homePage = new HomePage(driver);
        ArrayList<ArrayList<String>> actualArray= webTableArrayList.gettingWebTableArrayList(homePage);

        //asserting actual and expected data
        Assert.assertEquals(expectedArray, actualArray);


        //closing window
        Thread.sleep(5000);
//        driver.close();
    }

}
 