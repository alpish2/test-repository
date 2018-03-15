package webdriver.test;

//Сделайте сценарий для добавления 3 товаров в корзину и удаления товаров из корзины.

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask13 extends DriverInitialization {

    @Test
    public void shoppingCartAddRemove() {
        initFFDriver();
        GetPageActions.getMainPage(driver);
        addProductsToCart(driver);
        removeProductsFromCart(driver);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    public void addProductsToCart(WebDriver driver) {

        System.out.println("INFO: Three products to be added to cart ...");
        for (int i = 0; i < 3; i++) {
            String quantityBefore;
            int quantityBeforeInt;
            List<WebElement> products;
            FindElements.findPopularProductsTab(driver);
            products = FindElements.findAllPopularProducts(driver);
            products.get(i).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            FindElements.findViewFullPageLink(driver).click();
            quantityBefore = FindElements.findCartQuantity(driver).getText();
            quantityBeforeInt = Integer.parseInt(quantityBefore);
            selectSizeIfThisFieldExists(driver);
            FindElements.findAddToCartButton(driver).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String quantityAfter = Integer.toString(i + 1);
            waitForProductTooBeAddedToTheCart(driver, quantityAfter, quantityBeforeInt);
            FindElements.findHomeButton(driver).click();
        }
    }

    public void selectSizeIfThisFieldExists(WebDriver driver) {
        if (FindElements.findOptionsSizeList(driver).size() != 0) {
            Select oSelect = new Select(FindElements.findOptionsSizeElement(driver));
            oSelect.selectByValue("Small");
        }
    }

    public void removeProductsFromCart(WebDriver driver) {
        List<WebElement> deleteIcons;
        FindElements.findCart(driver).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        deleteIcons = FindElements.findRemoveItemButton(driver);
        int numberOfProductsInTheCart = deleteIcons.size();
        for (int i = 0; i < numberOfProductsInTheCart; i++) {
            deleteIcons.get(i).click();
            deleteIcons = FindElements.findRemoveItemButton(driver);
            numberOfProductsInTheCart = deleteIcons.size();
            i = 0;
        }
        System.out.println("INFO: All items should be deleted now");
        Assert.assertTrue(FindElements.findEMelement(driver).size() != 0);
        System.out.println(FindElements.findEMelement(driver).get(0).getText());

    }

    public void waitForProductTooBeAddedToTheCart(WebDriver driver, String quantityAfter, int quantityBeforeInt) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), quantityAfter));
        int quantityAfterInt = Integer.parseInt(quantityAfter);
        Assert.assertTrue(quantityBeforeInt < quantityAfterInt);
    }


}

