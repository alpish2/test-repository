package webdriver.test;
// Задание 15. Постройте небольшой грид
// создайте грид, который состоит из диспетчера, работающего на вашей основной машине,
// и двух узлов -- один тоже на основной машине, а другой внутри виртуальной машины.
// На виртуальной машине  доступен браузер Internet Explorer, а на основной машине он недоступен.
// Попробуйте запустить какие-нибудь тесты удалённо на этом гриде, указывая разные браузеры,
// и убедитесь в правильности конфигурации.


import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumHomeworkTask15 {
    public  static WebDriver driver;

    @Test
    public void initRemoteDriver() throws MalformedURLException {

        InternetExplorerOptions IEOptions = new InternetExplorerOptions();
        IEOptions.setCapability("IEDriverServer", true);
         driver = new RemoteWebDriver(new URL("http://10.91.5.63:4444/wd/hub"), IEOptions);

        System.out.println("driver initialized");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://selenium2.ru/");  //IE11 on local machine
        driver.get("http://selenium2.ru/");  //IE11 on virtual machine


    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

}
