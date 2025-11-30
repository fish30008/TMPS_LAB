package Lab3.domain.adapter;
import Lab3.domain.models.*;

// ADAPTER: Makes FuelVehicle compatible with ChargingStation interface
public class ElectricVehicleAdapter implements ChargingStation {
    private final FuelVehicle fuelVehicle;

    public ElectricVehicleAdapter(FuelVehicle fuelVehicle) {
        this.fuelVehicle = fuelVehicle;
    }

    @Override
    public void charge() {
        System.out.println("Adapter converting electric charge to fuel...");
        fuelVehicle.refuel(40);
        System.out.println("Charging complete via adapter!");
    }
}