package ru.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.classes.XpathTemplates;

import java.io.FileNotFoundException;

public class ItemPage {
    private WebDriver driver;
    private XpathTemplates xpathTemplates;
    /**
     * Установка драйвера
     * @param driver - веб-драйвер
     */
    private void setDriver(WebDriver driver)
    {
        this.driver=driver;
    }
    /**
     *
     * @param json
     * @throws FileNotFoundException
     */
    private void setXpathTemplates(String json) throws FileNotFoundException
    {
        this.xpathTemplates=XpathTemplates.setXpathTemplates(json);
    }

    /**
     *
     * @param driver
     * @param json
     * @throws FileNotFoundException
     */
    public ItemPage(WebDriver driver, String json) throws FileNotFoundException
    {
        setXpathTemplates(json);
        setDriver(driver);
    }
    /**
     * Метод получает цену на товар
     * @return - цена
     */
    private String getPriceElement()
    {
        String price = driver.findElement(By.xpath(xpathTemplates.getPricexpath())).getText();
        return price;
    }

    /**
     * Метод получает название магазина
     * @return - название
     */
    private String getSellerElement()
    {
        String seller = driver.findElement(By.xpath(xpathTemplates.getSellerxpath())).getText();
        return seller;
    }

    /**
     * Метод добавляет цену товара и название магазина во вложения отчета allure
     */
    public void printInfo()
    {
        String price = getPriceElement();
        String seller = getSellerElement();
        System.out.println("Seller: " + seller + "; Price: " + price + ".");
        //Allure.addAttachment("Результаты поиска:", "text/plain","Название магазина: " + seller + "; Цена: " + price + "." );
    }
}
