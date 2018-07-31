import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class cheepItem {
    @Test
    public void opensite() throws InterruptedException {
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
        WebElement sort = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[3]/a[1]"));
        driver.manage().timeouts().implicitlyWait(8, SECONDS);
        sort.click();
        SECONDS.sleep(5);
        WebElement firstResult = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/a[1]"));
        firstResult.click();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        String price = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[4]/div[3]/div[1]/div[1]/div[1]/div[2]/span[1]")).getText();
        String seller = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[4]/div[3]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/span[1]/a[1]")).getText();
        System.out.println("ATTENTION");
        System.out.println(price+" "+seller);
        System.out.println("ATTENTION");

        driver.manage().timeouts().implicitlyWait(8, SECONDS);
        driver.quit();
    }
}
