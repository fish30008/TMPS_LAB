package Lab3.domain.state;

// Concrete state - vehicle is moving
public class MovingState implements VehicleState {
    private VehicleContext context;

    public MovingState(VehicleContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        System.out.println("Vehicle is already moving");
    }

    @Override
    public void stop() {
        System.out.println("Braking... Vehicle is now IDLE");
        context.setState(context.getIdleState());
    }

    @Override
    public void accelerate() {
        System.out.println("Accelerating faster!");
    }

    @Override
    public String getStateName() {
        return "MOVING";
    }
}
