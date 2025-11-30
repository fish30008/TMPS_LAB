package Lab3.domain.strategy;

// Concrete strategy - sport driving mode
public class SportDriving implements DrivingStrategy {
    @Override
    public void drive() {
        System.out.println("  🏎️ Sport driving mode:");
        System.out.println("     - Speed: High (120 km/h)");
        System.out.println("     - Fuel efficiency: Low");
        System.out.println("     - Acceleration: Maximum");
        System.out.println("     - Engine response: Aggressive");
    }

    @Override
    public String getStrategyName() {
        return "SPORT";
    }
}