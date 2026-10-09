
public abstract class Vehicle {
    private String plateNumber;
    private String maker;
    private String model;
    private Person owner;

    public Vehicle(String plateNumber, String maker, String model) {
        this.plateNumber = plateNumber;
        this.maker = maker;
        this.model = model;
        this.owner = null;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return plateNumber + " | " + maker + " " + model;
    }

    public abstract double calculateAnnualTax();
}