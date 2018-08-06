package ru.classes;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Класс скриншота для прикрепления к отчету allure
 */
public class Screenshot {
    /**
     * Метод прикрпеляет скриншот к отчету allure
     * @param driver -  веб-драйвер
     * @return - скриншот
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveAllureScreenshot( WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
