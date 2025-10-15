package Lab3;
import java.util.*;


interface Rechargeable {
    void plugIn();
}
interface FuelPowered {
    void fillTank();
}

class DieselTruck implements FuelPowered {
    @Override
    public void fillTank() {
        System.out.println("Filling up the diesel tank...");
    }
}

class RechargeAdapter implements Rechargeable {
    private final FuelPowered fuelMachine;

    public RechargeAdapter(FuelPowered fuelMachine) {
        this.fuelMachine = fuelMachine;
    }

    @Override
    public void plugIn() {
        System.out.print("Adapter converts electrical input into fuel process: ");
        fuelMachine.fillTank();
    }
}


abstract class MachineComponent {
    public abstract void showDetails(int level);
}

class MachinePart extends MachineComponent {
    private final String label;

    public MachinePart(String label) {
        this.label = label;
    }

    @Override
    public void showDetails(int level) {
        System.out.println(" ".repeat(level) + "- Component: " + label);
    }
}

class MachineUnit extends MachineComponent {
    private final String label;
    private final List<MachineComponent> subComponents = new ArrayList<>();

    public MachineUnit(String label) {
        this.label = label;
    }

    public void attach(MachineComponent component) {
        subComponents.add(component);
    }

    @Override
    public void showDetails(int level) {
        System.out.println(" ".repeat(level) + "+ Unit: " + label);
        for (MachineComponent component : subComponents) {
            component.showDetails(level + 2);
        }
    }
}


abstract class VehicleBase {
    public abstract String description();
    public abstract double price();
}

class SimpleVan extends VehicleBase {
    @Override
    public String description() {
        return "Standard Van";
    }

    @Override
    public double price() {
        return 25000;
    }
}

// Decorator base
abstract class VehicleUpgrade extends VehicleBase {
    protected final VehicleBase vehicle;

    public VehicleUpgrade(VehicleBase vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String description() {
        return vehicle.description();
    }

    @Override
    public double price() {
        return vehicle.price();
    }
}

// Concrete Decorators
class LeatherSeats extends VehicleUpgrade {
    public LeatherSeats(VehicleBase vehicle) {
        super(vehicle);
    }

    @Override
    public String description() {
        return vehicle.description() + " + Leather Seats";
    }

    @Override
    public double price() {
        return vehicle.price() + 1200;
    }
}

class NavigationSystem extends VehicleUpgrade {
    public NavigationSystem(VehicleBase vehicle) {
        super(vehicle);
    }

    @Override
    public String description() {
        return vehicle.description() + " + GPS Navigation";
    }

    @Override
    public double price() {
        return vehicle.price() + 950;
    }
}


public class Main {
    public static void main(String[] args) {

        // --- Adapter Pattern Demo ---
        System.out.println("ADAPTER PATTERN DEMO:");
        Rechargeable hybridTruck = new RechargeAdapter(new DieselTruck());
        hybridTruck.plugIn();

        // --- Composite Pattern Demo ---
        System.out.println("\nCOMPOSITE PATTERN DEMO:");
        MachineUnit powerUnit = new MachineUnit("Power Unit");
        powerUnit.attach(new MachinePart("Compressor"));
        powerUnit.attach(new MachinePart("Cooling Fan"));

        MachineUnit vehicleBody = new MachineUnit("Vehicle Body");
        vehicleBody.attach(new MachinePart("Chassis Frame"));
        vehicleBody.attach(powerUnit);

        vehicleBody.showDetails(0);

        // --- Decorator Pattern Demo ---
        System.out.println("\nDECORATOR PATTERN DEMO:");
        VehicleBase deliveryVan = new SimpleVan();
        deliveryVan = new LeatherSeats(deliveryVan);
        deliveryVan = new NavigationSystem(deliveryVan);

        System.out.println(deliveryVan.description() + " costs $" + deliveryVan.price());
    }
}
