package webdriver.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoginActions {

    public static void loginAsAdminFromLoginPage(WebDriver driver) {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(Data.usernameAdmin);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Data.passwordAdmin);
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    public static void loginAsUserFromMenu(WebDriver driver, User user) {
        FindElements.findUsernameFieldInMenu(driver).clear();
        FindElements.findUsernameFieldInMenu(driver).sendKeys(user.email);
        FindElements.findPasswordFieldInMenu(driver).clear();
        FindElements.findPasswordFieldInMenu(driver).sendKeys(user.password);
        FindElements.findLoginButtonInMenu(driver).click();
        if (FindElements.findSuccessAlert(driver).size() != 0)
            System.out.println("SUCCESS: " + "success message should be displayed, login should be successful. ");
        else {
            if (FindElements.findErrorAlert(driver).size() != 0)
                System.out.println("ERROR: there is an error message displayed, login was NOT successful");
            else System.out.println("ERROR: Login was not successful, no error messages are displayed");
        }
    }

    public static void createAccountFormFilling(WebDriver driver, User user) {
        FindElements.findElementByName(driver, "firstname").clear();
        FindElements.findElementByName(driver, "firstname").sendKeys(user.firstname);
        FindElements.findElementByName(driver, "lastname").clear();
        FindElements.findElementByName(driver, "lastname").sendKeys(user.lastname);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        FindElements.findEmailFieldInCreateAccountForm(driver).clear();
        FindElements.findEmailFieldInCreateAccountForm(driver).sendKeys(user.email);
        FindElements.findPasswordFieldInCreateAccountForm(driver).clear();
        FindElements.findPasswordFieldInCreateAccountForm(driver).sendKeys(user.password);
        FindElements.findConfirmPasswordFieldInCreateAccountForm(driver).clear();
        FindElements.findConfirmPasswordFieldInCreateAccountForm(driver).sendKeys(user.confirmed_password);

        WebElement countryCode = FindElements.findElementByName(driver, "country_code");
        Select oselect1 = new Select(countryCode);
        oselect1.selectByValue("US");


        if (driver.findElement(By.cssSelector("select[name='country_code'] option[value='US']")).isSelected()) {
            WebElement zoneCode = FindElements.findElementByName(driver, "zone_code");
            Select oselect2 = new Select(zoneCode);
            oselect2.selectByIndex(3);
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (!FindElements.findElementByName(driver, "newsletter").isSelected())
            FindElements.findElementByName(driver, "newsletter").click();

    }

    public static void createProductFormFilling(WebDriver driver, Product product) {
        //select
        List<WebElement> selectList = driver.findElements(By.cssSelector("label.btn.btn-default"));
        for (int i = 0; i < selectList.size(); i++) {
            if (selectList.get(i).getText().equals("Enabled")) selectList.get(i).click();
        }

        //checkbox
        List<WebElement> checkboxList = driver.findElements(By.cssSelector("div.checkbox label"));
        for (int i = 0; i < checkboxList.size(); i++) {
            if (checkboxList.get(i).isSelected()) checkboxList.get(i).clear();
            if (checkboxList.get(i).getText().equals("Male")) checkboxList.get(i).click();
            if (checkboxList.get(i).getText().equals("Root")) checkboxList.get(i).click();
        }

        //date
        FindElements.findElementByInputName(driver, "date_valid_from").sendKeys(product.dateFrom);
        FindElements.findElementByInputName(driver, "date_valid_to").sendKeys(product.dateTo);

        //textfields
        FindElements.findElementByInputName(driver, "name[en]").clear();
        FindElements.findElementByInputName(driver, "name[en]").sendKeys(product.name);
        FindElements.findElementByInputName(driver, "code").clear();
        FindElements.findElementByInputName(driver, "code").sendKeys(product.code);
        FindElements.findElementByInputName(driver, "sku").clear();
        FindElements.findElementByInputName(driver, "sku").sendKeys(product.sku);
        FindElements.findElementByInputName(driver, "mpn").clear();
        FindElements.findElementByInputName(driver, "mpn").sendKeys(product.mpn);
        FindElements.findElementByInputName(driver, "gtin").clear();
        FindElements.findElementByInputName(driver, "gtin").sendKeys(product.gtin);
        FindElements.findElementByInputName(driver, "taric").clear();
        FindElements.findElementByInputName(driver, "taric").sendKeys(product.taric);
        FindElements.findElementByInputName(driver, "keywords").clear();
        FindElements.findElementByInputName(driver, "keywords").sendKeys(product.keywords);

        //dropdown
        WebElement dropdown = driver.findElement(By.cssSelector("select[name='manufacturer_id']"));
        Select oSelect = new Select(dropdown);
        oSelect.selectByIndex(1);
    }

    public static int generateRandomNum() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(50);
        return randomNumber;
    }
}
