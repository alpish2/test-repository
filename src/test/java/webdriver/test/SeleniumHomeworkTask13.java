package webdriver.test;
//Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
//        1) открыть страницу какого-нибудь товара
//        2) добавить его в корзину
//        3) подождать, пока счётчик товаров в корзине обновится
//        4) вернуться на главную страницу,
// повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
//        5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
//        6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask13 {
    public static WebDriver driver;

    public static void initDriver(String link) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(link);
    }

    @Test
    public void shoppingCartAddRemove() {
        initDriver("http://localhost:8080/litecart/en/");
        addProductsToCart(driver);
        removeProductsFromCart(driver);
    }

    public void addProductsToCart(WebDriver driver) {

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("a[href='#popular-products']")).click();
            List<WebElement> products = driver.findElements(By.xpath(".//*[@id='box-popular-products']/div/div"));

            products.get(i).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("div#view-full-page a")).click();
            String quantityBefore = driver.findElement(By.cssSelector("span.quantity")).getText();
            int quantityBeforeInt = Integer.parseInt(quantityBefore);

            if (driver.findElements(By.name("options[Size]")).size() != 0) {
                Select oSelect = new Select(driver.findElement(By.name("options[Size]")));
                oSelect.selectByValue("Small");
            }

            driver.findElement(By.cssSelector("button.btn-success")).click();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String quantityAfter = Integer.toString(i + 1);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), quantityAfter));

            int quantityAfterInt = Integer.parseInt(quantityAfter);
            Assert.assertTrue(quantityBeforeInt < quantityAfterInt);

            driver.findElement(By.cssSelector("i.fa-home")).click();
        }
    }

    public void removeProductsFromCart(WebDriver driver) {
        driver.findElement(By.cssSelector("div#cart a")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> deleteIcons = driver.findElements(By.name("remove_cart_item"));
        for (int i = 0; i < deleteIcons.size(); i++) {
            deleteIcons.get(i).click();

        }
    }

}

