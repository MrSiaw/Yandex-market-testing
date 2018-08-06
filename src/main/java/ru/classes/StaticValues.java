package ru.classes;
import ru.pages.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для работы со статическими переменными
 */
public class StaticValues {
    public static String request;

    /**
     * Метод считвает переменные из файла и присваивает заданные значения
     * @throws IOException
     */
    public static void setStaticValues() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/main/resources/conf.ini")));
        IndexPage.url = props.getProperty("url");
        request=props.getProperty("request");
        IndexPage.searchfieldxpath= props.getProperty("searchfieldxpath");
        IndexPage.submitxpath = props.getProperty("submitxpath");
        SearchResultsPage.titlesxpath=props.getProperty("titlesxpath");
        SearchResultsPage.pricesxpath=props.getProperty("pricesxpath");
        SearchResultsPage.sortxpath=props.getProperty("sortxpath");
        SearchResultsPage.firstitemxpath=props.getProperty("firstitemxpath");
    }

}
