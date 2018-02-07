package webdriver.test;

//Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).
//        1) регистрация новой учётной записи,
//        2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//        3) повторный вход в только что созданную учётную запись,
//        4) и ещё раз выход.
//        В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask11 {
    public static WebDriver driver;

    public static void initDriver(String link) {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
    }

    public static void login( String link, String username, String password) {
        initDriver(link);
        driver.findElement(By.name("password")).sendKeys(username);
        driver.findElement(By.name("username")).sendKeys(password);
        driver.findElement(By.name("login")).submit();

    }

    @Test
    public void registerNewUserLogInLogOut() {
        initDriver("http://localhost:8080/litecart/en/");
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href='http://localhost:8080/litecart/en/create_account']")).click();
        findCapchaSetting();
        driver.findElement(By.name("firstname")).sendKeys("sasha");
        driver.findElement(By.name("lastname")).sendKeys("pish");
        driver.findElement(By.name(" country_code"));
        driver.findElement(By.name(" zone_code"));
        driver.findElement(By.name("email")).sendKeys("sashapish@sdl.com");
        driver.findElement(By.name("password")).sendKeys("sasha");
        driver.findElement(By.name("confirmed_password")).sendKeys("sasha");
        if(!driver.findElement(By.name("newsletter")).isSelected()) driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.name(" create_account")).click();
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href='http://localhost:8080/litecart/en/logout']")).click();
        login("http://localhost:8080/litecart/en/","sashapish@sdl.com", "sasha");
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href='http://localhost:8080/litecart/en/logout']")).click();

    }

    public void findCapchaSetting() {
        login("http://localhost:8080/litecart/admin/?app=settings&doc=security", "admin", "admin");
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
            if (elementList.get(i).getText().equals("CAPTCHA")){turnOffCapcha(i);
            System.out.println("capcha turned off");}
        }


    }

    public void turnOffCapcha(int i) {
        login("http://localhost:8080/litecart/admin/?app=settings&doc=security", "admin", "admin");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elementList = driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/a"));
        System.out.println(elementList.get(i).getText());
        elementList.get(i).click();
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
        }


}
