
public abstract class Car extends Vehicle {
    private double co2Emissions;

    public Car(String plateNumber, String maker, String model,
               double co2Emissions) {
        super(plateNumber, maker, model);
        this.co2Emissions = co2Emissions;
    }

    public double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }
}