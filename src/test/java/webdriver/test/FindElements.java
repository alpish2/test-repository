package webdriver.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindElements {
    public static String imageWrapperSelector = "div.image-wrapper";
    public static String imageStickerSelector = "div.sticker";
    public static String selectorForCampaignProducts = "div#box-campaign-products";
    public static String selectorForPopularProducts = "div#box-popular-products";
    public static String selectorForLatestProducts = "div#box-latest-products";
    public static String selectorForCreateAccountButton = "ul.dropdown-menu a[href='http://localhost:8088/litecart/en/create_account']";
    public static String selectorForLogOutAccountButton = "ul.dropdown-menu a[href='http://localhost:8088/litecart/en/logout']";

    public static List<WebElement> findMenuList(WebDriver driver) {
        return driver.findElements(By.cssSelector("li#app- a"));
    }

    public static WebElement findElementBySelector(WebDriver driver, String selector) {
        return driver.findElement(By.cssSelector(selector));
    }

    public static List<WebElement> findOptionsSizeList(WebDriver driver) {
        return driver.findElements(By.name("options[Size]"));
    }

    public static WebElement findOptionsSizeElement(WebDriver driver) {
        return driver.findElement(By.name("options[Size]"));
    }

    public static List<WebElement> findLinks(WebElement div) {
        return div.findElements(By.cssSelector("a"));

    }

    public static List<WebElement> findListBySelector(WebDriver driver, String selector) {
        return driver.findElements(By.cssSelector(selector));
    }

    public static List<WebElement> findStickerList(WebElement div) {
        return div.findElements(By.cssSelector(imageStickerSelector));
    }

    public static List<WebElement> findImageWrapperList(WebElement div) {
        return div.findElements(By.cssSelector(imageWrapperSelector));
    }

    public static WebElement findCampaignProductsTab(WebDriver driver) {
        driver.findElement(By.linkText("Campaign Products")).click();
        return FindElements.findElementBySelector(driver, "div#box-campaign-products");

    }

    public static WebElement findPopularProductsTab(WebDriver driver) {
        driver.findElement(By.linkText("Popular Products")).click();
        return FindElements.findElementBySelector(driver, "div#box-popular-products");

    }

    public static WebElement findLatestProductsTab(WebDriver driver) {
        driver.findElement(By.linkText("Latest Products")).click();
        return FindElements.findElementBySelector(driver, "div#box-latest-products");

    }

    public static WebElement findSaveButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("button[value='Save']"));

    }

    public static WebElement findCancelButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("div.btn-group button[value='Cancel']"));

    }

    public static WebElement findDropdownToggleButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("li.account a.dropdown-toggle"));
    }

    public static WebElement findCreateAccountButtonInMenu(WebDriver driver) {
        return driver.findElement(By.cssSelector(selectorForCreateAccountButton));
    }

    public static WebElement findCreateAccountButtonInForm(WebDriver driver) {
        return driver.findElement(By.name("create_account"));
    }

    public static WebElement findLoginButtonInMenu(WebDriver driver) {
        return driver.findElement(By.cssSelector("ul.dropdown-menu [name='login']"));
    }

    public static WebElement findLogoutButtonInMenu(WebDriver driver) {
        return driver.findElement(By.cssSelector(selectorForLogOutAccountButton));
    }

    public static List<WebElement> findCountriesList(WebDriver driver) {
        return driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[5]//a"));
    }

    public static List<WebElement> findCountriesZonesList(WebDriver driver) {
        return driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[6]"));
    }

    public static List<WebElement> findZonesInCountriesSectionList(WebDriver driver) {
        return driver.findElements(By.xpath(".//*[@id='main']/form/table/tbody//td[3]/input"));
    }

    public static List<WebElement> findNewProductPage(WebDriver driver) {
        return driver.findElements(By.cssSelector("ul.list-inline.pull-right a"));
    }

    public static WebElement findAddToCartButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("button.btn-success"));
    }

    public static WebElement findCart(WebDriver driver) {
        return driver.findElement(By.cssSelector("div#cart a"));
    }

    public static List<WebElement> findRemoveItemButton(WebDriver driver) {
        return driver.findElements(By.cssSelector("button.btn.btn-danger"));
    }

    public static List<WebElement> findListOfHelpButtons(WebDriver driver) {
        return driver.findElements(By.cssSelector("label a[target]"));
    }

    public static List<WebElement> findListOfEditButtons(WebDriver driver) {
        return driver.findElements(By.cssSelector("a[title='Edit']"));
    }

    public static List<WebElement> findSuccessAlert(WebDriver driver) {
        return driver.findElements(By.cssSelector("div.alert.alert-success"));
    }

    public static List<WebElement> findErrorAlert(WebDriver driver) {
        return driver.findElements(By.cssSelector("alert alert-danger"));
    }

    public static WebElement findTitle(WebDriver driver) {
        return driver.findElement(By.cssSelector("h1"));

    }

    public static WebElement findCartQuantity(WebDriver driver) {
        return driver.findElement(By.cssSelector("span.quantity"));
    }

    public static WebElement findViewFullPageLink(WebDriver driver) {
        return driver.findElement(By.cssSelector("div#view-full-page a"));
    }

    public static WebElement findHomeButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("i.fa-home"));
    }

    public static List<WebElement> findAllPopularProducts(WebDriver driver) {
        return driver.findElements(By.xpath(".//*[@id='box-popular-products']/div/div"));
    }

    public static List<WebElement> findEMelement(WebDriver driver) {
        return driver.findElements(By.cssSelector("em"));
    }
}
