package my.bridgePattern.shape;

/**
 * 2022/6/18
 * NJL
 */
public abstract class Shape {
    
    Color color;
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public abstract void draw();
}
