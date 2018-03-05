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
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SeleniumHomeworkTask11 extends  DriverInitialization {

    String link = "http://localhost:8088/litecart/admin/?app=settings&doc=security";

    @Test
    public void registerNewUser() {
        initFFDriver();
        User user = new User("sasha", "pish", "sashapish6@sdl.com", "sasha", "sasha");
        turnOffCapchaSetting();
        BasicActions.getMainPage(driver);
        openCreateAccountPage(driver);
        fillTheFormWithData(driver, user);
        submitRegistrationForm(driver);
        logoutFromMainPage(driver);
        loginFromMainPage(driver);
        logoutFromMainPage(driver);
    }

    public void turnOffCapchaSetting() {
      BasicActions.getPageByLinkAsAdmin(driver,link);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elementList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[1]"));
        for (int i = 0; i < elementList.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (elementList.get(i).getText().equals("CAPTCHA")) {
                List<WebElement> elementList2 = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/a"));
                System.out.println(elementList2.get(i).getText());
                elementList2.get(i).click();
                List<WebElement> trueFalse = driver.findElements(By.cssSelector("div.btn-group label.btn.active"));
                for (int j = 0; j < trueFalse.size(); j++) {
                    if (trueFalse.get(j).equals("True")) {
                        trueFalse.get(j + 1).click();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.findElement(By.cssSelector("div.btn-group button[value='Save']")).click();
                        System.out.println("saved");
                        break;
                    } else {
                        driver.findElement(By.cssSelector("div.btn-group button[value='Cancel']")).click();
                        System.out.println("canceled");
                    }
                }
                System.out.println("capcha is turned off");
            }

        }

        driver.quit();
    }

    public void openCreateAccountPage(WebDriver driver) {
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href='http://localhost:8080/litecart/en/create_account']")).click();
    }

    public void fillTheFormWithData(WebDriver driver, User user) {

        driver.findElement(By.name("firstname")).sendKeys(user.firstname);
        driver.findElement(By.name("lastname")).sendKeys(user.lastname);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!driver.findElement(By.name("newsletter")).isSelected())
            driver.findElement(By.name("newsletter")).click();

    }

    public void submitRegistrationForm(WebDriver driver) {
        driver.findElement(By.name("create_account")).click();
    }

    public void loginFromMainPage(WebDriver driver) {
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu [name='email']")).sendKeys("sashapish@sdl.com");
        driver.findElement(By.cssSelector("ul.dropdown-menu [name='password']")).sendKeys("sasha");
        driver.findElement(By.cssSelector("ul.dropdown-menu [name='login']")).click();
    }

    public void logoutFromMainPage(WebDriver driver) {
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href='http://localhost:8080/litecart/en/logout']")).click();
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
