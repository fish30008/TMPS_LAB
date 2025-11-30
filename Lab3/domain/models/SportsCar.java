package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public class SportsCar implements IVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The sports car is speeding on the track.");
        logger.log("Sports car is moving");
    }

    @Override
    public String getType() {
        return "Sports Car";
    }}
