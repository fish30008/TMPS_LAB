package Lab3.domain.composite;
import Lab3.domain.models.IVehicle;

public class VehicleLeaf extends VehicleComponent {
    private final IVehicle vehicle;

    public VehicleLeaf(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void displayInfo(int indentLevel) {
        String indent = "  ".repeat(indentLevel);
        System.out.println(indent + "└─ " + vehicle.getType());
    }
}