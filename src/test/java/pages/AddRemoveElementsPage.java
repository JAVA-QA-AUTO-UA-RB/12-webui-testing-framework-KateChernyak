package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String URL = "https://the-internet.herokuapp.com/add_remove_elements/";

    @FindBy(css = "button[onclick='addElement()']")
    private WebElement addButton;

    //@FindBy(css = "button.added-manually")
    //private List<WebElement> deleteButtons;

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void addElements(int count) {
        for (int i = 0; i < count; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        }
    }
//тут я винесла селектор окремо, бо тест час від часу падав. а в такому варіанті працює стабільно
    public int getDeleteButtonsCount() {
        List<WebElement> buttons = driver.findElements(By.cssSelector("button.added-manually"));
        if(!buttons.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
        }
        return buttons.size();
    }

    public void removeAllElements() {
        while(true) {
            List<WebElement> buttons = driver.findElements(By.cssSelector("button.added-manually"));
        if(buttons.isEmpty()) break;
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0))).click();
        }
    }
}
