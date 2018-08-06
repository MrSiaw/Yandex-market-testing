package ru.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Страница с выбранным товаром
 */
public class ItemPage{
    static String pricexpath="//div[@class='n-product-price-cpa2']";
    static String sellerxpath="//div[@class='n-product-default-offer-multiple__shop']//a";

    /**
     * Метод получает цену на товар
     * @param driver - веб-драйвер
     * @return - цена
     */
    private static String getPriceElement(WebDriver driver)
    {
        String price = driver.findElement(By.xpath(pricexpath)).getText();
        return price;
    }

    /**
     * Метод получает название магазина
     * @param driver - веб-драйвер
     * @return - название
     */
    private static String getSellerElement(WebDriver driver)
    {
        String seller = driver.findElement(By.xpath(sellerxpath)).getText();
        return seller;
    }

    /**
     * Метод добавляет цену товара и название магазина во вложения отчета allure
     * @param driver - веб-драйвер
     */
    public static void printInfo(WebDriver driver)
    {
        String price = getPriceElement(driver);
        String seller = getSellerElement(driver);
        System.out.println("Seller: " + seller + "; Price: " + price + ".");
        //Allure.addAttachment("Результаты поиска:", "text/plain","Название магазина: " + seller + "; Цена: " + price + "." );
        driver.quit();
    }
}
