import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    public WebDriver driver;

    // creating constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // Writing xpath of each element present in the given webpage
    private By dataTableButton = By.xpath("//div[@class='centered']//details//summary");
    private By textBox = By.xpath("//*[@id='jsondata']");
    private By refreshButton = By.xpath("//*[@id='refreshtable']");
    private By table = By.xpath("//*[@id='dynamictable']");


    // Returning WebElements using above xpaths
    public WebElement dataTableButton(){
        return driver.findElement(dataTableButton);
    }
    public WebElement textBox(){
        return driver.findElement(textBox);
    }
    public WebElement refreshButton(){
        return driver.findElement(refreshButton);
    }
    public WebElement dynamicTable(){
        return driver.findElement(table);
    }

}
