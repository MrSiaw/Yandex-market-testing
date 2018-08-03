import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 */
public class test {
    /**
     *Тест открывает яндекс-маркет, вводит в поле поиск название товара, нажимает кнопку поиска и получает список результатов.
     * Если в списке есть искомый товар - нажимает на сортировку по цене и выбирает самый первый результат.
     * Выводит название магазина и цену
     * @throws InterruptedException исключение
     */
    @Test(description = "Тест проверки работы поискового поля")
    public void findCheepItem() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Student/Downloads/chromedriver_win32/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        open(driver);
        sendsearchfield(driver);
        submitsearch(driver);
        getresult(driver);
        sortclick(driver);
        firstresultclick(driver);
        getpriceandseller(driver);
    }

    /**
     *Метод открывает сайт яндекс-маркета и делает сриншот.
     * @param driver Web-драйвер
     */
    @Step("Открыть сайт ")
    public void open(WebDriver driver){
        driver.get("https://market.yandex.ru/");
        saveAllureScreenshot(driver);
    }

    /**
     *Метод вводит запрос в поисковое поле и делает сриншот.
     * @param driver Web-драйвер
     */
    @Step("Ввести запрос в поиск")
    public void sendsearchfield(WebDriver driver){
        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.clear();
        searchField.sendKeys("Honor 9");
        saveAllureScreenshot(driver);
    }

    /**
     *Метод нажимает на кнопку "найти" и делает сриншот.
     * @param driver Web-драйвер
     */
    @Step("Нажать на кнопку поиска")
    public void submitsearch(WebDriver driver){
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
        saveAllureScreenshot(driver);
    }
    /**
     *Метод получает результаты поиска и проверяет наличие искомого товара.
     * Если товара нет - браузер закрывается и сообщение добавляется в отчет.
     * Если товар есть - к отчету прикрепляется список.
     * @param driver Web-драйвер
     */
    @Step("Получить список результатов")
    public void getresult(WebDriver driver){
        ArrayList<Item> results = new ArrayList<Item>();
        String nameandprice="";
        ArrayList<String> ItemsInfo = new ArrayList<String>();
        int resultscontains=0;
        Item phone = new Item();
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__title']"));
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__main-price']"));
        for (int i=0;i<(prices.size());i=i+1){

            phone.setItem(titles.get(i).getText(),prices.get(i).getText());
            nameandprice = phone.name+" "+phone.price;
            ItemsInfo.add(i,nameandprice);
            if (phone.name.contains("Honor 9")){
                resultscontains=1;
            }
            results.add(i,phone);
            System.out.println(phone.name+" "+phone.price);
        }
        if (resultscontains==0)
        {
            System.out.println("Нет товара в результатах поиска");
            Allure.addAttachment("Результаты поиска:", "text/plain","Нет товара в результатах поиска" );
            driver.quit();
        }
        else
        {
            listofitems(ItemsInfo);
        }
    }
    /**
     *Метод нажимает на сортировку по цене и делает сриншот.
     * @param driver Web-драйвер
     * @throws InterruptedException исключение
     */
    @Step("Сортировка по цене")
    public void sortclick(WebDriver driver) throws InterruptedException {
        WebElement sort = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']//a"));
        sort.click();
        SECONDS.sleep(3);
        saveAllureScreenshot(driver);
    }

    /**
     *Метод нажимает на самый первый товар в списке и делает сриншот.
     * @param driver Web-драйвер
     */
    @Step("Выбрать первый товар в списке")
    public void firstresultclick(WebDriver driver) {
        WebElement firstResult = driver.findElement(By.xpath("//div[@class='n-snippet-cell2__title']//a"));
        firstResult.click();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        saveAllureScreenshot(driver);
    }

    /**
     * Метод получает название магазина и цену товара и прикрепляет к отчету.
     * Закрывает браузер.
     * @param driver Web-драйвер
     */
    @Step("Получить название магазина и цену, закрыть браузер")
    public  void getpriceandseller(WebDriver driver) {
        String price = driver.findElement(By.xpath("//div[@class='n-product-price-cpa2']")).getText();
        String seller = driver.findElement(By.xpath("//div[@class='n-product-default-offer-multiple__shop']//a")).getText();
        System.out.println("Seller: " + seller + "; Price: " + price + ".");
        Allure.addAttachment("Результаты поиска:", "text/plain","Название магазина: " + seller + "; Цена: " + price + "." );
        driver.quit();
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

    /**
     * Метод добавляет список найденных товаров во вложения
     * @param ItemsInfo список найденных товаров
     * @return список найденных товаров
     */
    @Attachment(value = "List of Items", type = "text")
    public  ArrayList<String> listofitems (ArrayList<String> ItemsInfo) {
        return ItemsInfo;
    }
}
