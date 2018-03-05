package webdriver.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasicActions {
    public static String adminPageLink = "http://localhost:8088/litecart/admin/";
    public static String mainPageLink = "http://localhost:8088/litecart/en/";
    public static String countriesPageLink = "http://localhost:8088/litecart/admin/?app=countries&doc=countries";
    public static String catalogPageLink = "http://localhost:8088/litecart/admin/?app=catalog&doc=catalog";

    public static void getAdminPage(WebDriver driver) {
        driver.get(adminPageLink);
    }

    public static void getMainPage(WebDriver driver) {
        driver.get(mainPageLink);
    }

    public static void getCountriesPage(WebDriver driver) {
        driver.get(countriesPageLink);
        loginAsAdmin(driver);
    }

    public static void getPageByLinkAsAdmin(WebDriver driver, String link) {
        driver.get(link);
        loginAsAdmin(driver);
    }

    public static void getCatalogPage(WebDriver driver) {
        driver.get(catalogPageLink);
        loginAsAdmin(driver);

    }

    public static void getPageByLinkAsUser(WebDriver driver, String link, String login, String password) {
        driver.get(link);
        loginAsUser(driver, login, password);
    }


    public static void loginAsAdmin(WebDriver driver) {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    public static void loginAsUser(WebDriver driver, String login, String password) {

    }

    public static List<WebElement> getCountriesList(WebDriver driver) {
        List<WebElement> countriesNamesList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[5]//a"));
        return countriesNamesList;
    }

    public static List<WebElement> getCountriesZonesList(WebDriver driver) {
        List<WebElement> countriesZonesList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[6]"));
        return countriesZonesList;
    }

}
