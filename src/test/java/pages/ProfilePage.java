package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    // Локаторы в личном кабинете
    private By profileText = By.xpath("//a[text()='Профиль']");
    private By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProfileTextDisplayed() {
        try {
            return driver.findElement(profileText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для выхода из аккаунта
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
