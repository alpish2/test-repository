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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask9 extends DriverInitialization {

    @Test
    public void forCountriesCheckIfOrderIsAlphabetic() {
        List<WebElement> countries;
        initFFDriver();
        GetPageActions.getCountriesPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        countries = FindElements.findCountriesList(driver);
        compareTitleBubbleSort(countries);


    }

    @Test
    public void forZonesBiggerThanZeroCheckIfOrderIsAlphabetic() throws InterruptedException {
        List<WebElement> countryList, zoneCountList;
        initFFDriver();
        GetPageActions.getCountriesPage(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        countryList = FindElements.findCountriesList(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        zoneCountList = FindElements.findCountriesZonesList(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<String> linkToCountryWithZones = checkIfNumberOfZonesIsNotNull(driver, countryList, zoneCountList);
        System.out.println("INFO: number of links:" + linkToCountryWithZones.size());
        for (int i = 0; i < linkToCountryWithZones.size(); i++) {
            initFFDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            GetPageActions.getPageByLinkAsAdmin(driver, linkToCountryWithZones.get(i));
            List<WebElement> zoneList = FindElements.findZonesInCountriesSectionList(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            compareTitleBubbleSort(zoneList);
        }
    }

    public void compareTitleBubbleSort(List<WebElement> titles) {

        for (int i = 0; i < titles.size() - 1; i++) {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            String firstElement = titles.get(i).getText();
            String secondElement = titles.get(i + 1).getText();
            Assert.assertTrue(secondElement.compareTo(firstElement) > 0);
            System.out.println("first element " + firstElement + "is located before second element" + secondElement);

        }
    }

    public List<String> checkIfNumberOfZonesIsNotNull(WebDriver driver, List<WebElement> countryList, List<WebElement> zoneCountList) throws InterruptedException {
        List<String> linksToZones = new ArrayList<String>();
        for (int i = 0; i < zoneCountList.size(); i++) {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            if (zoneCountList.get(i).getText().compareTo("0") != 0) {
                linksToZones.add(countryList.get(i).getAttribute("href"));
                for (int j = 0; j < linksToZones.size(); j++) {
                    System.out.println("INFO: Countries with zones!=0:  " + linksToZones.get(j));
                }
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            }
        }
        return linksToZones;
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
