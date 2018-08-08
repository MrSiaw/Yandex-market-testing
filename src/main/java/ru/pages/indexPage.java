package ru.pages;
import com.google.gson.annotations.SerializedName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.classes.Screenshot;
import ru.classes.XpathTemplates;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.FileNotFoundException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class IndexPage {
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
    public IndexPage(WebDriver driver, String json) throws FileNotFoundException
    {
        setXpathTemplates(json);
        setDriver(driver);
    }
    /**
     * Метод получает строку запроса, вводит в поисковую строку и нажимает кнопку найти
     * @param request - запрос
     * @throws InterruptedException - исключение
     */
    @Step("Ввести в поисковую строку запрос и нажать найти")
    public void search (String request) throws InterruptedException {
        inputIntoSearchFiled(request);
        Screenshot.saveAllureScreenshot(driver);
        clickSubmitSearch();
        Screenshot.saveAllureScreenshot(driver);
    }
    /**
     * Метод ищет поле для ввода поискового запроса
     * @return - веб-элемент для ввода запроса
     */
    private WebElement getSearchFieldElement(){
        WebElement searchField = driver.findElement(By.xpath(xpathTemplates.getSearchfieldxpath()));
        return searchField;
    }
    /**
     * Метод ищет кнопку для поиска
     * @return - веб-элемент кнопка поиска
     */
    private WebElement getSubmitSearchElement(){
        WebElement button = driver.findElement(By.xpath(xpathTemplates.getSubmitxpath()));
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
