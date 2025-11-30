package Lab2;
import java.util.*;

interface VehicleBuilder {
    void buildType(String s);
    void buildWheels(String s);
    void buildEngine(String s);
    void buildColor(String s);
    Vehicle getVehicle();
}
//interface contains only defenitions
interface IVehicle
{
    String name = "Something";
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
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bicycle" -> new Bicycle();
            case "sports" -> new SportsCar(); //and as many as you want
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + type);
        };
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

class Vehicle {
    private String type;
    private String wheels;
    private String engine;
    private String color;

    public void setType(String type) { this.type = type; }
    public void setWheels(String wheels) { this.wheels = wheels; }
    public void setEngine(String engine) { this.engine = engine; }
    public void setColor(String color) { this.color = color; }
}


//here is concrete builder that implements our interface builder
class CarBuilder implements VehicleBuilder {
    private final Vehicle vehicle = new Vehicle();

    public void buildType(String type) {
        vehicle.setType(type);
    }
    public void buildWheels(String wheels) {
        vehicle.setWheels(wheels);
    }
    public void buildEngine(String engine) {
        vehicle.setEngine(engine);
    }
    public void buildColor(String color) {
        vehicle.setColor(color);
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}

    // ===== Director Class =====
    class VehicleDirector {
        private final VehicleBuilder builder;

        public VehicleDirector(VehicleBuilder builder) {
            this.builder = builder;
        }

        public Vehicle construct() {
            Scanner scanner = new Scanner(System.in);
            List<String> flags = new ArrayList<>();
            Map<String, String> flagValues = new HashMap<>();

            System.out.println("Enter which parts to build (type, wheels, engine, color). Type 'done' to finish:");
            while (true) {
                System.out.print("Part: ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("done")) break;
                flags.add(input);
            }

            // STEP 2: Enter values for those parts
            System.out.println("\nEnter values for selected parts:");
            for (String flag : flags) {
                System.out.print(flag + " value: ");
                String value = scanner.nextLine().trim();
                flagValues.put(flag, value);
            }

            // STEP 3: Build according to chosen parts and entered values
            for (String flag : flags) {
                switch (flag) {
                    case "type":
                        builder.buildType(flagValues.get("type"));
                        break;
                    case "wheels":
                        try {
                            builder.buildWheels(flagValues.get("wheels"));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number for wheels, skipping...");
                        }
                        break;
                    case "engine":
                        builder.buildEngine(flagValues.get("engine"));
                        break;
                    case "color":
                        builder.buildColor(flagValues.get("color"));
                        break;
                    default:
                        System.out.println("Unknown flag: " + flag);
                }
            }

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
        VehicleBuilder carBuilder = new CarBuilder();
        VehicleDirector carDirector = new VehicleDirector(carBuilder);

        Vehicle car = carDirector.construct();
        System.out.println("Constructed Vehicle: " + car);
//        System.out.println(car);


        logger.log("Program ended");

    }
}
