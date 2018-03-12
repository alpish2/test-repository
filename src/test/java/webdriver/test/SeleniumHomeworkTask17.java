package webdriver.test;
//Задание 17. Проверьте отсутствие сообщений в логе браузера
//        Сценарий должен состоять из следующих частей:
//        1) зайти в админку
//        2) открыть каталог, категорию, которая содержит товары
//        3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class SeleniumHomeworkTask17 extends DriverInitialization{

    @Test
    public void checkIfLogsMessageExists() {
        initChromeDriver();
        GetPageActions.getPageByLinkAsAdmin(driver, "http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        openAndCloseEachProductPage(driver);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            if (!entry.getMessage().equals("")) System.out.println("browser message: " + entry.getMessage());
            Assert.assertTrue(entry.getMessage().equals(""));
        }
    }

    public void openAndCloseEachProductPage(WebDriver driver) {
        List<WebElement> productList = driver.findElements(By.xpath("//*[@id=\"main\"]/form/table/tbody//td[3]/a"));
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getText().equals("Subcategory")) {
                productList.get(i).click();
                break;
            }
        }
        productList = driver.findElements(By.xpath("//*[@id=\"main\"]/form/table/tbody//td[3]/a"));
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).click();
            driver.findElement(By.name("cancel")).click();
            productList = driver.findElements(By.xpath("//*[@id=\"main\"]/form/table/tbody//td[3]/a"));
        }
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
