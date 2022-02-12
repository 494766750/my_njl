package my.Decorator;

public class CocaCocla implements Drinks{
    
    private String type = "可口可乐";
    
    @Override
    public String getDescription() {
        return type;
    }
    
    @Override
    public double getPrice() {
        return 50;
    }
}
