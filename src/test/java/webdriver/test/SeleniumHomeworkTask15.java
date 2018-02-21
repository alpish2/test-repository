package webdriver.test;

import org.junit.Test;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask15 {


    @Test
    public void initRemoteDriver() throws MalformedURLException {

        InternetExplorerOptions IEOptions = new InternetExplorerOptions();
        IEOptions.setCapability("IEDriverServer", true);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://10.91.5.63:4444/wd/hub"), IEOptions);

        System.out.println("driver initialized");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://selenium2.ru/");
        driver.quit();
    }


}
