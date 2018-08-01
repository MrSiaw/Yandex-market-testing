
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class cheepItem {
    @Test
    public void opensite() throws InterruptedException {
        ArrayList<Item> results = new ArrayList<Item>();
        int resultscontains=0;
        System.setProperty("webdriver.chrome.driver", "C:/Users/Student/Downloads/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://market.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.clear();
        searchField.sendKeys("Honor 9");
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        driver.manage().timeouts().implicitlyWait(8, SECONDS);
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__title']"));
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__main-price']"));
        for (int i=0;i<(prices.size());i=i+1){
            Item phone = new Item();
            phone.name=prices.get(i).getText();
            phone.price=titles.get(i).getText();
            if (phone.price.contains("Honor 9")){
                resultscontains=1;
            }
            results.add(i,phone);
        }
        if (resultscontains==1)
            {
                WebElement sort = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']//a"));
                sort.click();
                SECONDS.sleep(5);
                WebElement firstResult = driver.findElement(By.xpath("//div[@class='n-snippet-cell2__title']//a"));
                firstResult.click();
                driver.manage().timeouts().implicitlyWait(30, SECONDS);
                String price = driver.findElement(By.xpath("//div[@class='n-product-price-cpa2']")).getText();
                String seller = driver.findElement(By.xpath("//div[@class='n-product-default-offer-multiple__shop']//a")).getText();
                System.out.println("Seller: "+seller+"; Price: "+price+".");
            }
        else
            {
                System.out.println("No item in search results");
            }
        driver.quit();
    }
}
