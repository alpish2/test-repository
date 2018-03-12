package webdriver.test;

import org.openqa.selenium.WebDriver;

public class GetPageActions {
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
        LoginActions.loginAsAdminFromLoginPage(driver);
    }

    public static void getPageByLinkAsAdmin(WebDriver driver, String link) {
        driver.get(link);
        LoginActions.loginAsAdminFromLoginPage(driver);
    }

    public static void getCatalogPage(WebDriver driver) {
        driver.get(catalogPageLink);
        LoginActions.loginAsAdminFromLoginPage(driver);

    }

    public static void getPageByLinkAsUser(WebDriver driver, String link, String login, String password) {
        driver.get(link);
        //loginAsUser(driver, login, password);
    }


}
