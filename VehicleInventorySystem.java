import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VehicleInventorySystem extends JFrame {

    // Fields to store vehicle data
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private DefaultListModel<String> vehicleListModel = new DefaultListModel<>();
    private JList<String> vehicleJList = new JList<>(vehicleListModel);

    // Input fields
    private JTextField makeField = new JTextField(10);
    private JTextField modelField = new JTextField(10);
    private JTextField yearField = new JTextField(5);
    private JTextField priceField = new JTextField(7);
    private JTextField searchField = new JTextField(10);

    // Constructor to set up GUI
    public VehicleInventorySystem() {
        setTitle("Vehicle Inventory System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Top panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Make:"));
        inputPanel.add(makeField);
        inputPanel.add(new JLabel("Model:"));
        inputPanel.add(modelField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);

        // Buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        // Center list to display vehicles
        add(new JScrollPane(vehicleJList), BorderLayout.CENTER);

        // Bottom panel for search
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh");
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        add(searchPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addVehicle());
        updateButton.addActionListener(e -> updateVehicle());
        deleteButton.addActionListener(e -> deleteVehicle());
        searchButton.addActionListener(e -> searchVehicle());
        refreshButton.addActionListener(e -> refreshList());

        setVisible(true);
    }

    // Add new vehicle
    private void addVehicle() {
        try {
            String make = makeField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            double price = Double.parseDouble(priceField.getText());

            Vehicle v = new Vehicle(make, model, year, price);
            vehicles.add(v);
            vehicleListModel.addElement(v.toString());

            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid year or price.");
        }
    }

    // Update selected vehicle
    private void updateVehicle() {
        int index = vehicleJList.getSelectedIndex();
        if (index >= 0) {
            try {
                String make = makeField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                double price = Double.parseDouble(priceField.getText());

                Vehicle v = new Vehicle(make, model, year, price);
                vehicles.set(index, v);
                vehicleListModel.set(index, v.toString());

                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid year or price.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a vehicle to update.");
        }
    }

    // Delete selected vehicle
    private void deleteVehicle() {
        int index = vehicleJList.getSelectedIndex();
        if (index >= 0) {
            vehicles.remove(index);
            vehicleListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Select a vehicle to delete.");
        }
    }

    // Search for vehicles
    private void searchVehicle() {
        String keyword = searchField.getText().toLowerCase();
        vehicleListModel.clear();

        for (Vehicle v : vehicles) {
            if (v.getMake().toLowerCase().contains(keyword) || v.getModel().toLowerCase().contains(keyword)) {
                vehicleListModel.addElement(v.toString());
            }
        }
    }

    // Refresh the list to show all vehicles
    private void refreshList() {
        vehicleListModel.clear();
        for (Vehicle v : vehicles) {
            vehicleListModel.addElement(v.toString());
        }
    }

    // Clear input fields
    private void clearFields() {
        makeField.setText("");
        modelField.setText("");
        yearField.setText("");
        priceField.setText("");
    }

    // Main method
    public static void main(String[] args) {
        new VehicleInventorySystem();
    }
}

// Vehicle class
class Vehicle {
    private String make;
    private String model;
    private int year;
    private double price;

    public Vehicle(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ") - $" + price;
    }
}
