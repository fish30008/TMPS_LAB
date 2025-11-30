package Lab3.domain.iterator;
import Lab3.domain.models.IVehicle;
import java.util.*;

// Concrete iterator - filters by vehicle type
public class TypeFilterIterator implements VehicleIterator {
    private VehicleFleetCollection collection;
    private String targetType;
    private int currentPosition = 0;
    private List<IVehicle> cache;

    public TypeFilterIterator(VehicleFleetCollection collection, String targetType) {
        this.collection = collection;
        this.targetType = targetType;
    }

    private void lazyInit() {
        if (cache == null) {
            cache = new ArrayList<>();
            for (IVehicle vehicle : collection.getVehicles()) {
                if (vehicle.getType().equalsIgnoreCase(targetType)) {
                    cache.add(vehicle);
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyInit();
        return currentPosition < cache.size();
    }

    @Override
    public IVehicle getNext() {
        if (!hasNext()) {
            return null;
        }
        return cache.get(currentPosition++);
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}