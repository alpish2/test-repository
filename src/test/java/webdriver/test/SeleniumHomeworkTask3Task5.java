package webdriver.test;
/*
    1. Сделайте сценарий для логина в панель администрирования учебного приложения --
       http://localhost/litecart/admin/

    2.   Попробуйте запустить разработанный ранее сценарий логина во всех основных браузерах,
    доступных для вашей операционной системы.
    */

import org.junit.AfterClass;
import org.junit.Test;

public class SeleniumHomeworkTask3Task5 extends DriverInitialization {

    @Test
    public void loginToAdminPageWithIE() {
        initIEDriver();
        GetPageActions.getAdminPage(driver);
        LoginActions.loginAsAdminFromLoginPage(driver);
    }

    @Test
    public void loginToAdminPageWithFFesr() {
        initFFDriver();
        GetPageActions.getAdminPage(driver);
        LoginActions.loginAsAdminFromLoginPage(driver);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
