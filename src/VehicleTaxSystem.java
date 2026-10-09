
import java.util.ArrayList;

public class VehicleTaxSystem {
    private ArrayList<Person> persons;
    private ArrayList<Vehicle> vehicles;

    public VehicleTaxSystem() {
        persons = new ArrayList<Person>();
        vehicles = new ArrayList<Vehicle>();
    }

    public void addPerson(Person person) {
        if (searchPerson(person.getLicenseNumber()) != null) {
            System.out.println("This person already exists.");
        } else {
            persons.add(person);
            System.out.println("Person added successfully.");
        }
    }

    public Person searchPerson(String licenseNumber) {
        for (Person person : persons) {
            if (person.getLicenseNumber().equals(licenseNumber)) {
                return person;
            }
        }
        return null;
    }

    public void editPerson(String licenseNumber, String name,
                           String surname, String address) {
        Person person = searchPerson(licenseNumber);

        if (person == null) {
            System.out.println("Person not found.");
        } else {
            person.setName(name);
            person.setSurname(surname);
            person.setAddress(address);
            System.out.println("Person updated successfully.");
        }
    }

    public void deletePerson(String licenseNumber) {
        Person person = searchPerson(licenseNumber);

        if (person == null) {
            System.out.println("Person not found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getOwner() == person) {
                    vehicle.setOwner(null);
                }
            }

            persons.remove(person);
            System.out.println("Person deleted successfully.");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (searchVehicle(vehicle.getPlateNumber()) != null) {
            System.out.println("This vehicle already exists.");
        } else {
            vehicles.add(vehicle);
            System.out.println("Vehicle added successfully.");
        }
    }

    public Vehicle searchVehicle(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public void editVehicle(String plateNumber, String maker,
                            String model) {
        Vehicle vehicle = searchVehicle(plateNumber);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicle.setMaker(maker);
            vehicle.setModel(model);
            System.out.println("Vehicle updated successfully.");
        }
    }

    public void deleteVehicle(String plateNumber) {
        Vehicle vehicle = searchVehicle(plateNumber);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicles.remove(vehicle);
            System.out.println("Vehicle deleted successfully.");
        }
    }

    public void transferVehicle(String plateNumber,
                                String licenseNumber) {
        Vehicle vehicle = searchVehicle(plateNumber);
        Person newOwner = searchPerson(licenseNumber);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else if (newOwner == null) {
            System.out.println("New owner not found.");
        } else {
            vehicle.setOwner(newOwner);
            System.out.println("Vehicle transferred successfully.");
        }
    }

    public void generateAnnualReport() {
        System.out.println("\n===== ANNUAL TAX REPORT =====");

        for (Person person : persons) {
            double total = 0;
            boolean hasVehicles = false;

            System.out.println("\nOwner: " + person);

            for (Vehicle vehicle : vehicles) {
                if (vehicle.getOwner() == person) {
                    hasVehicles = true;

                    double tax = vehicle.calculateAnnualTax();
                    total = total + tax;

                    System.out.println(
                            vehicle.getDescription()
                                    + " | Tax: " + String.format("%.2f", tax)
                                    + " EUR"
                    );
                }
            }

            if (!hasVehicles) {
                System.out.println("No vehicles.");
            }

            System.out.println(
                    "Total tax: " + String.format("%.2f", total)
                            + " EUR"
            );
        }

        System.out.println("\n===== END OF REPORT =====");
    }
}