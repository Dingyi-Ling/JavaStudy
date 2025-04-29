package JavaProgramming.Inheritance.ClassHierarchyPlan;

public class VehicleTester {
    public static void main(String[] args) {
        // Test regular vehicle
        System.out.println("Testing Regular Vehicle");
        Vehicle v1 = new Vehicle("Toyota", 2020, 3500.5, "Blue", 28.5);

        // Show basic info
        v1.displayVehicleInfo();

        // Test basic features
        System.out.println("Is economical: " + v1.isEconomical());
        System.out.println("Is antique: " + v1.isAntique());
        System.out.println("Range with full tank: " + v1.calculateRange(18) + " miles");

        // Test car
        System.out.println("Testing Car");
        Car c1 = new Car("Honda", 2023, 3200.0, "Red", 32.5,
                4, "Sedan", 2.0, "Gas",
                5, true, 16.5, 130, true, true);

        // Show basic info
        c1.displayVehicleInfo();

        // Test car specific features
        System.out.println("Is sports car: " + c1.isSportsCar());
        System.out.println("Is family car: " + c1.isFamilyCar());
        System.out.println("Luggage efficiency: " + c1.calculateLuggageEfficiency());

        // Test electric car
        System.out.println("Testing Electric Car");
        Car tesla = new Car("Tesla", 2023, 4200.0, "White", 120.0,
                4, "Sedan", 0.0, "Electric",
                5, true, 15.0, 155, true, true);

        System.out.println("Is eco-friendly: " + tesla.isEcoFriendly());
        System.out.println("Electric car summary: " + tesla.carSummary());
    }
}