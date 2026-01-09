package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    // Локатор ссылки "Войти"
    private By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для перехода на страницу входа
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
