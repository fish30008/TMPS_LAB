package Lab3.domain.models;
import Lab3.utilities.VehicleLogger;

public interface DVehicle {
    void move(VehicleLogger logger);
    String getType();

    double getPrice();
}