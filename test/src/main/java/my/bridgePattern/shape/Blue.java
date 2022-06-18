package my.bridgePattern.shape;

/**
 * 2022/6/18
 * NJL
 */
public class Blue implements Color{
    @Override
    public void bepaint(String shape) {
        System.out.println("蓝色的" +shape);
    }
}
