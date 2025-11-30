package Lab3.domain.builder;

public class CarBuilder implements VehicleBuilder {
    private final Vehicle vehicle = new Vehicle();

    public void buildType(String type) {
        vehicle.setType(type);
    }
    public void buildWheels(String wheels) {
        vehicle.setWheels(wheels);
    }
    public void buildEngine(String engine) {
        vehicle.setEngine(engine);
    }
    public void buildColor(String color) {
        vehicle.setColor(color);
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}