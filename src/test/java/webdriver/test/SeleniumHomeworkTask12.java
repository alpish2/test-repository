package webdriver.test;
/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
        Для добавления товара нужно открыть меню Catalog,
        в правом верхнем углу нажать кнопку "Add New Product",
        заполнить поля с информацией о товаре и сохранить.
        заполнить только информацию на вкладках General, Information и Prices.
        После сохранения товара нужно убедиться, что он появился в каталоге
*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask12 extends DriverInitialization {

    @Test
    public void addNewProductCatalog() {
        Product product= new Product( "1991-01-01", "1995-01-01", generateRandomNumForName(), "123", "123" ,  "123",  "123", "123", "qwerty");
        String productName;
        initFFDriver();
        GetPageActions.getCatalogPage(driver);
        openNewProductPage(driver);
        productName = generateRandomNumForName();
        LoginActions.createProductFormFilling(driver,product);
        saveForm(driver);
        checkNewProduct(productName, driver);
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

    }

    public void checkNewProduct(String productName, WebDriver driver) {
        //login to catalog
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int temp = 0;
        List<WebElement> productList = driver.findElements(By.cssSelector("table tr td a"));
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getText().equals(productName)) {
                temp++;
                break;
            }
        }
        Assert.assertEquals(temp, 1);
    }

    public String generateRandomNumForName() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(50);
        String productName = "Oleksandra" + randomNumber;
        return productName;
    }

    public void saveForm(WebDriver driver) {
       FindElements.findSaveButton(driver).click();
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
