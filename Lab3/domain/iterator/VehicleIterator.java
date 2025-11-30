package Lab3.domain.iterator;
import Lab3.domain.models.IVehicle;

// Iterator interface - defines traversal operations
public interface VehicleIterator {
    boolean hasNext();
    IVehicle getNext();
    void reset();
}