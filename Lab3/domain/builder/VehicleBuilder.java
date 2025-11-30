package Lab3.domain.builder;

public interface VehicleBuilder {
    void buildType(String s);
    void buildWheels(String s);
    void buildEngine(String s);
    void buildColor(String s);
    Vehicle getVehicle();
}