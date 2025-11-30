package Lab3.domain.strategy;

// Concrete strategy - normal driving mode
public class NormalDriving implements DrivingStrategy {
    @Override
    public void drive() {
        System.out.println("  🚗 Normal driving mode:");
        System.out.println("     - Speed: Moderate (60 km/h)");
        System.out.println("     - Fuel efficiency: Optimal");
        System.out.println("     - Comfort: High");
    }

    @Override
    public String getStrategyName() {
        return "NORMAL";
    }
}