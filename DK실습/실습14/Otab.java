package mobile;

public class Otab extends Mobile {
    public Otab(){}


    public Otab(String mobileName, int batterySize, String osType) {
        super(mobileName, batterySize, osType);

    }

    @Override
    public void operate(int time) {
        super.setBatterySize(super.getBatterySize() - 12 * time);

    }

    @Override
    public void charge(int time) {
        super.setBatterySize(super.getBatterySize() + 8 * time);

    }
}
