package 실습6;

public abstract class Plane {
    private String planeName;
    private int fuelSize;

    public Plane(){}

    public Plane(String planeName, int fuelSize) {
        this.fuelSize = fuelSize;
        this.planeName = planeName;
    }

    public void refuel(int fuel) {
        setFuelSize(getFuelSize() + fuel);
    }

    public abstract void flight(int distance);




    public void setFuelSize(int fuelSize) {
        this.fuelSize = fuelSize;
    }

    public String getPlaneName() {
        return planeName;
    }

    public int getFuelSize() {
        return fuelSize;
    }
}
