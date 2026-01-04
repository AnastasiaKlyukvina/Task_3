package tests;

import api.UserApiClient;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    private String token;

    @BeforeEach
    public void createAndLoginUser() {

        String testEmail = "angelina" + System.currentTimeMillis() + "@test.com";
        String testPassword = "LogoutPass111";
        String testName = "Ангелина";
        token = UserApiClient.createUser(testEmail, testPassword, testName);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);
    }

    @AfterEach
    public void deleteUser() {
        if (token != null) {
            UserApiClient.deleteUser(token);
        }
    }

    @Test
    @Description("Выход из аккаунта через личный кабинет")
    public void testLogout() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();

        boolean loginHeaderDisplayed = driver.findElement(
                By.xpath("//h2[text()='Вход']")).isDisplayed();

        assertTrue(loginHeaderDisplayed, "После выхода должна отображаться страница входа");
    }
}