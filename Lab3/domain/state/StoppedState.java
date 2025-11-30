package Lab3.domain.state;

public class StoppedState implements VehicleState {
    private VehicleContext context;

    public StoppedState(VehicleContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        System.out.println("Engine starting... Vehicle is now IDLE");
        context.setState(context.getIdleState());
    }

    @Override
    public void stop() {
        System.out.println("Vehicle is already stopped");
    }

    @Override
    public void accelerate() {
        System.out.println("Cannot accelerate - vehicle is stopped. Start the engine first!");
    }

    @Override
    public String getStateName() {
        return "STOPPED";
    }
}