import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

// This test class inherits BasicSetupTest class, where the browser is initialized
// browser variable is available here as it's inherited, so you'll have it available at any place
public class SeleniumTestngTest extends BasicSetupTest {

    private WebDriverWait wait;

    @BeforeClass
    public void initWait() {
        wait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }



    //Сценарій 1
    @Test
    public void abTestingPageHasSpecificTextTest()  {
        browser.get("https://the-internet.herokuapp.com/abtest");

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.example h3")));
        String text = title.getText();

        Assert.assertTrue(text.contains("A/B Test Control") || text.contains("A/B Test Variation 1"), "Текст не знайдений");
    }

    //Сценарій 2
    @Test
    public void addRemoveElementsTest() {
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");


        WebElement addElementButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='addElement()']")));

        //додаю 3 Delete та перевіряю їх відображення на сторінці
        for (int i = 0; i < 3; i++) {
            addElementButton.click();
        }

    wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("button.added-manually"), 3));


        List<WebElement> deleteButtons = browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(deleteButtons.size(), 3, "Невірна кількість кнопок Delete");

        //видалення 3 кнопок
        for (WebElement deleteButton : deleteButtons) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        }
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("button.added-manually"), 0));

        //перевірка, що кнопки видалились
        List<WebElement> latestButtons = browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(latestButtons.size(), 0, "Не всі кнопки видалені");
    }

    //Сценарій 3
    @Test
    public void checkBoxesTest(){
        browser.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("input[type='checkbox']"),0));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Не обрано Checkbox");
        }
    }

    //Сценарій 4
    @Test
    public void Option2OfDropdownTest() {
        browser.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown")));

        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 2");


        WebElement selected = select.getFirstSelectedOption();
        Assert.assertEquals(selected.getText(), "Option 2", "Оберіть Option2");
    }

    //Сценарій 5
    @Test
    public void formAuthenticationTest()  {
        browser.get("https://the-internet.herokuapp.com/login");

        //введення імені та паролю
        browser.findElement(By.id("username")).sendKeys("tomsmith");
        browser.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        browser.findElement(By.cssSelector("button.radius")).click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        String actualMessage = message.getText().trim();
        Assert.assertTrue(actualMessage.contains("You logged into a secure area!"),
                "Очікуваний повідомлення не отримано! Отримано: " + actualMessage);

        //logout
        browser.findElement(By.cssSelector("a.button.secondary.radius")).click();

        //перевірка після логауту
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h2"), "Login Page" ));
        String headerText = browser.findElement(By.tagName("h2")).getText();

        Assert.assertTrue(headerText.contains("Login Page"), "Сторінка логіну не відкрита після Logout ");
    }

    //сценарій 6
    @Test
    public void dragAndDropTest() {
        browser.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement columnA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement columnB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));

        String beforeA = columnA.findElement(By.tagName("header")).getText();
        String beforeB = columnB.findElement(By.tagName("header")).getText();


        //симуляція перетаскування
        Actions actions = new Actions(browser);
        actions.dragAndDrop(columnA, columnB).perform();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(columnA.findElement(By.tagName("header")),
                beforeA)));

        String afterA = columnA.findElement(By.tagName("header")).getText();
        Assert.assertNotEquals(beforeA, afterA, "Елементи не помінялись місцями");
    }

    //сценарій 7
    @Test
    public void horizontalSliderTest()  {
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='range']")));
        WebElement value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("range")));

        int steps = 8;
        double expected = steps * 0.5;
        String expectedValue = formatValue(expected);

        for(int i =0; i < steps; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String current = value.getText();
        System.out.println("Поточне значення: " + current);
            Assert.assertEquals(current, expectedValue, " Слайдер повинен показувати " + expectedValue);
    }
    private String formatValue(double val) {
        if(val % 1 == 0) {
            return String.valueOf((int) val);
        }
        return String.valueOf(val);
    }
}



        

























