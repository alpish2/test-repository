package webdriver.test;
/*
    1. Сделайте сценарий для логина в панель администрирования учебного приложения --
       http://localhost/litecart/admin/

    2.   Попробуйте запустить разработанный ранее сценарий логина во всех основных браузерах,
    доступных для вашей операционной системы.
    */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumHomeworkTask3Task5 {

    @Test
    public void loginToAdminPageWithIE() {
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
    public void loginToAdminPageWithFFesr() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).submit();
        driver.quit();
    }

}
