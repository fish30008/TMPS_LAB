package Lab3.domain.iterator;
import Lab3.domain.models.IVehicle;
import java.util.*;

// Concrete collection - manages vehicles and creates iterators
public class VehicleFleetCollection implements VehicleCollection {
    private List<IVehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(IVehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<IVehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public VehicleIterator createTypeIterator(String vehicleType) {
        return new TypeFilterIterator(this, vehicleType);
    }

    @Override
    public VehicleIterator createAllIterator() {
        return new AllVehiclesIterator(this);
    }
}