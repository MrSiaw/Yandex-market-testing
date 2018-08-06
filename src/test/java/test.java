import com.google.gson.annotations.SerializedName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.classes.*;
import ru.pages.*;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class test {
    @SerializedName("request")
    public static String request;
    /**
     *Тест открывает яндекс-маркет, вводит в поле поиск название товара, нажимает кнопку поиска и получает список результатов.
     * Если в списке есть искомый товар - нажимает на сортировку по цене и выбирает самый первый результат.
     * Выводит название магазина и цену
     * @throws InterruptedException исключение
     */
    @Test(description = "Тест проверки работы поискового поля")
    public void findCheepItem() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Student/Downloads/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        StaticValues.setStaticValues();
        request = StaticValues.request;
        IndexPage indexPage = new IndexPage(driver);
        indexPage.search(request);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        //del
        // List<Item> results = resultsPage.returnItemList();
        resultsPage.checkItemInList(request,resultsPage.returnStringList());
        ListOfItems.returnToAllureListOfItems(resultsPage.returnStringList());
        resultsPage.clickSortElement();
        resultsPage.clickFirstItem();
        ItemPage.printInfo(driver);
    }
}
