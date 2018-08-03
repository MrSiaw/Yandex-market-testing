package ru.pages;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.classes.item;
import ru.pages.searchResultsPage;
import ru.pages.resultPage;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Главная страница
 */
public class indexPage {
    public  WebDriver driver;
    public indexPage(WebDriver webDriver){
        driver=webDriver;
    }
    /**
     * Открыть бразуер и сайт яндекс-маркета
     */
    private void openSite(){
        driver.get("https://market.yandex.ru/");
    }

    /**
     * Метод получает текст запроса для поиска, вводит в поле и производит поиск.
     * Возвращает список найденных товаров."
     * @param request - запрос
     * @return - список найденных товаров
     * @throws InterruptedException - исключение
     */
    public ArrayList<String> search (String request) throws InterruptedException {
        ArrayList<String> results = new ArrayList<String>();
        int contains=0;
        WebElement searchField = searchFieldElement();
        searchField.clear();
        searchField.sendKeys(request);
        saveAllureScreenshot(driver);
        WebElement button = submitSearchElement();
        button.click();
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
        searchResultsPage resultsPage = new searchResultsPage();
        results=resultsPage.createListOfItems(driver);
        contains=resultsPage.checkItemInList(request,results);
        if (contains==1)
        {
            resultsPage.clickSortElement(driver);
            resultsPage.clickFirstItem(driver);
        }
        else
        {
            driver.quit();
        }
        return results;
    }
    /**
     * Метод ищет поле для ввода поискового запроса
     * @return - веб-элемент для ввода запроса
     */
    private WebElement searchFieldElement(){
        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        return searchField;
    }

    /**
     * Метод ищет кнопку для поиска
     * @return - веб-элемент кнопка поиска
     */
    private WebElement submitSearchElement(){
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        return button;
    }
    /**
     *Метод прикрепляет скриншот к отчету
     * @param driver Web-драйвер
     * @return скриншот
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveAllureScreenshot( WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
