package tests;

import api.UserApiClient;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest extends BaseTest {

    @Test
    @Description("Успешная регистрация")
    public void testSuccessfulRegistration() {
        String name = "Иван Васильевич";
        String email = "ivan604" + System.currentTimeMillis() + "@yandex.ru";
        String password = "123456";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);

        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {

        }

        boolean onLoginPage = driver.getCurrentUrl().contains("login");

        assertTrue(onLoginPage);

        try {
            String token = UserApiClient.createUser(email, password, name);
            if (token != null && !token.isEmpty()) {
                UserApiClient.deleteUser(token);
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя: " + e.getMessage());
        }
    }

    @Test
    @Description("Ошибка при коротком пароле")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Анна", "anna@test.ru", "123");

        try {
            Thread.sleep(2000);
        } catch (Exception ignored) {
        }

        boolean hasError = registerPage.isPasswordErrorDisplayed();

        assertTrue(hasError);
    }
}