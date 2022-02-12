package my.bridgePattern;

public abstract class Treatment {
    Medicine medicine;
    
    public Treatment(Medicine medicine) {
        this.medicine = medicine;
    }
    
    public abstract void modality();
    
}
