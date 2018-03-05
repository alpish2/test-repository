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

import java.util.List;

public class SeleniumHomeworkTask8 extends DriverInitialization {

    public String selectorForCampaignProducts = "div#box-campaign-products";
    public String selectorForPopularProducts = "div#box-popular-products";
    public String selectorForLatestProducts = "div#box-latest-products";

    @Test
    public void checkCampaignProductsSticker() {
        initFFDriver();
        BasicActions.getMainPage(driver);
        checkSticker(setTab(driver, "Campaign Products", selectorForCampaignProducts));

    }

    @Test
    public void checkPopularProductsSticker() {
        initFFDriver();
        BasicActions.getMainPage(driver);
        checkSticker(setTab(driver, "Popular Products", selectorForPopularProducts));
    }

    @Test
    public void checkLatestProductsSticker() {
        initFFDriver();
        BasicActions.getMainPage(driver);
        checkSticker(setTab(driver, "Latest Products", selectorForLatestProducts));
    }

    public WebElement setTab(WebDriver driver, String tabName, String selector) {
        driver.findElement(By.linkText(tabName)).click();
        WebElement firstDiv = driver.findElement(By.cssSelector(selector));
        return firstDiv;
    }

    public void checkSticker(WebElement firstDiv) {
        List<WebElement> secondDiv = firstDiv.findElements(By.cssSelector("div.image-wrapper"));
        int listSize = secondDiv.size();
        for (int i = 0; i < listSize; i++) {
            if (secondDiv.get(i).findElements(By.cssSelector("div.sticker")).size() != 0) {
                int stickerCount = secondDiv.get(i).findElements(By.cssSelector("div.sticker")).size();
                Assert.assertEquals(stickerCount, 1);
            }
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
