import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ru.classes.*;
import ru.pages.*;

import java.io.FileNotFoundException;

public class newtest {
    @Test
    public void sometest() throws FileNotFoundException {
        WebDriver driver = new ChromeDriver();
        ItemPage itemPage = new ItemPage(driver,"data/itemPage.json");
        System.out.println(" ");
        System.out.println(" ");
    }
}
