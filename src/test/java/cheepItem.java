import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;


public class cheepItem {
    @Test(description = "Search option test")
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
    @Step("Open site ")
    public void open(WebDriver driver){
        driver.manage().window().maximize();
        driver.get("https://market.yandex.ru/");
        saveAllureScreenshot(driver);
    }
    @Step("Input into search field")
    public void sendsearchfield(WebDriver driver){
        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.clear();
        searchField.sendKeys("Honor 9");
        saveAllureScreenshot(driver);
    }
    @Step("Click on search button")
    public void submitsearch(WebDriver driver){
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
        saveAllureScreenshot(driver);
    }
    @Step("Get list of search results")
    public void getresult(WebDriver driver){
        ArrayList<Item> results = new ArrayList<Item>();
        int resultscontains=0;
        Item phone = new Item();
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__title']"));
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__main-price']"));
        for (int i=0;i<(prices.size());i=i+1){

            phone.setItem(titles.get(i).getText(),prices.get(i).getText());
            if (phone.price.contains("Honor 9")){
                resultscontains=1;
            }
            results.add(i,phone);
            System.out.println(phone.name+" "+phone.price);
        }
        listofitems(results);
        //Allure.addAttachment("Search results:", "text/plain", toString(results));
    }
    @Step("Sort by price")
    public void sortclick(WebDriver driver) throws InterruptedException {
        WebElement sort = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']//a"));
        sort.click();
        SECONDS.sleep(3);
        saveAllureScreenshot(driver);
        //driver.manage().timeouts().pageLoadTimeout(10,SECONDS);
    }
    @Step("Click on first item in results")
    public void firstresultclick(WebDriver driver) {
        WebElement firstResult = driver.findElement(By.xpath("//div[@class='n-snippet-cell2__title']//a"));
        firstResult.click();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        saveAllureScreenshot(driver);
    }
    @Step("Get price and name seller and quit browser")
    public  void getpriceandseller(WebDriver driver) {
        String price = driver.findElement(By.xpath("//div[@class='n-product-price-cpa2']")).getText();
        String seller = driver.findElement(By.xpath("//div[@class='n-product-default-offer-multiple__shop']//a")).getText();
        System.out.println("Seller: " + seller + "; Price: " + price + ".");
        Allure.addAttachment("Search results:", "text/plain","Seller: " + seller + "; Price: " + price + "." );
        driver.quit();
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveAllureScreenshot( WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment(value = "List of Items", type = "text")
    public  ArrayList<String> listofitems (ArrayList<Item> Items) {
        Item phone = new Item();
        String info="";
        ArrayList<String> ItemsInfo = new ArrayList<String>();
        for (int i=0;i<Items.size();i=i+1)
        {
            phone = Items.get(i);
            info="Name: "+phone.name+" Price: "+phone.price;
            ItemsInfo.add(i,info);
            info="";
        }
        return ItemsInfo;
    }
}
