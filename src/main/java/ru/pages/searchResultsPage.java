package ru.pages;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import ru.classes.item;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Страница с результатами поиска
 */
public class searchResultsPage
{
    /**
     * Метод получает список веб-эелементов, содержаших названия товаров
     *
     * @param driver - веб-драйвер
     * @return - список названий
     */
    private List<WebElement> getTitles(WebDriver driver)
    {
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__title']"));
        return titles;
    }

    /**
     * Метод получает список веб-эелементов, содержаших цены на товары
     *
     * @param driver - веб-драйвер
     * @return - список цен
     */
    private List<WebElement> getPrices(WebDriver driver)
    {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__main-price']"));
        return prices;
    }

    /**
     * Метод создает список найденных товаров из списка веб-элементов с ценами и названиями товаров
     * @param driver - веб-драйвер
     * @return - список товаров
     */
    public ArrayList<String> createListOfItems(WebDriver driver)
    {
        ArrayList<String> listOfItems = new ArrayList<String>();
        List<WebElement> titles = getTitles(driver);
        List<WebElement> prices = getPrices(driver);
        for (int i = 0; i < (prices.size()); i = i + 1)
        {
            listOfItems.add(titles.get(i).getText() + " " + prices.get(i).getText());
        }
        returnListOfItems(listOfItems);
        return listOfItems;
    }

    /**
     * Метод проверяет налие искомого товара в списке товаров
     * @param request - текст запроса
     * @param listOfItems - список найденных товаров
     * @return - 1 или 0 в зависимости от наличие товара в списке
     */
    public int checkItemInList(String request, ArrayList<String> listOfItems)
    {
        int contains=0;

        // TODO: Stream API

        for (int i = 0; i < (listOfItems.size()); i = i + 1)
        {
            if (listOfItems.get(i).contains(request))
            {
                contains=1;
            }
        }
        return contains;
    }

    /**
     * Метод возвращает веб-элемент для сортировки товаров по цене
     * @param driver - веб-драйвер
     * @return веб-элемент сортировки по цене
     */
    private WebElement findSortElement(WebDriver driver)
    {
        WebElement sort = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']//a"));
        return sort;
    }

    /**
     * Метод сортирует найденные товары по цене
     * @param driver - веб-драйвер
     * @throws InterruptedException - исключение
     */
    public void clickSortElement(WebDriver driver) throws InterruptedException {
        WebElement sort=findSortElement(driver);
        sort.click();
        SECONDS.sleep(3);
    }

    /**
     * Метод ищет первый товар в списке
     * @param driver - веб-драйвер
     * @return - первый товар в списке
     */
    private WebElement findFisrtItem(WebDriver driver)
    {
        WebElement firstItem = driver.findElement(By.xpath("//div[@class='n-snippet-cell2__title']//a"));
        return firstItem;
    }

    /**
     * Щелкнуть на первый товар в списке
     * @param driver
     */
    public void clickFirstItem(WebDriver driver)
    {
        WebElement firstItem = findFisrtItem(driver);
        firstItem.click();
    }
    /**
     * Метод добавляет список найденных товаров во вложения
     * @param ItemsInfo список найденных товаров
     * @return список найденных товаров
     */
    @Attachment(value = "List of Items", type = "text")
    public  ArrayList<String> returnListOfItems (ArrayList<String> ItemsInfo) {
        return ItemsInfo;
    }
}

