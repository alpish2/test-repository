package webdriver.test;

public class Data {

    public static String localhostAddress = "http://localhost:8088/litecart";

    //new product info
    public static String dateFrom = "1991-01-01";
    public static String dateTo = "1995-01-01";
    public static String code = "123";
    public static String sku = "123";
    public static String mpn = "123";
    public static String gtin = "123";
    public static String taric = "123";
    public static String keywords = "qwerty";

    //links
    public static String adminPageLink = localhostAddress + "/admin/";
    public static String mainPageLink = localhostAddress + "/en/";
    public static String countriesPageLink = localhostAddress + "/admin/?app=countries&doc=countries";
    public static String catalogPageLink = localhostAddress + "/admin/?app=catalog&doc=catalog";
    public static String securityPageLink = localhostAddress + "/admin/?app=settings&doc=security";

    //new user info
    public static String firstname = "sasha";
    public static String lastname = "pish";
    public static String emailPart1 = "sashapish";
    public static String emailPart3 = "@sdl.com";
    public static String password = "sasha";
    public static String confirmed_password = "sasha";

    public static String usernameAdmin = "admin";
    public static String passwordAdmin = "admin";

}
