package my.bridgePattern;

public class Doctor extends Treatment{
    private String name = "li";
    
    public Doctor(Medicine medicine) {
        super(medicine);
    }
    
    @Override
    public void modality() {
        System.out.println("---->");medicine.type();
    }
}
