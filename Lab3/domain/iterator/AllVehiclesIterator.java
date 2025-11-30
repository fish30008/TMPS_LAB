package Lab3.domain.iterator;
import Lab3.domain.models.IVehicle;

// Concrete iterator - iterates through all vehicles
public class AllVehiclesIterator implements VehicleIterator {
    private VehicleFleetCollection collection;
    private int currentPosition = 0;

    public AllVehiclesIterator(VehicleFleetCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < collection.getVehicles().size();
    }

    @Override
    public IVehicle getNext() {
        if (!hasNext()) {
            return null;
        }
        return collection.getVehicles().get(currentPosition++);
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}