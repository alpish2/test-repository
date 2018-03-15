package webdriver.test;

// Сделайте сценарий, который проверяет, что ссылки на странице Countries открываются в новом окне.
//        а) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой --
//         кликнуть по ссылке, потом переключиться в это окно, закрыть его.


import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask14 extends DriverInitialization {

    @Test
    public void checkIfNewWindowsOpen() {
        List<WebElement> linksList;
        initFFDriver();
        GetPageActions.getCountriesPage(driver);
        openCountryPage(driver);
        linksList = FindElements.findListOfHelpButtons(driver);
        String startWindow = driver.getWindowHandle();
        SwitchToWindowAndCloseIt(driver, linksList, startWindow);

    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    public void openCountryPage(WebDriver driver) {
        List<WebElement> countryList;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        countryList = FindElements.findListOfEditButtons(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        countryList.get(LoginActions.generateRandomNum()).click();
    }

    public void SwitchToWindowAndCloseIt(WebDriver driver, List<WebElement> linksList, String startWindow) {
        for (int i = 0; i < linksList.size(); i++) {
            linksList.get(i).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            ArrayList windows = new ArrayList<String>(driver.getWindowHandles());
            for (int j = 0; j < windows.size(); j++) {
                if (!windows.get(j).toString().equals(startWindow)) {
                    driver.switchTo().window(windows.get(j).toString());
                    driver.close();
                }
                driver.switchTo().window(startWindow);
            }

        }
    }


}






