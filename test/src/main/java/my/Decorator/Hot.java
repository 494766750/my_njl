package my.Decorator;

public class Hot extends Decorator{
    
    private String type = "加热";
    private Drinks drinks;
    
    public Hot(Drinks drinks) {
        this.drinks = drinks;
    }
    
    public String getDescription() {
        return drinks.getDescription()+type;
    }
    
    public double getPrice() {
        System.out.println("---->如果加热多收费20块");
        return drinks.getPrice()+20;
    }
}
