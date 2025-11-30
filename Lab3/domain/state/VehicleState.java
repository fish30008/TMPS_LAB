package Lab3.domain.state;

// State interface - defines behavior that changes based on state
public interface VehicleState {
    void start();
    void stop();
    void accelerate();
    String getStateName();
}