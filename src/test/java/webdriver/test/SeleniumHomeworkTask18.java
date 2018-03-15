package webdriver.test;

// Перенаправьте трафик в прокси-сервер
//        Инициализируйте драйвер так, чтобы запросы из браузера отправлялись через этот прокси-сервер.

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
    public static String localhostAddress = "localhost:" + Data.port;

    @Test
    public void checkIfProxyIsUsed() {

        initDriverWithProxy(Data.linkToGoogle);
        System.out.println("current -  " + driver.getCurrentUrl() + "   link used- " + Data.linkToGoogle);
        Assert.assertTrue(driver.getCurrentUrl().equals(Data.linkToGoogle));

    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    public static void initDriverWithProxy(String link) {
        ChromeOptions chromeOptions = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(localhostAddress);
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
