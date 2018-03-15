package webdriver.test;
/*
Сделайте сценарий для добавления нового товара ( меню Catalog) в учебном приложении litecart (в админке).
        После сохранения товара нужно убедиться, что он появился в каталоге
*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask12 extends DriverInitialization {

    @Test
    public void addNewProductCatalog() {
        String productName = "name" + LoginActions.generateRandomNum();
        Product product = new Product(Data.dateFrom,
                Data.dateTo,
                productName,
                Data.code,
                Data.sku,
                Data.mpn,
                Data.gtin,
                Data.taric,
                Data.keywords);
        initFFDriver();
        GetPageActions.getCatalogPage(driver);
        openNewProductPage(driver);
        LoginActions.createProductFormFilling(driver, product);
        saveForm(driver);
        checkThatNewProductAppearedInCatalog(productName, driver);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    public void openNewProductPage(WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> buttonList = FindElements.findNewProductPage(driver);
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getText().equals("Add New Product")) {
                buttonList.get(i).click();
                break;
            }
        }
        System.out.println("INFO: New Product Page should be opened now");

    }

    public void saveForm(WebDriver driver) {
        FindElements.findSaveButton(driver).click();
        System.out.println("New product should be created now");
    }

    public void checkThatNewProductAppearedInCatalog(String productName, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int temp = 0;
        List<WebElement> productList = driver.findElements(By.cssSelector("table tr td a"));
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getText().equals(productName)) {
                temp++;
                break;
            }
        }
        Assert.assertEquals("New product is not appearing in catalog", 1, temp);
        System.out.println("INFO: New product appears on the Catalog page");
    }

}
