package mobile;

public class Ltab extends Mobile {


    public Ltab() {}

    public Ltab(String mobileName, int batterySize, String osType) {
        super(mobileName, batterySize, osType);
    }

    @Override
    public void operate(int time) {
        super.setBatterySize(super.getBatterySize() - 10 * time);
    }

    @Override
    public void charge(int time) {
        super.setBatterySize(super.getBatterySize() + 10 * time);
    }
}
