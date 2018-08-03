package ru.classes;
/**
 * Класс товара
 */
public class item {
    String name;
    String price;

    /**
     * Присваивает товару название и цену
     * @param namefromsite название товара
     * @param pricefromsite цена товара
     */
    public void setItem(String namefromsite,String pricefromsite) {
        item.this.name = namefromsite;
        item.this.price = pricefromsite;
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
