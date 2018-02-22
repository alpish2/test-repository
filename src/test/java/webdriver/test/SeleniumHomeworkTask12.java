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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask12 {
    public static WebDriver driver;

    public static void initDriver(String link) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
    }

    public static void login(String link, String username, String password) {
        initDriver(link);
        driver.findElement(By.name("password")).sendKeys(username);
        driver.findElement(By.name("username")).sendKeys(password);
        driver.findElement(By.name("login")).submit();
    }

    @Test
    public void addNewProductCatalog() {
        login("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog", "admin", "admin");
        openNewProductPage(driver);
        String productName = generateRandomNumForName();
        fillTheForm(driver, productName);
        driver.findElement(By.cssSelector("button[name='save']")).click();
        checkNewProduct(productName, driver);
    }

    public void openNewProductPage(WebDriver driver) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[name='sku']")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[name='mpn']")).sendKeys("qwertt");
        driver.findElement(By.cssSelector("input[name='gtin']")).sendKeys("q1w2e3r36");
        driver.findElement(By.cssSelector("input[name='taric']")).sendKeys("1!!@qwt");
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("QWERTY");

        //dropdown
        WebElement dropdown = driver.findElement(By.cssSelector("select[name='manufacturer_id']"));
        Select oSelect = new Select(dropdown);
        oSelect.selectByIndex(1);


    }

    public void checkNewProduct(String productName, WebDriver driver) {
        // login("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog", "admin", "admin");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
