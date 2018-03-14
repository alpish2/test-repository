package webdriver.test;

import org.openqa.selenium.WebDriver;

public class GetPageActions {

    public static void getAdminPage(WebDriver driver) {
        driver.get(Data.adminPageLink);
        if (driver.getCurrentUrl().equals(Data.adminPageLink))
            System.out.println("SUCCESS: " + "Admin page should be opened now. Current URL: " + driver.getCurrentUrl());
        else
            System.out.println("ERROR: Current URL is not the same as admin page URL. Current URL: " + driver.getCurrentUrl());
    }

    public static void getMainPage(WebDriver driver) {
        driver.get(Data.mainPageLink);
        if (driver.getCurrentUrl().equals(Data.mainPageLink))
            System.out.println("SUCCESS: " + "Main page should be opened now. Current URL: " + driver.getCurrentUrl());
        else
            System.out.println("ERROR: Current URL is not the same as main page URL. Current URL: " + driver.getCurrentUrl());
    }

    public static void getCountriesPage(WebDriver driver) {
        driver.get(Data.countriesPageLink);
        LoginActions.loginAsAdminFromLoginPage(driver);
        if (driver.getCurrentUrl().equals(Data.countriesPageLink))
            System.out.println("SUCCESS: " + "Countries page should be opened now. Current URL: " + driver.getCurrentUrl());
        else System.out.println("ERROR: Current URL is not the same as countries page URL");
    }

    public static void getPageByLinkAsAdmin(WebDriver driver, String link) {
        driver.get(link);
        LoginActions.loginAsAdminFromLoginPage(driver);
        if (driver.getCurrentUrl().equals(link))
            System.out.println("SUCCESS: " + "Specified page should be opened now. Current URL: " + driver.getCurrentUrl());
        else System.out.println("ERROR: Current URL is not the same as specified page URL");
    }

    public static void getPageByLink(WebDriver driver, String link) {
        driver.get(link);
        // LoginActions.loginAsAdminFromLoginPage(driver);
        if (driver.getCurrentUrl().equals(link))
            System.out.println("SUCCESS: " + "Specified page should be opened now. Current URL: " + driver.getCurrentUrl());
        else System.out.println("ERROR: Current URL is not the same as specified page URL");
    }

    public static void getCatalogPage(WebDriver driver) {
        driver.get(Data.catalogPageLink);
        LoginActions.loginAsAdminFromLoginPage(driver);
        if (driver.getCurrentUrl().equals(Data.catalogPageLink))
            System.out.println("SUCCESS: " + "Catalog page should be opened now. Current URL: " + driver.getCurrentUrl());
        else System.out.println("ERROR: Current URL is not the same as catalog page URL");
    }

    public static void getSecurityPage(WebDriver driver) {
        driver.get(Data.securityPageLink);
        if (driver.getCurrentUrl().equals(Data.securityPageLink))
            System.out.println("SUCCESS: " + "Security page should be opened now. Current URL: " + driver.getCurrentUrl());
        else
            System.out.println("ERROR: Current URL is not the same as admin page URL. Current URL: " + driver.getCurrentUrl());
    }

    public static void getPageByLinkAsUser(WebDriver driver, String link, String login, String password) {
    }


}
