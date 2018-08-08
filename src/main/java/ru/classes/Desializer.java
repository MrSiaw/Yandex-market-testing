package ru.classes;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class Desializer
{
    /*
    public static IndexPage getParamFoIndexPage() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("data/indexPage.json"));
        IndexPage indexPage = gson.fromJson(reader,IndexPage.class);
        return indexPage;
    }
    public static SearchResultPage getParamFoSearchResultsPage() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("data/searchResultPage.json"));
        SearchResultPage searchResultPage = gson.fromJson(reader,SearchResultPage.class);
        return searchResultPage;
    }
    public static ItemPage getParamFoItemPage() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("data/itemPage.json"));
        ItemPage itemPage;
        itemPage= gson.fromJson(reader,ItemPage.class);
        return itemPage;
    }*/
    public static SearchOptions getParamFoSearchOptions() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("data/searchOptions.json"));
        SearchOptions options = gson.fromJson(reader,SearchOptions.class);
        return options;
    }

    public static <T> T fromJson(Class<T> type, String json) throws FileNotFoundException
    {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader(json));
    return (T) gson.fromJson(reader,type);
    }
}
