package webdriver.test;
/*
        Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница
         товара в учебном приложении litecart.
        1) Открыть главную страницу
        2) Кликнуть по первому товару в категории Campaigns
        3) Проверить, что открывается страница правильного товара
        Более точно, проверить, что
        а) совпадает текст названия товара
        б) совпадает цена (обе цены)
        Кроме того, проверить стили цены на главной странице и на странице товара --
         первая цена серая и зачёркнутая, а вторая цена красная и жирная.
*/

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask10 {

    public static WebDriver driver;

    public static void initDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/litecart/en/");
    }

    @Test
    public void checkEquivalenceOfMainPageAndProductPage() {
        initDriver();
        driver.get(" http://localhost:8080/litecart/en/");
        driver.findElement(By.linkText("Campaign Products")).click();
        List<WebElement> elementListMainPage = driver.findElements(By.cssSelector("div#box-campaign-products a"));
        String titleMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.name")).getText();

        WebElement elementStrongPriceMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("color"),"rgb(204, 0, 0)");
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("font-weight"),"700");
        String strongPriceMainPage = elementStrongPriceMainPage.getText();


        WebElement elementRegularPriceMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("color"),"rgb(51, 51, 51)");
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("text-decoration"),"line-through");
        String regularPriceMainPage = elementRegularPriceMainPage.getText();

        elementListMainPage.get(0).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement elementProductPage = driver.findElement(By.cssSelector("div.featherlight-content"));
        String titleProductPage = elementProductPage.findElement(By.cssSelector("div.row h1.title")).getText();

        WebElement elementStrongPriceProductPage =  elementProductPage.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("color"),"rgb(204, 0, 0)");
        Assert.assertEquals(elementStrongPriceMainPage.getCssValue("font-weight"),"700");
        String strongPriceProductPage = elementStrongPriceProductPage.getText();

        WebElement elementRegularPriceProductPage =  elementProductPage.findElement(By.cssSelector("div.price-wrapper del.regular-price"));
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("color"),"rgb(51, 51, 51)");
        Assert.assertEquals(elementRegularPriceMainPage.getCssValue("text-decoration"),"line-through");
        String regularPriceProductPage = elementRegularPriceProductPage.getText();

        Assert.assertTrue(titleMainPage.equals(titleProductPage));
        Assert.assertTrue(strongPriceMainPage.equals(strongPriceProductPage));
        Assert.assertTrue(regularPriceMainPage.equals(regularPriceProductPage));

    }


    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


}
