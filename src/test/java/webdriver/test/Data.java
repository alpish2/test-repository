package webdriver.test;

public class Data {

    public static String port = "8088";
    public static String litecartAddress = "http://localhost:" + port + "/litecart";

    //links
    public static String adminPageLink = litecartAddress + "/admin/";
    public static String mainPageLink = litecartAddress + "/en/";
    public static String countriesPageLink = litecartAddress + "/admin/?app=countries&doc=countries";
    public static String catalogPageLink = litecartAddress + "/admin/?app=catalog&doc=catalog";
    public static String securityPageLink = litecartAddress + "/admin/?app=settings&doc=security";

    //new product info
    public static String dateFrom = "1991-01-01";
    public static String dateTo = "1995-01-01";
    public static String code = "123";
    public static String sku = "123";
    public static String mpn = "123";
    public static String gtin = "123";
    public static String taric = "123";
    public static String keywords = "qwerty";

    //new user info
    public static String firstname = "sasha";
    public static String lastname = "pish";
    public static String emailPart1 = "sashapish";
    public static String emailPart3 = "@sdl.com";
    public static String password = "sasha";
    public static String confirmed_password = "sasha";

    public static String usernameAdmin = "admin";
    public static String passwordAdmin = "admin";

    public static String linkToGoogle = "http://google.com/";
}
