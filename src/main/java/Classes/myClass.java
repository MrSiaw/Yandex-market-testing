package Classes;

public class myClass {
    String name;
    String price;
    public void setItem(String namefromsite,String pricefromsite) {
        myClass.this.name = namefromsite;
        myClass.this.price = pricefromsite;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }

}
