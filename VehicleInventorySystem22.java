import java.util.ArrayList;
import java.util.Iterator;

class Vehicle {
    private String make;
    private String model;
    private int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}

public class VehicleInventorySystem2 {
    private ArrayList<Vehicle> inventory;

    public VehicleInventorySystem2() {
        inventory = new ArrayList<>();
    }

    // Add a vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Remove a vehicle from the inventory
    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.remove(vehicle);
    }

    // Display all vehicles in the inventory
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No vehicles in inventory.");
            return;
        }

        System.out.println("Vehicle Inventory:");
        Iterator<Vehicle> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        VehicleInventorySystem2 system = new VehicleInventorySystem2();

        // Create some vehicles
        Vehicle car1 = new Vehicle("Toyota", "Camry", 2022);
        Vehicle car2 = new Vehicle("Honda", "Civic", 2021);
        Vehicle car3 = new Vehicle("Ford", "Mustang", 2023);

        // Add vehicles to inventory
        system.addVehicle(car1);
        system.addVehicle(car2);
        system.addVehicle(car3);

        // Display the inventory
        system.displayInventory();

        // Remove a vehicle from inventory
        system.removeVehicle(car2);

        // Display the inventory after removal
        System.out.println("\nAfter removal:");
        system.displayInventory();
    }
}
