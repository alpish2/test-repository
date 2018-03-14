package webdriver.test;
/*
        Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница
         товара в учебном приложении litecart.
        1) Открыть главную страницу
        2) Кликнуть по первому товару в категории Campaigns
        3) Проверить, что
                а) совпадает текст названия товара
                б) совпадает цена (обе цены)
        Кроме того, проверить стили цены на главной странице и на странице товара --
         первая цена серая и зачёркнутая, а вторая цена красная и жирная.
*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask10 extends DriverInitialization {

    @Test
    public void checkEquivalenceOfMainPageAndProductPage() {
        List<WebElement> elementListMainPage;
        initFFDriver();
        GetPageActions.getMainPage(driver);
        WebElement mainPage = FindElements.findCampaignProductsTab(driver);
        elementListMainPage = FindElements.findLinks(mainPage);
        String titleMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.name")).getText();
        System.out.println("Checking price appearance on the main page ...");
        WebElement elementStrongPriceMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("color"), "rgb(204, 0, 0)");
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("font-weight"), "700");
        String strongPriceMainPage = elementStrongPriceMainPage.getText();
        System.out.println("Color of price1 is red and bold");

        WebElement elementRegularPriceMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("color"), "rgb(51, 51, 51)");
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("text-decoration"), "line-through");
        String regularPriceMainPage = elementRegularPriceMainPage.getText();
        System.out.println("Color of price2 is gray and crossed off");

        elementListMainPage.get(0).click();
        System.out.println("Product page opened");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement elementProductPage = driver.findElement(By.cssSelector("div.featherlight-content"));
        String titleProductPage = elementProductPage.findElement(By.cssSelector("div.row h1.title")).getText();
        System.out.println("Checking price appearance on the product page ...");
        WebElement elementStrongPriceProductPage = elementProductPage.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("color"), "rgb(204, 0, 0)");
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("font-weight"), "700");
        String strongPriceProductPage = elementStrongPriceProductPage.getText();
        System.out.println("Color of price1 is red and bold");
        WebElement elementRegularPriceProductPage = elementProductPage.findElement(By.cssSelector("div.price-wrapper del.regular-price"));
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("color"), "rgb(51, 51, 51)");
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("text-decoration"), "line-through");
        String regularPriceProductPage = elementRegularPriceProductPage.getText();
        System.out.println("Color of price2 is gray and crossed off");

        Assert.assertTrue(titleMainPage.equals(titleProductPage));
        Assert.assertTrue(strongPriceMainPage.equals(strongPriceProductPage));
        Assert.assertTrue(regularPriceMainPage.equals(regularPriceProductPage));
        System.out.println("Titles are the same on main and product pages: " + titleMainPage + "/" + titleProductPage);
        System.out.println("Price1 are the same on main and product pages: " + strongPriceMainPage + "/" + strongPriceProductPage);
        System.out.println("Price2 are the same on main and product pages: " + regularPriceMainPage + "/" + regularPriceProductPage);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}
