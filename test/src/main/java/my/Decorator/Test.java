package my.Decorator;

public class Test {
    
    public static void main(String[] args) {
        Drinks drinks = new CocaCocla();
        Ice decorator = new Ice(drinks);
        decorator.only();
        System.out.println("---->" + decorator.getDescription());
        System.out.println("---->" + decorator.getPrice());
    }
}
