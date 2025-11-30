package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public class GasCar implements IVehicle, FuelVehicle {
    private int fuelLevel = 50;

    @Override
    public void move(VehicleLogger logger) {
        System.out.println("The gas car is driving with combustion engine.");
        logger.log("Gas car is moving");
    }

    @Override
    public String getType() {
        return "Gas Car";
    }

    @Override
    public void refuel(int liters) {
        this.fuelLevel += liters;
        System.out.println("Refueled with " + liters + " liters. Total: " + fuelLevel);
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }
}