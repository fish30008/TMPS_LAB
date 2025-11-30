package Lab3.domain.decorator;
import Lab3.domain.models.DVehicle;

import Lab3.utilities.VehicleLogger;

public class ArmorDecorator extends VehicleDecorator {
    public ArmorDecorator(DVehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void move(VehicleLogger logger) {
        System.out.println("[ARMOR] Protective armor enabled");
        decoratedVehicle.move(logger);
        System.out.println("[ARMOR] Vehicle protected");
    }

    @Override
    public String getType() {
        return decoratedVehicle.getType() + " + Armor";
    }

    @Override
    public double getPrice() {
        return decoratedVehicle.getPrice() + 15000.0;
    }
}