package ru.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Страница с выбранным товаром
 */
public class resultPage{
    private String getPriceElement(WebDriver driver)
    {
        String price = driver.findElement(By.xpath("//div[@class='n-product-price-cpa2']")).getText();
        return price;
    }
    private String getSellerElement(WebDriver driver)
    {
        String seller = driver.findElement(By.xpath("//div[@class='n-product-default-offer-multiple__shop']//a")).getText();
        return seller;
    }
    public void printInfo(WebDriver driver)
    {
        String price = getPriceElement(driver);
        String
        System.out.println("Seller: " + seller + "; Price: " + price + ".");
        Allure.addAttachment("Результаты поиска:", "text/plain","Название магазина: " + seller + "; Цена: " + price + "." );
        driver.quit();
    }

}
