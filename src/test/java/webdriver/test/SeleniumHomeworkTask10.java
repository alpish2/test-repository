package webdriver.test;
/*
        Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница
         товара в учебном приложении litecart.
        Проверить, что совпадает текст названия товара и цена
        Проверить стили цен -- первая цена серая и зачёркнутая, а вторая цена красная и жирная.
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

        initFFDriver();
        GetPageActions.getMainPage(driver);
        WebElement mainPage = FindElements.findCampaignProductsTab(driver);
        List<WebElement> elementListMainPage = FindElements.findLinks(mainPage);
        String titleMainPage = elementListMainPage.get(0).findElement(By.cssSelector("div.name")).getText();
        WebElement elementStrongPriceMainPage = FindElements.findCampaignPrice(elementListMainPage.get(0));

        String strongPriceMainPage = elementStrongPriceMainPage.getText();

        WebElement elementRegularPriceMainPage = FindElements.findRegularPriceMainPAge(elementListMainPage.get(0));
        String regularPriceMainPage = elementRegularPriceMainPage.getText();

        elementListMainPage.get(0).click();
        System.out.println("Product page opened");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement elementProductPage = FindElements.findElementProductPage(driver);
        WebElement elementStrongPriceProductPage = FindElements.findCampaignPrice(elementProductPage);
        WebElement elementRegularPriceProductPage = FindElements.findRegularPriceProductPage(elementProductPage);

        String titleProductPage = elementProductPage.findElement(By.cssSelector("div.row h1.title")).getText();
        String strongPriceProductPage = elementStrongPriceProductPage.getText();
        String regularPriceProductPage = elementRegularPriceProductPage.getText();


        Asserts(regularPriceMainPage, strongPriceMainPage,
                regularPriceProductPage, strongPriceProductPage,
                titleMainPage, titleProductPage,
                elementRegularPriceMainPage, elementStrongPriceMainPage);

    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }


    public void Asserts(String regularPriceMainPage, String strongPriceMainPage,
                        String regularPriceProductPage, String strongPriceProductPage,
                        String titleMainPage, String titleProductPage,
                        WebElement elementRegularPriceMainPage, WebElement elementStrongPriceMainPage) {

        Assert.assertTrue(titleMainPage.equals(titleProductPage));

        Assert.assertEquals("Color is not red", "rgb(204, 0, 0)", elementStrongPriceMainPage.getCssValue("color"));
        Assert.assertEquals("Text is not bold", "700", elementStrongPriceMainPage.getCssValue("font-weight"));
        Assert.assertTrue(strongPriceMainPage.equals(strongPriceProductPage));
        System.out.println("Color of price1 is red and bold");

        Assert.assertEquals("Color is not gray", "rgb(51, 51, 51)", elementRegularPriceMainPage.getCssValue("color"));
        Assert.assertEquals("Text is not crossed off", "line-through", elementRegularPriceMainPage.getCssValue("text-decoration"));
        Assert.assertTrue(regularPriceMainPage.equals(regularPriceProductPage));
        System.out.println("Color of price2 is gray and crossed off");

        System.out.println("Titles are the same on main and product pages: " + titleMainPage + "/" + titleProductPage);
        System.out.println("Price1 are the same on main and product pages: " + strongPriceMainPage + "/" + strongPriceProductPage);
        System.out.println("Price2 are the same on main and product pages: " + regularPriceMainPage + "/" + regularPriceProductPage);
    }
}
