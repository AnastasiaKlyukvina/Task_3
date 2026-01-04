package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    @Step("Открыть браузер")
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = Browser.getWebDriver(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://stellarburgers.education-services.ru");
    }

    @AfterEach
    @Step("Закрыть браузер")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}