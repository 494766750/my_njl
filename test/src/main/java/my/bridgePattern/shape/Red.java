package my.bridgePattern.shape;

/**
 * 2022/6/18
 * NJL
 */
public class Red implements Color{
    @Override
    public void bepaint(String shape) {
        System.out.println("红色的" +shape);
    }
}
