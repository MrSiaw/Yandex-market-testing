package ru.pages;
import org.openqa.selenium.*;
import ru.classes.Screenshot;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Главная страница
 */
public class IndexPage {
    public  WebDriver driver;
    public static String url="https://market.yandex.ru/";
    public static String searchfieldxpath;
    public static String submitxpath;
    public IndexPage(WebDriver webDriver){
        driver=webDriver;
    }
    /**
     * Открыть в браузере сайт яндекс-маркета
     */
    @Step("Открыть сайт в браузере")
    private void openSite(){

        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
    }

    /**
     * Метод получает строку запроса, вводит в поисковую строку и нажимает кнопку найти
     * @param request - запрос
     * @throws InterruptedException - исключение
     */
    @Step("Ввести в поисковую строку запрос и нажать найти")
    public void search (String request) throws InterruptedException {
        openSite();
        Screenshot.saveAllureScreenshot(driver);
        inputIntoSearchFiled(request);
        Screenshot.saveAllureScreenshot(driver);
        clickSubmitSearch();
        Screenshot.saveAllureScreenshot(driver);
/*

        SearchResultsPage resultsPage = new SearchResultsPage();
        results=resultsPage.createListOfItems(driver);
        //прикрепляем список найденных товаров к отчету
        ListOfItems.returnListOfItems(results);
        //проверяем наличие в списке нужного товара
        contains=resultsPage.checkItemInList(request,results);
        if (contains==1)
        {
            resultsPage.clickSortElement(driver);
            Screenshot.saveAllureScreenshot(driver);
            resultsPage.clickFirstItem(driver);
            Screenshot.saveAllureScreenshot(driver);
            ItemPage.printInfo(driver);
        }
        //выход из браузера если товар не найден
        else
        {
            driver.quit();
        }
        return results;
*/

    }
    /**
     * Метод ищет поле для ввода поискового запроса
     * @return - веб-элемент для ввода запроса
     */
    private WebElement getSearchFieldElement(){
        WebElement searchField = driver.findElement(By.xpath(searchfieldxpath));
        return searchField;
    }
    /**
     * Метод ищет кнопку для поиска
     * @return - веб-элемент кнопка поиска
     */
    private WebElement getSubmitSearchElement(){
        WebElement button = driver.findElement(By.xpath(submitxpath));
        return button;
    }

    /**
     * Метод нажимает на кнопку поиска
     */
    private void clickSubmitSearch()
    {
        WebElement submit = getSubmitSearchElement();
        submit.click();
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
    }

    /**
     * Метод вводит запрос в поисковую строку
     * @param request - запрос
     */
    private void inputIntoSearchFiled(String request)
    {
        WebElement searchField = getSearchFieldElement();
        searchField.clear();
        searchField.sendKeys(request);
    }
}
