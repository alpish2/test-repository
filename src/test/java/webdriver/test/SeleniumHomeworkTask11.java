package webdriver.test;

//Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).
//        1) регистрация новой учётной записи,
//        2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//        3) повторный вход в только что созданную учётную запись,
//        4) и ещё раз выход.
//        В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask11 extends DriverInitialization {


    @Test
    public void registerNewUser() {
        initFFDriver();
        User user1 = new User(Data.firstname, Data.lastname, Data.emailPart1 + LoginActions.generateRandomNum() + Data.emailPart3, Data.password, Data.confirmed_password);

        turnOffCapchaSetting();
        GetPageActions.getMainPage(driver);
        openCreateAccountPage(driver);
        LoginActions.createAccountFormFilling(driver, user1);
        submitRegistrationForm(driver);
        logoutFromMainPage(driver);
        loginFromMainPage(driver, user1);
        logoutFromMainPage(driver);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


    public void turnOffCapchaSetting() {
        GetPageActions.getSecurityPage(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> elementList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[1]"));
        for (int i = 0; i < elementList.size(); i++) {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            if (elementList.get(i).getText().equals("CAPTCHA")) {
                List<WebElement> elementList2 = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/a"));
                System.out.println(elementList2.get(i).getText());
                elementList2.get(i).click();
                List<WebElement> trueFalse = driver.findElements(By.cssSelector("div.btn-group label.btn.active"));
                for (int j = 0; j < trueFalse.size(); j++) {
                    if (trueFalse.get(j).equals("True")) {
                        trueFalse.get(j + 1).click();
                        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        FindElements.findSaveButton(driver).click();
                        System.out.println("INFO:" + "Captcha settings saved. It is turned off now");
                        break;
                    } else {
                        FindElements.findCancelButton(driver).click();
                        System.out.println("INFO: " + "Captcha setting change canceled. It was already turned off");
                    }
                }
                System.out.println("SUCCESS: " + "Capcha is turned off");
            }
        }
    }

    public void openCreateAccountPage(WebDriver driver) {
        FindElements.findDropdownToggleButton(driver).click();
        FindElements.findCreateAccountButtonInMenu(driver).click();
        System.out.println("INFO: Create account page should be opened: " + driver.getCurrentUrl());
    }

    public void submitRegistrationForm(WebDriver driver) {
        FindElements.findCreateAccountButtonInForm(driver).click();
        System.out.println("INFO: New user should be created now");
    }

    public void loginFromMainPage(WebDriver driver, User user1) {
        FindElements.findDropdownToggleButton(driver).click();
        LoginActions.loginAsUserFromMenu(driver, user1);
    }

    public void logoutFromMainPage(WebDriver driver) {
        FindElements.findDropdownToggleButton(driver).click();
        FindElements.findLogoutButtonInMenu(driver).click();
    }
}
