package Lab2;


interface VehicleBuilder {
    void buildType();
    void buildWheels();
    void buildEngine();
    void buildColor();
    Vehicle getVehicle();
}
//interface contains only defenitions
interface IVehicle {
    void move(VehicleLogger v);
}


// use implements because it's interface
class Car implements IVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The car is driving on the road.");
        logger.log("Car is moving");
    }
}

class SportsCar implements IVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The sports car is driving on the road.");
    }
}


class Bicycle implements IVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The bicycle is pedaling along the path.");
    }
}

// Factory Pattern centralize concrete classes
class VehicleFactory {
    public static IVehicle createVehicle(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "bicycle":
                return new Bicycle();
            case "sports":
                return new SportsCar(); //and as many as you want
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}

// Singleton Pattern (static means only wans synchronized also)
class VehicleLogger {
    private static VehicleLogger instance;

    private VehicleLogger() {}

    public static synchronized VehicleLogger getInstance() {
        if (instance == null) {
            instance = new VehicleLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Here is concrete class ->
class Vehicle {
    private String type;
    private int wheels;
    private String engine;
    private String color;

    public Vehicle(String type, int wheels, String engine, String color) {
        this.type = type;
        this.wheels = wheels;
        this.engine = engine;
        this.color = color;
    }

    // setters used by builders (not public API for others)
    public void setType(String type) { this.type = type; }
    public void setWheels(int wheels) { this.wheels = wheels; }
    public void setEngine(String engine) { this.engine = engine; }
    public void setColor(String color) { this.color = color; }

    // simulate behavior
    public void move(VehicleLogger logger) {
        logger.log(type + " with " + wheels + " wheels and " + engine + " engine is moving!");
        System.out.println(type + " (" + color + ") is now on the road.");
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", wheels=" + wheels +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
//here is concrete builder that implements our interface builder
class CarBuilder implements VehicleBuilder {
    private final Vehicle vehicle;

    public CarBuilder(String type, int wheels, String engine, String color) {
        // directly create a configured Vehicle
        this.vehicle = new Vehicle(type, wheels, engine, color);
    }
    @Override
    public void buildType() {}

    @Override
    public void buildWheels() {}

    @Override
    public void buildEngine() {}

    @Override
    public void buildColor() { }

    @Override
    public Vehicle getVehicle() { return vehicle; }
}

class VehicleDirector {
    private final VehicleBuilder builder;

    public VehicleDirector(VehicleBuilder builder) {
        this.builder = builder;
    }

    public Vehicle construct() {
        builder.buildType();
        builder.buildWheels();
        builder.buildEngine();
        builder.buildColor();
        return builder.getVehicle();
    }
}


// ===== File: Main.java =====
public class Main {
    public static void main(String[] args) {
        // Direct vehicle use
        VehicleLogger logger = VehicleLogger.getInstance();
        logger.log("Application started");



        System.out.println("\n=== Factory Pattern ===");
        IVehicle factoryCar = VehicleFactory.createVehicle("car");
        IVehicle factoryBike = VehicleFactory.createVehicle("bicycle");
        //we don't write classes as car and bicycle

        factoryCar.move(logger);
        factoryBike.move(logger);


        System.out.println("\n=== Builder Pattern ===");
        VehicleDirector carDirector = new VehicleDirector(new CarBuilder("Car", 4, "V8", "Blue"));
        Vehicle car = carDirector.construct();
        car.move(logger);
        System.out.println(car);



        logger.log("Program ended");

    }
}
