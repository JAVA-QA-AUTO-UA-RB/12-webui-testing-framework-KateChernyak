package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {
    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/drag_and_drop";

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    public DragAndDropPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void dragAtoB() {
        new Actions(driver).dragAndDrop(columnA, columnB).perform();
    }

    public String getColumnAText() {
        return columnA.getText().trim();
    }

    public String getColumnBText() {
        return columnB.getText().trim();
    }
}
