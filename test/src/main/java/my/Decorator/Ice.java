package my.Decorator;

public class Ice extends Decorator{
    private String type = "加冰";
    
    private Drinks drinks;
    
    public Ice(Drinks drinks) {
        this.drinks = drinks;
    }
    
//    public String getDescription() {
//        return drinks.getDescription()+type;
//    }
    
//    public double getPrice() {
//        return drinks.getPrice()+10;
//    }
    
    public void only(){
        System.out.println("---->不再出售加冰的饮料");
    }
}
