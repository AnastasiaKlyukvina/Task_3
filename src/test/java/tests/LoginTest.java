package tests;

import api.UserApiClient;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    private String token;
    private String testEmail;
    private String testPassword = "password123";
    private String testName = "Тест Юзер";

    @BeforeEach
    public void createUser() {
        testEmail = "test" + System.currentTimeMillis() + "@mail.ru";
        token = UserApiClient.createUser(testEmail, testPassword, testName);
    }

    @AfterEach
    public void deleteUser() {
        if (token != null) {
            UserApiClient.deleteUser(token);
        }
    }

    @Test
    @Description("Вход через кнопку 'Войти в аккаунт' на главной")
    public void testLoginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        boolean hasOrderButton = driver.findElement(
                By.xpath("//button[contains(text(), 'Оформить заказ')]")).isDisplayed();

        assertTrue(hasOrderButton);
    }

    @Test
    @Description("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, testPassword);

        boolean hasOrderButton = driver.findElement(
                By.xpath("//button[contains(text(), 'Оформить заказ')]")).isDisplayed();

        assertTrue(hasOrderButton);
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        boolean hasLoginHeader = driver.findElement(
                By.xpath("//h2[text()='Вход']")).isDisplayed();

        assertTrue(hasLoginHeader);
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        boolean hasLoginHeader = driver.findElement(
                By.xpath("//h2[text()='Вход']")).isDisplayed();

        assertTrue(hasLoginHeader);
    }
}