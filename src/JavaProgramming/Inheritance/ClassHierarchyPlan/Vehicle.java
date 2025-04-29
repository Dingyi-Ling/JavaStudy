package JavaProgramming.Inheritance.ClassHierarchyPlan;

public class Vehicle {
    // Instance variables
    private String make;
    private int year;
    private double weight;
    private String color;
    private double mpg;

    // Constructor
    public Vehicle(String make, int year, double weight, String color, double mpg) {
        this.make = make;
        this.year = year;
        this.weight = weight;
        this.color = color;
        this.mpg = mpg;
    }

    // Helper/getter methods
    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public double getMpg() {
        return mpg;
    }

    // Setter methods
    public void setMake(String make) {
        this.make = make;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    // Methods listed in UML
    public void displayVehicleInfo() {
        System.out.println("Make: " + make);
        System.out.println("Year: " + year);
        System.out.println("Weight: " + weight);
        System.out.println("Color: " + color);
        System.out.println("MPG: " + mpg);
    }

    public double calculateFuelEfficiency(double distance, double fuelUsed) {
        return distance / fuelUsed;
    }

    public boolean isEconomical() {
        return mpg > 30.0;
    }

    public double estimateYearlyFuel(double milesPerYear) {
        return milesPerYear / mpg;
    }

    public double calculateRange(double fuelCapacity) {
        return fuelCapacity * mpg;
    }

    // Overloaded method
    public double calculateRange(double fuelCapacity, double efficiencyFactor) {
        return fuelCapacity * mpg * efficiencyFactor;
    }

    public boolean isAntique() {
        int currentYear = 2025; // Assuming current year
        return (currentYear - year) > 25;
    }

    public double calculateDepreciation(double originalValue, int age) {
        double result = 1;
        for (int i = 0; i < age; i++) {
            result *= 0.85;
        }
        return originalValue * result;
    }

    public String vehicleDescription() {
        return year + " " + make + " (" + color + ") ";
    }

    // Overrides
    public String toString() {
        return "Vehicle [make=" + make + ", year=" + year + ", weight=" + weight +
                ", color=" + color + ", mpg=" + mpg + "]";
    }
}