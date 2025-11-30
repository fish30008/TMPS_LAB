package Lab3.domain.strategy;

// Concrete strategy - off-road driving mode
public class OffRoadDriving implements DrivingStrategy {
    @Override
    public void drive() {
        System.out.println("  🏔️ Off-road driving mode:");
        System.out.println("     - Speed: Variable (terrain-dependent)");
        System.out.println("     - Traction control: Enhanced");
        System.out.println("     - Suspension: Raised");
        System.out.println("     - 4WD: Engaged");
    }

    @Override
    public String getStrategyName() {
        return "OFF-ROAD";
    }
}