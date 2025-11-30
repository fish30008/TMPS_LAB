package Lab3.domain.iterator;

// Collection interface - creates iterators
public interface VehicleCollection {
    VehicleIterator createTypeIterator(String vehicleType);
    VehicleIterator createAllIterator();
    void addVehicle(Lab3.domain.models.IVehicle vehicle);
}
