package webdriver.test;

    /*
    Task 7
    Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
            1) входит в панель администратора http://localhost/litecart/admin
            2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
            3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
    */

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleniumHomeworkTask7 {

    public static WebDriver driver;

    public void loginToAdminAndGetPage(String link) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();

    }

    @Test
    public void goTroughTheMenuAndCheckTitle() {
        loginToAdminAndGetPage("http://localhost:8080/litecart/admin/");
        List<WebElement> webElementListToGetSize = driver.findElements(By.cssSelector("li#app- a"));
        int webElementListSize = webElementListToGetSize.size();
        System.out.println("initial SIZE:" + webElementListSize);

        int diff;

        for (int i = 0; i < webElementListSize; i++) {
            List<WebElement> webElementList = driver.findElements(By.cssSelector("li#app- a"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webElementList.get(i).click();
            List<WebElement> webElementListChanged = driver.findElements(By.cssSelector("li#app- a"));
            diff = webElementListChanged.size() - webElementListSize;


            if (diff != 0) {

                for (int j = 1; j <= diff; j++) {
                    List<WebElement> webElementListChanged2 = driver.findElements(By.cssSelector("li#app- a"));
                    webElementListChanged2.get(i + j).click();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Assert.assertTrue("h1 element exists on the current page: ", driver.findElement(By.cssSelector("h1")).isDisplayed());

                }
                driver.get("http://localhost:8080/litecart/admin/");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}