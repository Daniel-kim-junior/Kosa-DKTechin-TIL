package 실습6;

public class CargoPlane extends Plane {
    public CargoPlane() {}

    public CargoPlane(String planeName, int fuelSize) {
        super(planeName, fuelSize);
    }
    @Override
    public void flight(int distance) {
        setFuelSize(getFuelSize() - 50 * (distance / 10));
    }
}
