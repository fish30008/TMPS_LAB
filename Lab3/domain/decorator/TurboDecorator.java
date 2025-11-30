package Lab3.domain.decorator;
import Lab3.domain.models.DVehicle;
import Lab3.utilities.VehicleLogger;

public class TurboDecorator extends VehicleDecorator {
    public TurboDecorator(DVehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void move(VehicleLogger logger) {
        System.out.println("[TURBO] Turbo boost engaged!");
        decoratedVehicle.move(logger);
        System.out.println("[TURBO] Speed increased by 50%");
    }

    @Override
    public String getType() {
        return decoratedVehicle.getType() + " + Turbo";
    }

    @Override
    public double getPrice() {
        return decoratedVehicle.getPrice() + 5000.0;
    }
}