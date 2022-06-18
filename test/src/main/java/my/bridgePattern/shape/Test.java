package my.bridgePattern.shape;

/**
 * 2022/6/18
 * NJL
 */
public class Test {
    public static void main(String[] args) {
        Color color = new Blue();
        Shape shape = new Circle();
        shape.setColor(color);
        shape.draw();
        System.out.println(color instanceof Color);
        System.out.println(shape instanceof Circle);
    }
}
