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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask9 extends DriverInitialization {

    String countryWithZones;

    @Test
    public void forCountriesCheckIfOrderIsAlphabetic() throws InterruptedException {
        initFFDriver();
        BasicActions.getCountriesPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> countries = BasicActions.getCountriesList(driver);

        for (int i = 0; i < countries.size() - 1; i++) {
            String firstElement = countries.get(i).getText();
            String secondElement = countries.get(i + 1).getText();
            Assert.assertTrue(secondElement.compareTo(firstElement) > 0);

        }
    }

    @Test
    public void forZonesBiggerThanZeroCheckIfOrderIsAlphabetic() throws InterruptedException {
        initFFDriver();
        BasicActions.getCountriesPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> countryList = BasicActions.getCountriesList(driver);
        List<WebElement> zoneCountList = BasicActions.getCountriesZonesList(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkIfNumberOfZonesIsNotNull(driver, countryList, zoneCountList);

    }

    public void checkIfNumberOfZonesIsNotNull(WebDriver driver, List<WebElement> countryList, List<WebElement> zoneCountList) throws InterruptedException {
        for (int i = 0; i < zoneCountList.size(); i++) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (zoneCountList.get(i).getText().compareTo("0") != 0) {
                countryWithZones = countryList.get(i).getAttribute("href");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                checkOrder(countryWithZones);
            }


        }
    }

    public void checkOrder(String link) throws InterruptedException {
        initFFDriver();
        BasicActions.getPageByLinkAsAdmin(driver, link);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> zoneList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/input"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
