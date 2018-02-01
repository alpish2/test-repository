package webdriver.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleniumHomeworkTask7 {

    public WebDriver driver;

    @Before
    public void initDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /*
    Task 7
    Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
            1) входит в панель администратора http://localhost/litecart/admin
            2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
            3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
    */

    @Test
    public void task7_loginToAdminGoTroughTheMenuAndCheckTitle() {
        driver.get("http://localhost:8080/litecart/admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();


        List<WebElement> webElementListToGetSize = driver.findElements(By.cssSelector("li#app- a"));
        int webElementListSize = webElementListToGetSize.size();
        System.out.println("initial SIZE:" + webElementListSize);

        int diff;

        for (int i = 0; i < webElementListSize; i++) {
            System.out.println("the beginning of for loop, i:" + i + "here:" + driver.getCurrentUrl());
            List<WebElement> webElementList = driver.findElements(By.cssSelector("li#app- a"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webElementList.get(i).click();
            System.out.println("click to" +  "here" + driver.getCurrentUrl());
            List<WebElement> webElementListChanged = driver.findElements(By.cssSelector("li#app- a"));
            diff = webElementListChanged.size() - webElementListSize;
            System.out.println("difference = " + diff);

            if (diff != 0) {
                System.out.println("inside if");

                for (int j = 1; j < diff; j++) {
                    List<WebElement> webElementListChanged2 = driver.findElements(By.cssSelector("li#app- a"));
                    webElementListChanged2.get(i + j).click();
                    System.out.println("inside second for loop, j = " + j);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("second loop after click" +  "here" + driver.getCurrentUrl());
                    System.out.println("i:" + i + "j:" + j);


                }
                driver.findElement(By.cssSelector("[title= 'Home']"));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Home button clicked"  + driver.getCurrentUrl());
            }
            diff = 0;
            System.out.println("diff:" + diff + "i:" + i);

        }

    }

    @After
    public void quitDriver() {
        // driver.quit();
    }


}