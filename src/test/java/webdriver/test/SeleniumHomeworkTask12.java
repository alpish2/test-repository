package webdriver.test;
/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
        Для добавления товара нужно открыть меню Catalog,
        в правом верхнем углу нажать кнопку "Add New Product",
        заполнить поля с информацией о товаре и сохранить.
        заполнить только информацию на вкладках General, Information и Prices.
        После сохранения товара нужно убедиться, что он появился в каталоге
*/

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask12 {
    public static WebDriver driver;

    public static void initDriver(String link) {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
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
        public void addNewProductCatalog(){
        login("http://localhost:8080/litecart/admin", "admin", "admin");
}

















}
