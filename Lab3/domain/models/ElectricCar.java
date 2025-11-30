package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public class ElectricCar implements IVehicle {
    private int batteryLevel = 100;

    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The electric car is silently driving.");
        logger.log("Electric car is moving");
    }

    @Override
    public String getType() {
        return "Electric Car";
    }

    public void charge() {
        batteryLevel = 100;
        System.out.println("Electric car charged to 100%");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}