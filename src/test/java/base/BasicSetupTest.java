package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BasicSetupTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void webdriverCommonSetup() {
        // не змінюйте цей метод
        // в даному випадку він використовується, щоб автоматично підтягнути і встановити
        // останню стабільну версію chromedriver (щоб вам не потрібно це робити вручну)
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //очікування
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // В цих методах відбувається ініціалізація браузера перед виконанням тестових методів
    // А також його закриття після виконання усіх тестів в класі
    @AfterClass
    public void tearDown() {

        if (driver!= null) {
            driver.quit();
        }
    }
}
