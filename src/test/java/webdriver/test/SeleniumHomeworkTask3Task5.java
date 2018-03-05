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
        BasicActions.getAdminPage(driver);
        BasicActions.loginAsAdmin(driver);
    }

    @Test
    public void loginToAdminPageWithFFesr() {
        initFFDriver();
        BasicActions.getAdminPage(driver);
        BasicActions.loginAsAdmin(driver);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
