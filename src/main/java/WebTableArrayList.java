import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
public class WebTableArrayList {
    public ArrayList<ArrayList<String>> gettingWebTableArrayList(HomePage homePage){
        //EXTRACTING DATA FROM FRESHLY POPULATED WEBTABLE AND PUTTING ITS DATA INTO ARRAYLIST
        ArrayList<ArrayList<String>> actualArray = new ArrayList<>();
        WebElement table = homePage.dynamicTable(); //getting webElement of table from homePage class
        // Now get all the TR elements from the table
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        // And iterate over them, getting the cells
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            ArrayList<String> element = new ArrayList<>();
            for (WebElement cell : cells) {
                element.add(cell.getText());
            }
            if(!element.isEmpty()) {
                actualArray.add(element);
            }
        }
        //returning extracted data from webtable in the form of Arraylist
        return actualArray;
    }
}
 