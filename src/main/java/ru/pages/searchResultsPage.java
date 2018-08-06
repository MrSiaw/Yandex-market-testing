package ru.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import ru.classes.Item;
import ru.classes.Screenshot;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Страница с результатами поиска
 */
public class SearchResultsPage
{
    private WebDriver driver;
    public static String titlesxpath ="//div[@class='n-snippet-cell2__title']";
    public static String pricesxpath="//div[@class='n-snippet-cell2__main-price']";
    public static String sortxpath="//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']//a";
    public static String firstitemxpath="//div[@class='n-snippet-cell2__title']//a";

    /**
     * Конструктор класса
     * @param webDriver - веб-драйвер
     */
    public SearchResultsPage(WebDriver webDriver){
        driver=webDriver;
    }
    /**
     * Метод получает список веб-эелементов, содержаших названия товаров
     *
     * @param driver - веб-драйвер
     * @return - список названий
     */
    private List<WebElement> getTitles(WebDriver driver)
    {
        List<WebElement> titles = driver.findElements(By.xpath(titlesxpath));
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
        List<WebElement> prices = driver.findElements(By.xpath(pricesxpath));
        return prices;
    }

    /**
     * Метод создает String-список найденных товаров из списка веб-элементов с ценами и названиями товаров
     * @return - список товаров
     */
    public List<String> returnStringList()
    {
        List<String> listOfItems = new ArrayList<String>();
        List<WebElement> titles = getTitles(driver);
        List<WebElement> prices = getPrices(driver);
        for (int i = 0; i < (prices.size()); i = i + 1)
        {
            listOfItems.add(titles.get(i).getText() + " " + prices.get(i).getText());
        }
        return listOfItems;
    }
    /**
     * Метод создает Item-список найденных товаров из списка веб-элементов с ценами и названиями товаров
     * @return - Item-список товаров
     */
    public List<Item> returnItemList ()
    {
        List<Item> results = new ArrayList<Item>();
        Item phone = new Item();
        List<WebElement> titles = getTitles(driver);
        List<WebElement> prices = getPrices(driver);
        for (int i = 0; i < (prices.size()); i = i + 1)
        {
            phone.setItem(titles.get(i).getText(),prices.get(i).getText());
            results.add(i,phone);
        }
        return results;
    }
    /**
     * Метод проверяет налие искомого товара в списке товаров. Если его нет - браузер закрывается.
     * @param request - текст запроса
     * @param listOfItems - список найденных товаров
     */

    @Step("Проверить наличие товара в списке найденных таваров")
    public void checkItemInList(String request, List<String> listOfItems)
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
        /*
        if (contains==1)
        {
            //Allure.addAttachment("Наличие товара","товар есть в списке");
        }
        else
        {
            //Allure.addAttachment("Наличие товара","товара нет в списке");
            driver.quit();
        }*/
    }
    /**
     * Метод возвращает веб-элемент для сортировки товаров по цене
     * @return веб-элемент сортировки по цене
     */
    private WebElement findSortElement()
    {
        WebElement sort = driver.findElement(By.xpath(sortxpath));
        return sort;
    }

    /**
     * Метод сортирует найденные товары по цене
     * @throws InterruptedException - исключение
     */
    @Step("Щелкнуть на сортировку по цене")
    public void clickSortElement() throws InterruptedException {
        WebElement sort=findSortElement();
        sort.click();
        SECONDS.sleep(3);
        Screenshot.saveAllureScreenshot(driver);
    }

    /**
     * Метод ищет первый товар в списке
     * @return - первый товар в списке
     */
    private WebElement findFisrtItem()
    {
        WebElement firstItem = driver.findElement(By.xpath(firstitemxpath));
        return firstItem;
    }

    /**
     * Щелкнуть на первый товар в списке
     */
    @Step(" Щелкнуть на первый товар в списке")
    public void clickFirstItem()
    {
        WebElement firstItem = findFisrtItem();
        firstItem.click();
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
        Screenshot.saveAllureScreenshot(driver);
    }
}

