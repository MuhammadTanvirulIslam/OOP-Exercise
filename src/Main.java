
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        VehicleTaxSystem system = new VehicleTaxSystem();

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== VEHICLE TAX SYSTEM =====");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person");
            System.out.println("3. Delete Person");
            System.out.println("4. Search Person");
            System.out.println("5. Add Vehicle");
            System.out.println("6. Edit Vehicle");
            System.out.println("7. Delete Vehicle");
            System.out.println("8. Search Vehicle");
            System.out.println("9. Transfer Vehicle");
            System.out.println("10. Generate Annual Tax Report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                System.out.print("License number: ");
                String license = sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Surname: ");
                String surname = sc.nextLine();

                System.out.print("Address: ");
                String address = sc.nextLine();

                system.addPerson(
                        new Person(license, name, surname, address));

            } else if (choice == 2) {
                System.out.print("Enter license number to search: ");
                String license = sc.nextLine();

                if (system.searchPerson(license) == null) {
                    System.out.println("Person not found.");
                } else {
                    System.out.print("New name: ");
                    String name = sc.nextLine();

                    System.out.print("New surname: ");
                    String surname = sc.nextLine();

                    System.out.print("New address: ");
                    String address = sc.nextLine();

                    system.editPerson(
                            license, name, surname, address);
                }

            } else if (choice == 3) {
                System.out.print("Enter license number to search: ");
                String license = sc.nextLine();

                if (system.searchPerson(license) != null) {
                    system.deletePerson(license);
                } else {
                    System.out.println("Person not found.");
                }

            } else if (choice == 4) {
                System.out.print("Enter license number: ");
                Person person = system.searchPerson(sc.nextLine());

                if (person == null) {
                    System.out.println("Person not found.");
                } else {
                    System.out.println(person);
                }

            } else if (choice == 5) {
                System.out.println("1. Motorcycle");
                System.out.println("2. Petrol car");
                System.out.println("3. Diesel car");
                System.out.println("4. Hybrid car");
                System.out.print("Vehicle type: ");
                int type = Integer.parseInt(sc.nextLine());

                System.out.print("Plate number: ");
                String plate = sc.nextLine();

                System.out.print("Maker: ");
                String maker = sc.nextLine();

                System.out.print("Model: ");
                String model = sc.nextLine();

                Vehicle vehicle = null;

                if (type == 1) {
                    System.out.print("Engine displacement: ");
                    double cc = Double.parseDouble(sc.nextLine());

                    vehicle = new Motorcycle(
                            plate, maker, model, cc);

                } else if (type == 2) {
                    System.out.print("CO2 emissions: ");
                    double co2 = Double.parseDouble(sc.nextLine());

                    vehicle = new PetrolCar(
                            plate, maker, model, co2);

                } else if (type == 3) {
                    System.out.print("CO2 emissions: ");
                    double co2 = Double.parseDouble(sc.nextLine());

                    vehicle = new DieselCar(
                            plate, maker, model, co2);

                } else if (type == 4) {
                    System.out.print("CO2 emissions: ");
                    double co2 = Double.parseDouble(sc.nextLine());

                    vehicle = new HybridCar(
                            plate, maker, model, co2);
                }

                if (vehicle != null) {
                    system.addVehicle(vehicle);
                } else {
                    System.out.println("Invalid vehicle type.");
                }

            } else if (choice == 6) {
                System.out.print("Enter plate number to search: ");
                String plate = sc.nextLine();

                Vehicle vehicle = system.searchVehicle(plate);

                if (vehicle == null) {
                    System.out.println("Vehicle not found.");
                } else {
                    System.out.print("New maker: ");
                    String maker = sc.nextLine();

                    System.out.print("New model: ");
                    String model = sc.nextLine();

                    system.editVehicle(plate, maker, model);

                    if (vehicle instanceof Motorcycle) {
                        System.out.print("New engine displacement: ");
                        double cc = Double.parseDouble(sc.nextLine());

                        ((Motorcycle) vehicle).setEngineDisplacement(cc);

                    } else if (vehicle instanceof Car) {
                        System.out.print("New CO2 emissions: ");
                        double co2 = Double.parseDouble(sc.nextLine());

                        ((Car) vehicle).setCo2Emissions(co2);
                    }
                }

            } else if (choice == 7) {
                System.out.print("Enter plate number to search: ");
                String plate = sc.nextLine();

                if (system.searchVehicle(plate) != null) {
                    system.deleteVehicle(plate);
                } else {
                    System.out.println("Vehicle not found.");
                }

            } else if (choice == 8) {
                System.out.print("Enter plate number: ");
                Vehicle vehicle = system.searchVehicle(sc.nextLine());

                if (vehicle == null) {
                    System.out.println("Vehicle not found.");
                } else {
                    System.out.println(vehicle.getDescription());
                    System.out.printf("Annual tax: %.2f EUR%n",
                            vehicle.calculateAnnualTax());

                    if (vehicle.getOwner() == null) {
                        System.out.println("Owner: None (dealership)");
                    } else {
                        System.out.println("Owner: "
                                + vehicle.getOwner().getFullName());
                    }
                }

            } else if (choice == 9) {
                System.out.print("Vehicle plate number: ");
                String plate = sc.nextLine();

                if (system.searchVehicle(plate) == null) {
                    System.out.println("Vehicle not found.");
                } else {
                    System.out.print("New owner's license number: ");
                    String license = sc.nextLine();

                    system.transferVehicle(plate, license);
                }

            } else if (choice == 10) {
                system.generateAnnualReport();

            } else if (choice == 0) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}