package webdriver.test;
// Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
//        1) зайти в админку
//        2) открыть пункт меню Countries
//        3) открыть на редактирование какую-нибудь страну или начать создание новой
//        4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой --
//         кликнуть по ссылке, потом переключиться в это окно, закрыть его.


import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask14 extends DriverInitialization {

    @Test
    public void checkIfNewWindowsOpen() {
        initFFDriver();
        BasicActions.getCountriesPage(driver);
        openCountryPage(driver);
        List<WebElement> linksList = driver.findElements(By.cssSelector("label a[target]"));
        String startWindow = driver.getWindowHandle();
        SwitchToWindowAndCloseIt(driver, linksList, startWindow);

    }

    public void openCountryPage(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        List<WebElement> countryList = driver.findElements(By.cssSelector("a[title='Edit']"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        countryList.get(generateRandomNum()).click();

    }

    public void SwitchToWindowAndCloseIt(WebDriver driver, List<WebElement> linksList, String startWindow) {
        for (int i = 0; i < linksList.size(); i++) {
            linksList.get(i).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public int generateRandomNum() {
        Random rand = new Random();
        int randomNum = rand.nextInt(242);

        return randomNum;
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}






