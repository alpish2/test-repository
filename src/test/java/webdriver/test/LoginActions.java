package webdriver.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginActions {

    public static void loginAsAdminFromLoginPage(WebDriver driver) {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    public static void loginAsUserFromMenu(WebDriver driver, User user) {
        driver.findElement(By.cssSelector("ul.dropdown-menu [name='email']")).sendKeys(user.email);
        driver.findElement(By.cssSelector("ul.dropdown-menu [name='password']")).sendKeys(user.password);
    }

    public static void createAccountFormFilling(WebDriver driver, User user){
    driver.findElement(By.name("firstname")).sendKeys(user.firstname);
    driver.findElement(By.name("lastname")).sendKeys(user.lastname);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    driver.findElement(By.cssSelector("div#box-create-account [name='email']")).sendKeys(user.email);
    driver.findElement(By.cssSelector("div#box-create-account [name='password']")).sendKeys(user.password);
    driver.findElement(By.cssSelector("div#box-create-account [name='confirmed_password']")).sendKeys(user.confirmed_password);

    WebElement countryCode = driver.findElement(By.name("country_code"));
    Select oselect1 = new Select(countryCode);
    oselect1.selectByValue("US");


    if (driver.findElement(By.cssSelector("select[name='country_code'] option[value='US']")).isSelected()) {
        WebElement zoneCode = driver.findElement(By.name("zone_code"));
        Select oselect2 = new Select(zoneCode);
        oselect2.selectByIndex(3);
    }

    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    if (!driver.findElement(By.name("newsletter")).isSelected())
        driver.findElement(By.name("newsletter")).click();

}

    public static void createProductFormFilling(WebDriver driver,  Product product){
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
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys(product.dateFrom);
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys(product.dateTo);
        //textfields
        driver.findElement(By.cssSelector("input[name='name[en]']")).clear();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(product.name);
        driver.findElement(By.cssSelector("input[name='code']")).clear();
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(product.code);
        driver.findElement(By.cssSelector("input[name='sku']")).clear();
        driver.findElement(By.cssSelector("input[name='sku']")).sendKeys(product.sku);
        driver.findElement(By.cssSelector("input[name='mpn']")).clear();
        driver.findElement(By.cssSelector("input[name='mpn']")).sendKeys(product.mpn);
        driver.findElement(By.cssSelector("input[name='gtin']")).clear();
        driver.findElement(By.cssSelector("input[name='gtin']")).sendKeys(product.gtin);
        driver.findElement(By.cssSelector("input[name='taric']")).clear();
        driver.findElement(By.cssSelector("input[name='taric']")).sendKeys(product.taric);
        driver.findElement(By.cssSelector("input[name='keywords']")).clear();
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys(product.keywords);

        //dropdown
        WebElement dropdown = driver.findElement(By.cssSelector("select[name='manufacturer_id']"));
        Select oSelect = new Select(dropdown);
        oSelect.selectByIndex(1);


    }
}
