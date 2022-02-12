package my.bridgePattern;

public class Test {
    public static void main(String[] args) {
        Medicine medicine = new Valid();
        Treatment treatment = new Doctor(medicine);
        treatment.modality();
    }
}
