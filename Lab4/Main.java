package Lab4;
import java.util.*;
// Observer Interface
interface VehicleListener {
    void onEvent(String eventName);
}

class VehicleEventCenter {
    private List<VehicleListener> listeners = new ArrayList<>();

    public void subscribe(VehicleListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(VehicleListener listener) {
        listeners.remove(listener);
    }

    public void broadcast(String eventName) {
        for (VehicleListener listener : listeners) {
            listener.onEvent(eventName);
        }
    }
}

class VehicleDashboard implements VehicleListener {
    @Override
    public void onEvent(String eventName) {
        System.out.println("Dashboard notification: Vehicle " + eventName);
    }
}

class SedanDashboard implements VehicleListener {
    @Override
    public void onEvent(String eventName) {
        System.out.println("Sedan notification: Vehicle " + eventName);
    }
}

// =======================
// STRATEGY PATTERN
// =======================

interface BrakingMode {
    void applyBrake();
}

class NormalBraking implements BrakingMode {
    @Override
    public void applyBrake() {
        System.out.println("Performing smooth braking at 70% pressure.");
    }
}

class SmartABSBraking implements BrakingMode {
    @Override
    public void applyBrake() {
        System.out.println("Executing smart ABS braking with controlled pulses.");
    }
}


class Drift implements BrakingMode {
    @Override
    public void applyBrake() {
        System.out.println("Ia criciu macan , vi - ....");
    }
}

// =======================
// COMMAND PATTERN IS EHRE
// =======================

interface VehicleCommand {
    void run();
}

class StartEngineCommand implements VehicleCommand {
    private VehicleEventCenter eventCenter;

    public StartEngineCommand(VehicleEventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    @Override
    public void run() {
        System.out.println("Ignition initiated...");
        eventCenter.broadcast("engine started");
    }
}

class EngageBrakesCommand implements VehicleCommand {
    private BrakingMode brakingMode;

    public EngageBrakesCommand(BrakingMode brakingMode) {
        this.brakingMode = brakingMode;
    }

    @Override
    public void run() {
        System.out.print("Braking system engaged: ");
        brakingMode.applyBrake();
    }
}


class Automobile {
    private VehicleEventCenter eventCenter = new VehicleEventCenter();
    private BrakingMode brakingMode;

    public Automobile(BrakingMode brakingMode) {
        this.brakingMode = brakingMode;
        // Default observer
        //eventCenter.subscribe(new VehicleDashboard());
    }

    public void addListener(VehicleListener listener) {
        eventCenter.subscribe(listener);
    }

    public void startEngine() {
        new StartEngineCommand(eventCenter).run();
    }

    public void pressBrakes() {
        new EngageBrakesCommand(brakingMode).run();
    }

    public void changeBrakingMode(BrakingMode newMode) {
        this.brakingMode = newMode;
    }
}


public class Main {
    public static void main(String[] args) {
        Automobile sedan = new Automobile(new NormalBraking());
        Automobile suv = new Automobile(new SmartABSBraking());

        // First observation pattern ))) we can even spam
        suv.addListener(new VehicleDashboard());
        suv.addListener(new VehicleDashboard());
        suv.addListener(new VehicleDashboard());



        System.out.println("=== Sedan Operation Flow ===");
        sedan.startEngine();
        sedan.pressBrakes();

        System.out.println("\n=== SUV Operation Flow ===");
        suv.startEngine();
        suv.pressBrakes();

        System.out.println("\n=== Sedan switches to Smart ABS Braking ===");
        sedan.changeBrakingMode(new SmartABSBraking()); // Strategy pattern
        sedan.pressBrakes();

        sedan.changeBrakingMode(new Drift());
        sedan.pressBrakes();
    }
}
