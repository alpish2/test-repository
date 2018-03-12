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

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleniumHomeworkTask7 extends DriverInitialization {

    @Test
    public void goTroughTheMenuAndCheckIfTitleExists() {
        int menuListSize, diff = 0;
        List<WebElement> menuList;
        initFFDriver();
        GetPageActions.getAdminPage(driver);
        LoginActions.loginAsAdminFromLoginPage(driver);

        menuList = FindElements.findMenuList(driver);
        menuListSize = menuList.size();

        for (int i = 0; i < menuListSize; i++) {
            menuList = FindElements.findMenuList(driver);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            menuList.get(i).click();
            menuList = FindElements.findMenuList(driver);
            diff = menuList.size() - menuListSize;
            if (diff != 0) {

                checkIfTitleExistsOnThePage(driver, diff, menuList, i);
                GetPageActions.getAdminPage(driver);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        }

    }


    public void checkIfTitleExistsOnThePage(WebDriver driver, int diff, List<WebElement> menuList, int i) {

        for (int j = 1; j <= diff; j++) {
            menuList = FindElements.findMenuList(driver);
            menuList.get(i + j).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            Assert.assertTrue("h1 element exists on the current page: ", driver.findElement(By.cssSelector("h1")).isDisplayed());

        }
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}