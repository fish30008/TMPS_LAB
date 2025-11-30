package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public class Bicycle implements IVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The bicycle is pedaling along the path.");
        logger.log("Bicycle is moving");
    }

    @Override
    public String getType() {
        return "Bicycle";
    }
}