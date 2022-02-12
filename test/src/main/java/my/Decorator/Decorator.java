package my.Decorator;

public class Decorator implements Drinks{
    
    private String type = "装饰器";
    
    @Override
    public String getDescription() {
        return type;
    }
    
    @Override
    public double getPrice() {
        return 0;
    }
}
