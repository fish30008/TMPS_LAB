package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public interface IVehicle {
    void move(VehicleLogger logger);
    String getType();
}