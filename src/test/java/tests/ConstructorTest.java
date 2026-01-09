package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        boolean bunsActive = driver.findElement(
                By.xpath("//div[contains(@class, 'current')]//span[text()='Булки']")).isDisplayed();

        assertTrue(bunsActive);
    }

    @Test
    @Description("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesSection();

        boolean saucesActive = driver.findElement(
                By.xpath("//div[contains(@class, 'current')]//span[text()='Соусы']")).isDisplayed();

        assertTrue(saucesActive);
    }

    @Test
    @Description("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsSection();

        boolean fillingsActive = driver.findElement(
                By.xpath("//div[contains(@class, 'current')]//span[text()='Начинки']")).isDisplayed();

        assertTrue(fillingsActive);
    }
}