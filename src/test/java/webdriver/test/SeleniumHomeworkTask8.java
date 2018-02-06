package webdriver.test;

/*    Сделайте сценарий, проверяющий наличие стикеров у всех товаров
в учебном приложении litecart на главной странице.
Сценарий должен проверять, что у каждого товара имеется ровно один стикер.*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask8 {


    public static WebDriver driver;


    public static void initDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/litecart/en/");
    }


    @Test
    public void checkCampaignProductsSticker() {
        initDriver();
        driver.findElement(By.linkText("Campaign Products")).click();
        WebElement firstDiv = driver.findElement(By.cssSelector("div#box-campaign-products"));

        List<WebElement> secondDiv = firstDiv.findElements(By.cssSelector("div.image-wrapper"));
        int listSize = secondDiv.size();
        for (int i = 0; i < listSize; i++) {
            Assert.assertTrue(secondDiv.get(i).findElement(By.cssSelector("div.sticker")).isDisplayed());
            int stickerCount = secondDiv.get(i).findElements(By.cssSelector("div.sticker")).size();
            Assert.assertEquals(stickerCount, 1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void checkPopularProductsSticker() {
        initDriver();
        driver.findElement(By.linkText("Popular Products")).click();
        WebElement firstDiv = driver.findElement(By.cssSelector("div#box-popular-products"));

        List<WebElement> secondDiv = firstDiv.findElements(By.cssSelector("div.image-wrapper"));
        int listSize = secondDiv.size();
        for (int i = 0; i < listSize; i++) {
            Assert.assertTrue(secondDiv.get(i).findElement(By.cssSelector("div.sticker")).isDisplayed());
            int stickerCount = secondDiv.get(i).findElements(By.cssSelector("div.sticker")).size();
            Assert.assertEquals(stickerCount, 1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    @Test
    public void checkLatestProductsSticker() {
        initDriver();
        driver.findElement(By.linkText("Latest Products")).click();
        WebElement firstDiv = driver.findElement(By.cssSelector("div#box-latest-products"));
        List<WebElement> secondDiv = firstDiv.findElements(By.cssSelector("div.image-wrapper"));
        int listSize = secondDiv.size();
        for (int i = 0; i < listSize; i++) {
            Assert.assertTrue(secondDiv.get(i).findElement(By.cssSelector("div.sticker")).isDisplayed());
            int stickerCount = secondDiv.get(i).findElements(By.cssSelector("div.sticker")).size();
            Assert.assertEquals(stickerCount, 1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
