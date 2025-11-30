package Lab3.domain.state;

// Concrete state - vehicle is idling
public class IdleState implements VehicleState {
    private VehicleContext context;

    public IdleState(VehicleContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        System.out.println("Engine is already running");
    }

    @Override
    public void stop() {
        System.out.println("Engine stopping... Vehicle is now STOPPED");
        context.setState(context.getStoppedState());
    }

    @Override
    public void accelerate() {
        System.out.println("Accelerating... Vehicle is now MOVING");
        context.setState(context.getMovingState());
    }

    @Override
    public String getStateName() {
        return "IDLE";
    }
}
