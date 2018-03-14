package webdriver.test;
// Задание 18. Перенаправьте трафик в прокси-сервер
//        Установите какой-нибудь прокси-сервер, который умеет протоколировать запросы и ответы.
//        Инициализируйте драйвер так, чтобы запросы из браузера отправлялись через этот прокси-сервер,
//        убедитесь, что они там видны.


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumHomeworkTask18 {
    public static WebDriver driver;

    @Test
    public void checkIfProxyIsUsed() {
        String link = "http://google.com";
        initDriverWithProxy(link);
        Assert.assertTrue(driver.getCurrentUrl().equals(link));
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


    public static void initDriverWithProxy(String link) {
        ChromeOptions chromeOptions = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8888");
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability("chromeDriver", true);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        chromeOptions.setCapability("proxy", proxy);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
    }


}
