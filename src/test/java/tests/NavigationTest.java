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

public class NavigationTest extends BaseTest {

    private String token;

    @BeforeEach
    public void createAndLoginUser() {
        String testEmail = "navigator1" + System.currentTimeMillis() + "@gmail.com";
        String testName = "Михаил";
        String testPassword = "Testnav234";
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
    @Description("Переход в личный кабинет после авторизации")
    public void testNavigateToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        boolean profileVisible = profilePage.isProfileTextDisplayed();

        assertTrue(profileVisible, "После перехода в ЛК должен отображаться раздел 'Профиль'");
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void testNavigateFromProfileToConstructor() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        mainPage.clickConstructorButton();
        boolean orderButtonDisplayed = driver.findElement(
                By.xpath("//button[contains(text(), 'Оформить заказ')]")).isDisplayed();

        assertTrue(orderButtonDisplayed, "После клика на 'Конструктор' должны вернуться на главную");
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на логотип")
    public void testNavigateFromProfileToConstructorViaLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        mainPage.clickLogo();
        boolean orderButtonDisplayed = driver.findElement(
                By.xpath("//button[contains(text(), 'Оформить заказ')]")).isDisplayed();

        assertTrue(orderButtonDisplayed, "После клика на логотип должны вернуться на главную");
    }
}
