package webdriver.test;

/*    Сделайте сценарий, проверяющий наличие стикеров у всех товаров
в учебном приложении litecart на главной странице.
Сценарий должен проверять, что у каждого товара имеется ровно один стикер.*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask8 extends DriverInitialization {

    @Test
    public void checkCampaignProductsSticker() {
        initFFDriver();
        GetPageActions.getMainPage(driver);
        checkSticker(FindElements.findCampaignProductsTab(driver));

    }

    @Test
    public void checkPopularProductsSticker() {
        initFFDriver();
        GetPageActions.getMainPage(driver);
        checkSticker(FindElements.findPopularProductsTab(driver));
    }

    @Test
    public void checkLatestProductsSticker() {
        initFFDriver();
        GetPageActions.getMainPage(driver);
        checkSticker(FindElements.findLatestProductsTab(driver));
    }

    public void checkSticker(WebElement firstDiv) {
        int listSize, stickerCount;
        List<WebElement> secondDiv = FindElements.findImageWrapperList(firstDiv);
        listSize = secondDiv.size();
        for (int i = 0; i < listSize; i++) {
            WebElement stickerElement = secondDiv.get(i);
            List<WebElement> stickerList = FindElements.findStickerList(stickerElement);
            if (stickerList.size() != 0) {
                stickerCount = stickerList.size();
                Assert.assertEquals(stickerCount, 1);
            }
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        }
    }


    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
