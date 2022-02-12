package my.test;

public interface  Test {
    
    default String aa(){
        System.out.println("---->" );
        return "a";
    }
    
    public static void main(String[] args) {
        Test t = new Test() {
            @Override
            public String aa() {
                return Test.super.aa();
            }
            
            public void aaa(){
            
            }
        };
        System.out.println("---->" + t.aa());
    }
}
