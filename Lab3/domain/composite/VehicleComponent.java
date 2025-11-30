package Lab3.domain.composite;

// COMPOSITE: Allows treating individual vehicles and vehicle groups uniformly
public abstract class VehicleComponent {
    public abstract void displayInfo(int indentLevel);

    public void add(VehicleComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(VehicleComponent component) {
        throw new UnsupportedOperationException();
    }
}