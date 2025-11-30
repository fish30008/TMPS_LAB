package Lab3.domain.iterator;
import Lab3.domain.models.IVehicle;
import Lab3.utilities.VehicleLogger;

// Client class - uses iterator to perform operations on vehicles
public class VehicleNotifier {
    private VehicleLogger logger = VehicleLogger.getInstance();

    public void notifyMaintenance(VehicleIterator iterator, String message) {
        logger.log("Starting maintenance notifications...");
        int count = 0;
        while (iterator.hasNext()) {
            IVehicle vehicle = iterator.getNext();
            System.out.println("  → Notifying " + vehicle.getType() + ": " + message);
            count++;
        }
        logger.log("Sent " + count + " maintenance notifications");
    }

    public void performInspection(VehicleIterator iterator) {
        logger.log("Starting vehicle inspections...");
        int count = 0;
        while (iterator.hasNext()) {
            IVehicle vehicle = iterator.getNext();
            System.out.println("  ✓ Inspecting " + vehicle.getType() + " - Status: OK");
            count++;
        }
        logger.log("Completed " + count + " inspections");
    }
}