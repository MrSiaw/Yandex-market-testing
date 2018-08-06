package ru.classes;
/**
 * Класс товара
 */
public class Item {
    String name;
    String price;

    /**
     * Присваивает товару название и цену
     * @param namefromsite название товара
     * @param pricefromsite цена товара
     */
    public void setItem(String namefromsite,String pricefromsite) {
        Item.this.name = namefromsite;
        Item.this.price = pricefromsite;
    }

    /**
     * Возвращает название товара
     * @return название
     */
    public String getName(){
        return name;
    }

    /**
     * Возвращает цену объекта
     * @return цена
     */
    public String getPrice(){
        return price;
    }

}
