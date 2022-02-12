package my.Decorator;

public class Sprite implements Drinks{
    
    private String type = "雪碧";
    
    @Override
    public String getDescription() {
        return type;
    }
    
    @Override
    public double getPrice() {
        return 30;
    }
}
