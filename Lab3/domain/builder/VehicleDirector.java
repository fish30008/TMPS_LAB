package Lab3.domain.builder;

public class VehicleDirector {
    private final VehicleBuilder builder;

    public VehicleDirector(VehicleBuilder builder) {
        this.builder = builder;
    }

    public Vehicle constructSportsCar() {
        builder.buildType("Sports Car");
        builder.buildWheels("4 Racing Wheels");
        builder.buildEngine("V8 Twin-Turbo");
        builder.buildColor("Red");
        return builder.getVehicle();
    }

    public Vehicle constructFamilyCar() {
        builder.buildType("Family Car");
        builder.buildWheels("4 Standard Wheels");
        builder.buildEngine("1.6L Inline-4");
        builder.buildColor("Silver");
        return builder.getVehicle();
    }
}