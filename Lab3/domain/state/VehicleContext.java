package Lab3.domain.state;

// Context - maintains current state and delegates operations to it
public class VehicleContext {
    private VehicleState stoppedState;
    private VehicleState idleState;
    private VehicleState movingState;

    private VehicleState currentState;
    private String vehicleType;

    public VehicleContext(String vehicleType) {
        this.vehicleType = vehicleType;

        // Initialize all states
        stoppedState = new StoppedState(this);
        idleState = new IdleState(this);
        movingState = new MovingState(this);

        // Start in stopped state
        currentState = stoppedState;
    }

    public void setState(VehicleState state) {
        this.currentState = state;
    }

    public void start() {
        System.out.print("[" + vehicleType + " - " + currentState.getStateName() + "] ");
        currentState.start();
    }

    public void stop() {
        System.out.print("[" + vehicleType + " - " + currentState.getStateName() + "] ");
        currentState.stop();
    }

    public void accelerate() {
        System.out.print("[" + vehicleType + " - " + currentState.getStateName() + "] ");
        currentState.accelerate();
    }

    public String getCurrentState() {
        return currentState.getStateName();
    }

    // Getters for states
    public VehicleState getStoppedState() { return stoppedState; }
    public VehicleState getIdleState() { return idleState; }
    public VehicleState getMovingState() { return movingState; }
}