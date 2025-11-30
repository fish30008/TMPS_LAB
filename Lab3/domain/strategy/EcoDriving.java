package Lab3.domain.strategy;

// Concrete strategy - eco driving mode
public class EcoDriving implements DrivingStrategy {
    @Override
    public void drive() {
        System.out.println("  🌱 Eco driving mode:");
        System.out.println("     - Speed: Low (50 km/h)");
        System.out.println("     - Fuel efficiency: Maximum");
        System.out.println("     - Emissions: Minimal");
        System.out.println("     - Battery regeneration: Active");
    }

    @Override
    public String getStrategyName() {
        return "ECO";
    }
}