package ru.classes;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XpathTemplates {
    //index page xpaths
    @SerializedName("searchfieldxpath")
    private String searchfieldxpath;
    @SerializedName("submitxpath")
    private String submitxpath;
    //search result xpaths
    @SerializedName("titlesxpath")
    private String titlesxpath;
    @SerializedName("pricesxpath")
    private String pricesxpath;
    @SerializedName("sortxpath")
    private String sortxpath;
    @SerializedName("firstitemxpath")
    private String firstitemxpath;
    //item page xpaths
    @SerializedName("pricexpath")
    private String pricexpath;
    @SerializedName("sellerxpath")
    private String sellerxpath;

    /**
     *
     * @param json
     * @return
     * @throws FileNotFoundException
     */
    public static XpathTemplates setXpathTemplates(String json) throws FileNotFoundException
    {
        return Desializer.fromJson(XpathTemplates.class,json);
    }
    public String getSearchfieldxpath() {
        return searchfieldxpath;
    }

    public void setSearchfieldxpath(String searchfieldxpath) {
        this.searchfieldxpath = searchfieldxpath;
    }

    public String getSubmitxpath() {
        return submitxpath;
    }

    public void setSubmitxpath(String submitxpath) {
        this.submitxpath = submitxpath;
    }

    public String getTitlesxpath() {
        return titlesxpath;
    }

    public void setTitlesxpath(String titlesxpath) {
        this.titlesxpath = titlesxpath;
    }

    public String getPricesxpath() {
        return pricesxpath;
    }

    public void setPricesxpath(String pricesxpath) {
        this.pricesxpath = pricesxpath;
    }

    public String getSortxpath() {
        return sortxpath;
    }

    public void setSortxpath(String sortxpath) {
        this.sortxpath = sortxpath;
    }

    public String getFirstitemxpath() {
        return firstitemxpath;
    }

    public void setFirstitemxpath(String firstitemxpath) {
        this.firstitemxpath = firstitemxpath;
    }

    public String getPricexpath() {
        return pricexpath;
    }

    public void setPricexpath(String pricexpath) {
        this.pricexpath = pricexpath;
    }

    public String getSellerxpath() {
        return sellerxpath;
    }

    public void setSellerxpath(String sellerxpath) {
        this.sellerxpath = sellerxpath;
    }
}
