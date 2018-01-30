package webdriver.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumHomeworkTask3Task5 {


    @Test
    public void task3_loginToAdminPageWithIE() {
        System.setProperty("webdriver.ie.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).submit();
        driver.quit();
    }

    @Test
    public void task5_loginToAdminPageWithFFesr() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(FirefoxDriver.MARIONETTE, false);
        WebDriver driver = new FirefoxDriver(options);
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).submit();
        driver.quit();
    }

}
