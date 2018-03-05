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
import org.openqa.selenium.WebElement;

import java.util.List;


public class SeleniumHomeworkTask7  extends DriverInitialization{

    @Test
    public void goTroughTheMenuAndCheckTitle() {
        initFFDriver();
        BasicActions.getAdminPage(driver);
        BasicActions.loginAsAdmin(driver);

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
                BasicActions.getAdminPage(driver);
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