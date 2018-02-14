package webdriver.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GeckoDriverExample {

    public static void main(String[] args) throws InterruptedException {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(firefoxOptions);

    }
}