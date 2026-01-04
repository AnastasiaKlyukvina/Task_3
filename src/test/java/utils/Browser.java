package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
    public static WebDriver getWebDriver(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":
                return getChromeDriver();

            case "yandex":
                return getYandexDriver();

            default:
                System.out.println("Неизвестный браузер: " + browserName +
                        ". Использую Chrome.");
                return getChromeDriver();
        }
    }

    private static WebDriver getChromeDriver() {
        System.out.println("Создаю драйвер для Google Chrome");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }

    private static WebDriver getYandexDriver() {
        System.out.println("Создаю драйвер для Яндекс.Браузера");

        String yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);  // Указываем путь к Яндекс.Браузеру
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }
}
