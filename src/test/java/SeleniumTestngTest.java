import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

// This test class inherits BasicSetupTest class, where the browser is initialized
// browser variable is available here as it's inherited, so you'll have it available at any place
public class SeleniumTestngTest extends BasicSetupTest {


    //Сценарій 1
    @Test
    public void abTestingPageHasSpecificTextTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/abtest");

        WebElement abTestingTaskLink = browser.findElement(By.cssSelector("div.example h3"));
        abTestingTaskLink.click();
        Thread.sleep(3000);
// довгий час очікування, але ніяк не могла зрозуміти чому падає через раз. допоки не побачила наступний текст
        Assert.assertTrue(abTestingTaskLink.getText().contains("A/B Test Control")
                || abTestingTaskLink.getText().contains("A/B Test Variation 1"), "Текст не знайдений");
    }

    //Сценарій 2
    @Test
    public void addRemoveElementsTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");


        WebElement addElement = browser.findElement(By.cssSelector("button[onclick='addElement()']"));

        //додаю 3 Delete та перевіряю їх відображення на сторінці
        for (int i = 0; i < 3; i++)
            addElement.click();
        Thread.sleep(2000);

        List<WebElement> deleteButtons = browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(deleteButtons.size(), 3, "Невірна кількість кнопок Delete");

        //видалення 3 кнопок
        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();
        }
        Thread.sleep(2000);

        //перевірка, що кнопки видалились
        List<WebElement> latestButtons = browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(latestButtons.size(), 0, "Не всі кнопки видалені");
    }

    //Сценарій 3
    @Test
    public void checkBoxesTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = browser.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        Thread.sleep(2000);

        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "Не обрано Checkbox");
        }
    }

    //Сценарій 4
    @Test
    public void Option2OfDropdownTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = browser.findElement(By.id("dropdown"));

        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 2");

        Thread.sleep(2000);

        WebElement option2OfDropdown = select.getFirstSelectedOption();
        Assert.assertEquals(option2OfDropdown.getText(), "Option 2", "Оберіть Option2");
    }

    //Сценарій 5
    @Test
    public void formAuthenticationTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/login");

        //введення імені та паролю
        browser.findElement(By.id("username")).sendKeys("tomsmith");
        browser.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        browser.findElement(By.cssSelector("button.radius")).click();

        WebElement message = browser.findElement(By.id("flash"));
        String actualMessage = message.getText().trim();
        System.out.println(" Actual flash message: ' " + actualMessage + "'");
        Assert.assertTrue(actualMessage.contains("You logged into a secure area!"),
                "Очікуваний повідомлення не отримано! Отримано: " + actualMessage);

        //logout
        browser.findElement(By.cssSelector("a.button.secondary.radius")).click();

        //тут я чекаю поки зявиться "Login Page"
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h2"), "Login Page"));

        String headerText = browser.findElement(By.tagName("h2")).getText();
        System.out.println(" Header after logout: '" + headerText + "'");
        Assert.assertTrue(headerText.contains("Login Page"), "Сторінка логіну не відкрита після Logout ");
    }

    //сценарій 6
    @Test
    public void dragAndDropTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement columnA = browser.findElement(By.id("column-a"));
        WebElement columnB = browser.findElement(By.id("column-b"));

        String beforeA = columnA.findElement(By.tagName("header")).getText();
        String beforeB = columnB.findElement(By.tagName("header")).getText();

        System.out.println((" До переміщення: А = " + beforeA + ", B = " + beforeB));

        //симуляція перетаскування
        Actions actions = new Actions(browser);
        actions.clickAndHold(columnA).moveToElement(columnB).release().perform();
        Thread.sleep(2000);

        String afterA = columnA.findElement(By.tagName("header")).getText();
        String afterB = columnB.findElement(By.tagName("header")).getText();

        System.out.println(" Після переміщення: A = " + afterA + ", B = " + afterB);

        Assert.assertNotEquals(beforeA, afterA, " Елементи не помінялись місцями");
        Assert.assertEquals(afterA, "B", "Елемент А не став на місце В!");
    }

    //сценарій 7
    @Test
    public void horizontalSliderTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");

        WebElement slider = browser.findElement(By.cssSelector("input[type='range']"));
        WebElement value = browser.findElement(By.id("range"));

        String startValue = value.getText();
        System.out.println("Початкове значення слайдера: " + startValue);

        //натискаю слайдер
        for (int i = 0; i < 8; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            Thread.sleep(2000);
        }

        //Присвоюю нове значення
        String newValue = value.getText();

        Assert.assertNotEquals(newValue, startValue, " Слайдер не змінив своє початкове положення ");
    }

}





















