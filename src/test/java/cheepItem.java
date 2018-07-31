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
        driver.quit();
    }
}
