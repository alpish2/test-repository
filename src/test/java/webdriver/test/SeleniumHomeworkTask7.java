package webdriver.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumHomeworkTask7 {

    public WebDriver driver;

    @Before
    public void initDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
    }


    @After
    public void quitDriver(){
        driver.quit();
    }




}