package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Все локаторы на одной странице
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By constructorButton = By.xpath("//p[text()='Конструктор']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");

    // Локаторы для разделов конструктора
    private By bunsSection = By.xpath("//span[text()='Булки']");
    private By saucesSection = By.xpath("//span[text()='Соусы']");
    private By fillingsSection = By.xpath("//span[text()='Начинки']");

    // Локатор для активного раздела
    private By activeSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для кнопок
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogo() {
        driver.findElement(logo).click();
    }

    // Методы для разделов конструктора
    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
    }

    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    // Метод для проверки активного раздела
    public String getActiveSectionText() {
        return driver.findElement(activeSection).getText();
    }
}
