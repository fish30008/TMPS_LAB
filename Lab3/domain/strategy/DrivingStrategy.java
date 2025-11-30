package Lab3.domain.strategy;

// Strategy interface - defines different driving algorithms
public interface DrivingStrategy {
    void drive();
    String getStrategyName();
}