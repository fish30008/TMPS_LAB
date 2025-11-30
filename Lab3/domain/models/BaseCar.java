package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public class BaseCar implements IVehicle, DVehicle {
    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The car is driving on the road.");
        logger.log("Car is moving");
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public double getPrice() {
        return 2000;
    }
}