package Lab3.domain.factories;
import Lab3.domain.models.*;

public class VehicleFactory {
    public static IVehicle createVehicle(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        return switch (type.toLowerCase()) {
            case "base_car" -> new BaseCar();
            case "car" -> new Car();
            case "bicycle" -> new Bicycle();
            case "sports" -> new SportsCar();
            case "electric" -> new ElectricCar();
            case "gas" -> new GasCar();
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + type);
        };
    }
}