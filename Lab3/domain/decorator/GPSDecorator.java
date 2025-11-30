package Lab3.domain.decorator;
import Lab3.domain.models.DVehicle;
import Lab3.utilities.VehicleLogger;

public class GPSDecorator extends VehicleDecorator {
    public GPSDecorator(DVehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void move(VehicleLogger logger) {
        System.out.println("[GPS] Navigation system activated");
        decoratedVehicle.move(logger);
        System.out.println("[GPS] Tracking location...");
    }

    @Override
    public String getType() {
        return decoratedVehicle.getType() + " + GPS";
    }

    @Override
    public double getPrice() {
        return decoratedVehicle.getPrice() + 950.0;
    }
}