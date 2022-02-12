package my.test;

class Sub extends Super {
        
        
        public Integer getLenght() {
            return  Integer.valueOf(5);
        }
    public static void main(String[] args) {
        Super sooper = new Super();
        Sub sub = new Sub();
        System.out.println(sooper.getLenght().toString() + "," +
                sub.getLenght().toString() );
    }
}