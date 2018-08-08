import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.classes.*;
import ru.pages.*;
import ru.yandex.qatools.allure.annotations.Step;
import java.io.IOException;
import static java.util.concurrent.TimeUnit.SECONDS;

public class test
{
    private String request,url;
    private  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public WebDriver getDriver()
    {
        return driver.get();
    }
    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    //private WebDriver driver;
    @BeforeTest
    /**
     * Метод инициализирует веб-драйвер
     */
    public void beforeTest()
    {

    }
    @DataProvider(name = "Conf", parallel = true)
    public static Object[][] credentials() {

        return new Object[][] { { "Honor 9" }, { "Iphone 9" }};

    }
    /**
     *Тест открывает яндекс-маркет, вводит в поле поиск название товара, нажимает кнопку поиска и получает список результатов.
     * Если в списке есть искомый товар - нажимает на сортировку по цене и выбирает самый первый результат.
     * Выводит название магазина и цену
     * @throws InterruptedException исключение
     */
    @Test(description = "Тест проверки работы поискового поля",dataProvider = "Conf",threadPoolSize=2)
    public void findCheepItem(String request) throws InterruptedException, IOException {
        SearchOptions options = Desializer.fromJson(SearchOptions.class,"data/searchOptions.json");
        url = options.url;
        setDriver(new ChromeDriver());
        openSite(url);
        IndexPage indexPage = new IndexPage(getDriver(),"data/indexPage.json");
        indexPage.search(request);
        SearchResultPage resultsPage = new SearchResultPage(getDriver(),"data/searchResultPage.json");
        //del
        // List<Item> results = resultsPage.returnItemList();
        resultsPage.checkItemInList(request,resultsPage.returnStringList());
        ListOfItems.returnToAllureListOfItems(resultsPage.returnStringList());
        resultsPage.clickSortElement();
        resultsPage.clickFirstItem();
        ItemPage itemPage = new ItemPage(getDriver(),"data/itemPage.json");
        itemPage.printInfo();
    }

    /**
     * Метод закрывает браузер
     */
    @Step("Закрыть бразуер")
    @AfterMethod
    private void qiut()
    {
        getDriver().close();
    }

    /**
     * Метод открывает в браузере полученный url и делает скриншот главной страницы
     */
    @Step("Открыть сайт в браузере")
    private void openSite(String url)
    {
        getDriver().get("https://market.yandex.ru/");
        getDriver().manage().timeouts().pageLoadTimeout(10,SECONDS);
        Screenshot.saveAllureScreenshot(getDriver());
    }

}
