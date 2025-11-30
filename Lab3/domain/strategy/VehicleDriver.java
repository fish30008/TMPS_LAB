package Lab3.domain.strategy;
import Lab3.utilities.VehicleLogger;

// Context - uses different strategies
public class VehicleDriver {
    private DrivingStrategy strategy;
    private String vehicleType;
    private VehicleLogger logger = VehicleLogger.getInstance();

    public VehicleDriver(String vehicleType) {
        this.vehicleType = vehicleType;
        this.strategy = new NormalDriving(); // Default strategy
    }

    public void setDrivingStrategy(DrivingStrategy strategy) {
        this.strategy = strategy;
        logger.log("Switched to " + strategy.getStrategyName() + " mode for " + vehicleType);
    }

    public void performDrive() {
        System.out.println("\n[" + vehicleType + " - " + strategy.getStrategyName() + " MODE]");
        strategy.drive();
    }

    public String getCurrentStrategy() {
        return strategy.getStrategyName();
    }
}
