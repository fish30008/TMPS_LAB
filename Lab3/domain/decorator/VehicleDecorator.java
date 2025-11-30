package Lab3.domain.decorator;
import Lab3.domain.models.DVehicle;
import Lab3.utilities.VehicleLogger;

// DECORATOR: Adds features to vehicles dynamically
public abstract class VehicleDecorator implements DVehicle {
    protected DVehicle decoratedVehicle;

    public VehicleDecorator(DVehicle vehicle) {
        this.decoratedVehicle = vehicle;
    }

    @Override
    public void move(VehicleLogger logger) {
        decoratedVehicle.move(logger);
    }

    @Override
    public String getType() {
        return decoratedVehicle.getType();
    }

    @Override
    public double getPrice() {
        return decoratedVehicle.getPrice();
    }
}