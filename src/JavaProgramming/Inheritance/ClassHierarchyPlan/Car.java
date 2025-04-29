package JavaProgramming.Inheritance.ClassHierarchyPlan;

public class Car extends Vehicle {
    // Instance variables
    private int numDoors;
    private String bodyType;
    private double engineSize;
    private String fuelType;
    private int numSeats;
    private boolean hasSunroof;
    private double trunkCapacity;
    private int maxSpeed;
    private boolean hasABS;
    private boolean hasAirbags;

    // Constructor
    public Car(String make, int year, double weight, String color, double mpg,
               int numDoors, String bodyType, double engineSize, String fuelType,
               int numSeats, boolean hasSunroof, double trunkCapacity,
               int maxSpeed, boolean hasABS, boolean hasAirbags) {
        super(make, year, weight, color, mpg);
        this.numDoors = numDoors;
        this.bodyType = bodyType;
        this.engineSize = engineSize;
        this.fuelType = fuelType;
        this.numSeats = numSeats;
        this.hasSunroof = hasSunroof;
        this.trunkCapacity = trunkCapacity;
        this.maxSpeed = maxSpeed;
        this.hasABS = hasABS;
        this.hasAirbags = hasAirbags;
    }

    // Overloaded constructor
    public Car(String make, int year, double weight, String color, double mpg,
               String bodyType, String fuelType) {
        super(make, year, weight, color, mpg);
        this.bodyType = bodyType;
        this.fuelType = fuelType;

        // Set Default Values
        this.numDoors = 4;
        this.engineSize = 2.0;
        this.numSeats = 5;
        this.hasSunroof = false;
        this.trunkCapacity = 15.0;
        this.maxSpeed = 120;
        this.hasABS = true;
        this.hasAirbags = true;
    }

    // Helper/getter methods
    public int getNumDoors() {
        return numDoors;
    }

    public String getBodyType() {
        return bodyType;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public boolean getHasSunroof() {
        return hasSunroof;
    }

    public double getTrunkCapacity() {
        return trunkCapacity;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public boolean getHasABS() {
        return hasABS;
    }

    public boolean getHasAirbags() {
        return hasAirbags;
    }

    // Setter methods
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    public void setTrunkCapacity(double trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setHasABS(boolean hasABS) {
        this.hasABS = hasABS;
    }

    public void setHasAirbags(boolean hasAirbags) {
        this.hasAirbags = hasAirbags;
    }

    // Methods from UML
    public boolean isSportsCar() {
        return maxSpeed > 150 && engineSize > 3.0 && bodyType.equals("coupe");
    }

    public boolean isFamilyCar() {
        return numSeats >= 5 && numDoors >= 4;
    }

    public boolean isSafetyCar() {
        return hasABS && hasAirbags;
    }

    public boolean isEcoFriendly() {
        return super.getMpg() > 35 || fuelType.equalsIgnoreCase("electric") || fuelType.equals("hybrid");
    }

    public double calculateLuggageEfficiency() {
        return trunkCapacity / super.getWeight();
    }

    public double estimateTravelTime(double distance) {
        return distance / maxSpeed;
    }

    public String carSummary() {
        return super.getYear() + " " + super.getMake() + " " + bodyType + " with " +
                engineSize + "L " + fuelType + " engine";
    }

    // Overridden methods from Vehicle
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Number of Doors: " + numDoors);
        System.out.println("Body Type: " + bodyType);
        System.out.println("Engine Size: " + engineSize + "L");
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Number of Seats: " + numSeats);
        System.out.println("Has Sunroof: " + hasSunroof);
        System.out.println("Trunk Capacity: " + trunkCapacity);
        System.out.println("Max Speed: " + maxSpeed);
        System.out.println("Has ABS: " + hasABS);
        System.out.println("Has Airbags: " + hasAirbags);
    }

    public boolean isEconomical() {
        return super.getMpg() > 40 || fuelType.equalsIgnoreCase("electric");
    }

    public String toString() {
        return "Car [" + super.toString() +
                ", numDoors=" + numDoors +
                ", bodyType=" + bodyType +
                ", engineSize=" + engineSize +
                ", fuelType=" + fuelType +
                ", numSeats=" + numSeats +
                ", hasSunroof=" + hasSunroof +
                ", trunkCapacity=" + trunkCapacity +
                ", maxSpeed=" + maxSpeed +
                ", hasABS=" + hasABS +
                ", hasAirbags=" + hasAirbags + "]";
    }
}
