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
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask12 extends DriverInitialization {


    @Test
    public void addNewProductCatalog() {
        initFFDriver();
        BasicActions.getCatalogPage(driver);
        openNewProductPage(driver);
        String productName = generateRandomNumForName();
        fillTheForm(driver, productName);
        saveForm(driver);
        checkNewProduct(productName, driver);
    }

    public void openNewProductPage(WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> buttonList = driver.findElements(By.cssSelector("ul.list-inline.pull-right a"));
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getText().equals("Add New Product")) {
                buttonList.get(i).click();
                break;
            }
        }

    }

    public void fillTheForm(WebDriver driver, String productName) {
        //select
        List<WebElement> selectList = driver.findElements(By.cssSelector("label.btn.btn-default"));
        for (int i = 0; i < selectList.size(); i++) {
            if (selectList.get(i).getText().equals("Enabled")) selectList.get(i).click();
        }

        //checkbox
        List<WebElement> checkboxList = driver.findElements(By.cssSelector("div.checkbox label"));
        for (int i = 0; i < checkboxList.size(); i++) {
            if (checkboxList.get(i).isSelected()) checkboxList.get(i).clear();
            if (checkboxList.get(i).getText().equals("Male")) checkboxList.get(i).click();
            if (checkboxList.get(i).getText().equals("Root")) checkboxList.get(i).click();
        }

        //date
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("1991-01-01");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("1995-01-01");


        //textfields
        driver.findElement(By.cssSelector("input[name='name[en]']")).clear();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name='code']")).clear();
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[name='sku']")).clear();
        driver.findElement(By.cssSelector("input[name='sku']")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[name='mpn']")).clear();
        driver.findElement(By.cssSelector("input[name='mpn']")).sendKeys("qwertt");
        driver.findElement(By.cssSelector("input[name='gtin']")).clear();
        driver.findElement(By.cssSelector("input[name='gtin']")).sendKeys("q1w2e3r36");
        driver.findElement(By.cssSelector("input[name='taric']")).clear();
        driver.findElement(By.cssSelector("input[name='taric']")).sendKeys("1!!@qwt");
        driver.findElement(By.cssSelector("input[name='keywords']")).clear();
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("QWERTY");

        //dropdown
        WebElement dropdown = driver.findElement(By.cssSelector("select[name='manufacturer_id']"));
        Select oSelect = new Select(dropdown);
        oSelect.selectByIndex(1);


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
        driver.findElement(By.cssSelector("button[name='save']")).click();
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
