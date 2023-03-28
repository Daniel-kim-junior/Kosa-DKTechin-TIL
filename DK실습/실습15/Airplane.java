package 실습6;

public class Airplane extends Plane {
    public Airplane() {}
    public Airplane(String planeName, int fuelSize) {
        super(planeName, fuelSize);
    }


    @Override
    public void flight(int distance) {
        setFuelSize(getFuelSize() - 30 * (distance / 10));
    }



}
