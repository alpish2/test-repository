package webdriver.test;

 /* Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
            1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
    а) проверить, что страны расположены в алфавитном порядке
    б) для тех стран, у которых количество зон отлично от нуля --
    открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
            2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
    зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask9 {


    public static WebDriver driver;
    String countryWithZones;


    public void loginToAdminAndGetPage(String link) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();

    }

    @Test
    public void forCountriesCheckIfOrderIsAlphabetic() {
        loginToAdminAndGetPage("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elementList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[5]//a"));

        System.out.println("size1=" + elementList.size());

        for (int i = 0; i < elementList.size() - 1; i++) {
            String firstElement = elementList.get(i).getText();
            String secondElement = elementList.get(i + 1).getText();
            Assert.assertTrue(secondElement.compareTo(firstElement) > 0);

        }
    }

    @Test
    public void forZonesBiggerThanZeroCheckIfOrderIsAlphabetic() {
        loginToAdminAndGetPage("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> countryList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[5]//a"));
        List<WebElement> zoneCountList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[6]"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("size1=" + zoneCountList.size());

        for (int i = 0; i < zoneCountList.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (zoneCountList.get(i).getText().compareTo("0") != 0) {

                countryWithZones = countryList.get(i).getAttribute("href");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkOrder();
            }


        }

    }


    public void checkOrder() {
        loginToAdminAndGetPage(countryWithZones);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> zoneList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/input"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("size23:" + zoneList.size());
        for (int i = 0; i < zoneList.size() - 1; i++) {
            String firstElement = zoneList.get(i).getAttribute("value");
            String secondElement = zoneList.get(i + 1).getAttribute("value");
            Assert.assertTrue(secondElement.compareTo(firstElement) > 0);
        }
        driver.close();
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
