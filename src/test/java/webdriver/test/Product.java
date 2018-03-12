package webdriver.test;

public class Product {
    String dateFrom;
    String dateTo;
    String name;
    String code;
    String sku;
    String mpn;
    String gtin;
    String taric;
    String keywords;

    Product(String dateFrom, String dateTo, String name, String code, String sku, String mpn, String gtin, String taric, String keywords) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.name = name;
        this.code = code;
        this.sku = sku;
        this.mpn = mpn;
        this.gtin = gtin;
        this.taric = taric;
        this.keywords = keywords;
    }
}
