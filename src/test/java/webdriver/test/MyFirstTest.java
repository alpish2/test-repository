package webdriver.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MyFirstTest {

    private WebDriver driver;

    @Before
    public void initDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\opishcheiko\\Desktop\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void getTitle() {
        String pageURL = "http://Store.DemoQA.com/";
        driver.get(pageURL);
        String pageTitle = driver.getTitle();
        int pageTitleLength = pageTitle.length();
        System.out.println("title: " + pageTitle + "\n length of the title: " + pageTitleLength);
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals(pageURL)) System.out.println("current URL: " + currentURL);
        else System.out.print("Current URL is not correct");
    }

    @Test
    public void navigationoPractice() {
        String pageURL = "http://Store.DemoQA.com/";
        driver.get(pageURL);
        driver.get("http://seleniumsimplified.com/about-2nd-edition/");
        driver.navigate().back();
        System.out.println("back to: " + driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("forward to: " + driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    @Test
    public void findByID() {
        String pageURL = "http://Store.DemoQA.com/";
        driver.get(pageURL);
        WebElement element = driver.findElement(By.id("UserName"));
    }

    @Test
    public void fillForm() {
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        driver.findElement(By.name("firstname")).sendKeys("sasha");
        driver.findElement(By.name("lastname")).sendKeys("p");
        driver.findElement(By.id("submit")).submit();
    }

    @Test
    public void clickLinks() {
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        driver.findElement(By.partialLinkText("Partial")).click();
        System.out.print(driver.findElement(By.tagName("button")).toString());
        driver.findElement(By.linkText("Link Test")).click();
        System.out.print(driver.getCurrentUrl());
    }

    @Test
    public void radioButton() {
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        List<WebElement> radioButtons = driver.findElements(By.name("sex"));
        boolean ButtonIsSelected = false;
        ButtonIsSelected = radioButtons.get(0).isSelected();
        if (ButtonIsSelected == true) radioButtons.get(1).click();
        else radioButtons.get(0).click();
    }

    @Test
    public void xPathTest() {
        driver.get("http://toolsqa.com/automation-practice-table/");
        System.out.println(driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[4]/th")).getText());
    }

    @Test
    public void startWebDriver() {
        driver.get("http://seleniumsimplified.com/");
        Assert.assertTrue("title should start differently",
                driver.getTitle().startsWith("Selenium Simplified"));
    }

    @Test
    public void tryToInspectElements() {
        driver.get("http://seleniumsimplified.com/");
        driver.findElement(By.id("menu-item-388")).click();
    }

    @Test
    public void remouteTry() {
        WebDriver driver = new RemoteWebDriver(DesiredCapabilities.firefox());
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        System.out.print(driver.getCurrentUrl());
        driver.close();
    }


    @Test
    public void SimpleDropDownMenuTry() {
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        WebElement element = driver.findElement(By.id("continents"));
        Select oSelect = new Select(element);
        oSelect.selectByIndex(1);
        oSelect.selectByVisibleText("Africa");
        List<WebElement> oSize = oSelect.getOptions();
        int sizeOfList = oSize.size();
        for (int i = 0; i < sizeOfList; i++) {
            System.out.println(oSize.get(i).getText());
        }
    }


    @Test
    public void MultipleDropDownMenuTry() {
        driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
        Select oSelect = new Select(driver.findElement(By.id("selenium_commands")));
        oSelect.selectByIndex(0);
        oSelect.deselectByIndex(0);
        oSelect.selectByVisibleText("Navigation Commands");
        oSelect.deselectByVisibleText("Navigation Commands");
        List<WebElement> oSize = oSelect.getOptions();
        int sizeOfList = oSize.size();
        for (int i = 0; i < sizeOfList; i++) {
            System.out.println(oSize.get(i).getText());
            oSelect.selectByIndex(i);
        }
        oSelect.deselectAll();
    }

    @Test
    public void windowNavigationTry() {
        driver.get("http://wkqasv1225:2245/servlets3/wietmsd?id=1516952473640&target=main&action=col_win");
        WebElement element = driver.findElement(By.xpath("html/body/div[2]/center/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[1]/span"));
        element.click();
        System.out.println("List" + driver.getWindowHandles());
        System.out.println("Current:" + driver.getWindowHandle());
        Object[] windowArray = driver.getWindowHandles().toArray();
        driver.switchTo().window(windowArray[1].toString());
        System.out.println("Current2:" + driver.getWindowHandle());
        System.out.println("URL:" + driver.getCurrentUrl());
    }

    @After
    public void quitDriver() {

        driver.quit();
    }

}