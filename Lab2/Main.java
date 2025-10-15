package Lab2;

//interface contains only defenitions
interface IVehicle {
    void move();
}

// use implements because it's interface
class Car implements IVehicle {
    @Override
    public void move() {
        System.out.println("The car is driving on the road.");
    }
}

class BetMobile implements IVehicle {
    @Override
    public void move() {
        System.out.println("The car is driving on the road.");
    }
}


class Bicycle implements IVehicle {
    @Override
    public void move() {
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
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}

// Singleton Pattern
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

// Builder Pattern, step by step makes a car
class VehicleBuilder {
    private String type = "car";

    public VehicleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public IVehicle build() {
        return VehicleFactory.createVehicle(type);
    }
}

class VehicleController {
    private final IVehicle vehicle;

    public VehicleController(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startMoving() {
        vehicle.move();
    }
}

// ===== File: Main.java =====
public class Main {
    public static void main(String[] args) {
        // Direct vehicle use
        VehicleLogger logger = VehicleLogger.getInstance();
        logger.log("Application started");
        System.out.println("\n=== DirectUse ===");
        IVehicle car = new Car();
        new VehicleController(car).startMoving();

        IVehicle bike = new Bicycle();
        new VehicleController(bike).startMoving();

        System.out.println("\n=== Factory Pattern ===");
        IVehicle factoryCar = VehicleFactory.createVehicle("car");
        IVehicle factoryBike = VehicleFactory.createVehicle("bicycle");
        factoryCar.move();
        factoryBike.move();

        System.out.println("\n=== Singleton Pattern ===");


        System.out.println("\n=== Builder Pattern ===");
        IVehicle builtCar = new VehicleBuilder().setType("car").build();
        IVehicle builtBike = new VehicleBuilder().setType("bicycle").build();
        builtCar.move();
        builtBike.move();
        logger.log("Program ended");

    }
}
