package Lab3.domain.composite;
import java.util.*;

public class VehicleFleet extends VehicleComponent {
    private final String fleetName;
    private final List<VehicleComponent> components = new ArrayList<>();

    public VehicleFleet(String fleetName) {
        this.fleetName = fleetName;
    }

    @Override
    public void add(VehicleComponent component) {
        components.add(component);
    }

    @Override
    public void remove(VehicleComponent component) {
        components.remove(component);
    }

    @Override
    public void displayInfo(int indentLevel) {
        String indent = "  ".repeat(indentLevel);
        System.out.println(indent + "┌─ Fleet: " + fleetName);
        for (VehicleComponent component : components) {
            component.displayInfo(indentLevel + 1);
        }
    }
}