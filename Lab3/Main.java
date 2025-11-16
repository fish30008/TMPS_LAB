package Lab3;
import java.util.*;

//apater pattenr ## client interface what it expects
interface Rechargeable {
    void plugIn();
}

//here is what i have
interface FuelPowered {
    void fillTank(int volume);
}

//Adapted Class
class DieselTruck implements FuelPowered {

    private int volume;

    public DieselTruck(int volume) {
        this.volume = volume;
    }
    @Override
    public void fillTank(int volume) {
        this.volume = volume;
        System.out.println("Filling up" + volume + "litres in the diesel tank...");
    }

    public int getVolume() {
        return volume;
    }
}

//Adapter
class RechargeAdapter implements Rechargeable {
    private final FuelPowered fuelMachine;
    private final int fuelVolume;

    public RechargeAdapter(FuelPowered fuelMachine, int fuelVolume) {
        this.fuelMachine = fuelMachine;
        this.fuelVolume = fuelVolume;
    }

    @Override
    public void plugIn() {
        System.out.print("Adapter converts electrical input into fuel process: ");
        fuelMachine.fillTank(this.fuelVolume);
    }
}

class ElectricCarsWorld {
    public void rechargeVehicle(Rechargeable vehicle) {
        System.out.println("Electric station ready!");
        vehicle.plugIn();
        System.out.println("Recharge complete!\n");
    }
}

//## adapter ends



// operations that are common for somple and complex elements of tree
abstract class MachineComponent {
    public abstract void showDetails(int level);
}
// the leaf class
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


//all componnent classes can extend other components aka delegates the actual job to childern
class MachineUnit extends MachineComponent {
    private final String label;
    private final List<MachineComponent> subComponents = new ArrayList<>();

    public MachineUnit(String label) {
        this.label = label;
    }

    public void attach(MachineComponent component) {
        subComponents.add(component);
    }

    public void detach(MachineComponent component) {
        subComponents.remove(component);
    }

    @Override
    public void showDetails(int level) {
        System.out.println(" ".repeat(level) + "+ Unit: " + label);
        for (MachineComponent component : subComponents) {
            component.showDetails(level + 2);
        }
    }
}



// implementing decorator -- Cline can wrap components in multiple layers of decorators
//  as long as it works with all obejcts
interface VehicleBase {
    String description();
    double price();
}

//conccrete components
class SimpleVan implements VehicleBase {
    @Override
    public String description() {
        return "Standard VehicleBase";
    }

    @Override
    public double price() {
        return 25000;
    }
}

// Decorator base with wrapper
abstract class VehicleUpgrade implements VehicleBase {
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


class BMW_Subscription extends VehicleUpgrade {
    public BMW_Subscription(VehicleBase vehicle) {
        super(vehicle);
    }

    @Override
    public String description() {
        return vehicle.description() + " + subscription";
    }

    @Override
    public double price() {
        return vehicle.price() + 10;
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

        //Adapters pattern
        ElectricCarsWorld station = new ElectricCarsWorld();
        DieselTruck truck = new DieselTruck(10);
        RechargeAdapter rechargeAdapter = new RechargeAdapter(truck, 59);
        station.rechargeVehicle(rechargeAdapter);

        // --- Composite Pattern Demo ---
        System.out.println("\nCOMPOSITE PATTERN DEMO:");
        MachineUnit powerUnit = new MachineUnit("Power Unit");
        powerUnit.attach(new MachinePart("StockParts"));
        powerUnit.attach(new MachinePart("Compressor"));
        powerUnit.attach(new MachinePart("Cooling Fan"));
        powerUnit.attach(new MachinePart("Down pipe"));
        powerUnit.detach(new MachinePart("StockParts"));

        MachineUnit vehicleBody = new MachineUnit("Vehicle Body");
        vehicleBody.attach(new MachinePart("Chassis Frame"));
        vehicleBody.attach(powerUnit);

        vehicleBody.showDetails(0);

        // --- Decorator Pattern Demo ---
        System.out.println("\nDECORATOR PATTERN DEMO:");
        VehicleBase deliveryVan = new SimpleVan();
        deliveryVan = new LeatherSeats(deliveryVan);
        deliveryVan = new NavigationSystem(deliveryVan);
        deliveryVan = new BMW_Subscription(deliveryVan);

        System.out.println(deliveryVan.description() + " costs $ = " + deliveryVan.price());
    }
}
