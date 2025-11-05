package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckboxesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String URL = "https://the-internet.herokuapp.com/checkboxes";

    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void selectAll() {
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) checkbox.click();
        }
    }

    public boolean areAllSelected() {
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
        return checkboxes.stream().allMatch(WebElement :: isSelected);
    }
}
