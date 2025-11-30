package Lab3.client;

import Lab3.domain.models.*;
import Lab3.domain.factories.VehicleFactory;
import Lab3.domain.builder.*;
import Lab3.domain.adapter.*;
import Lab3.domain.composite.*;
import Lab3.domain.decorator.*;
import Lab3.domain.iterator.*;
import Lab3.utilities.VehicleLogger;
import Lab3.domain.state.*;
import Lab3.domain.strategy.*;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleLogger logger = VehicleLogger.getInstance();
        logger.log("=== Vehicle Management System Started ===\n");

        // ===== CREATIONAL PATTERNS (from Lab2) =====
        System.out.println("=== 1. FACTORY PATTERN (Creational) ===");
        IVehicle car = VehicleFactory.createVehicle("car");
        IVehicle bike = VehicleFactory.createVehicle("bicycle");
        car.move(logger);
        bike.move(logger);

        System.out.println("\n=== 2. BUILDER PATTERN (Creational) ===");
        VehicleBuilder builder = new CarBuilder();
        VehicleDirector director = new VehicleDirector(builder);

        Lab3.domain.builder.Vehicle sportsCar = director.constructSportsCar();
        System.out.println("Built: " + sportsCar);

        Lab3.domain.builder.Vehicle familyCar = director.constructFamilyCar();
        System.out.println("Built: " + familyCar);

        // ===== STRUCTURAL PATTERNS (Lab3) =====

        System.out.println("\n=== 3. ADAPTER PATTERN (Structural) ===");
        // Problem: Gas car uses fuel, but we have electric charging stations
        GasCar gasCar = (GasCar) VehicleFactory.createVehicle("gas");
        System.out.println("Gas car fuel level: " + gasCar.getFuelLevel());

        // Solution: Use adapter to charge gas car at electric station
        ChargingStation adapter = new ElectricVehicleAdapter(gasCar);
        adapter.charge();
        System.out.println("After adapter charge: " + gasCar.getFuelLevel());

        System.out.println("\n=== 4. COMPOSITE PATTERN (Structural) ===");
        // Create vehicle fleet hierarchy
        VehicleFleet mainFleet = new VehicleFleet("Main Company Fleet");

        VehicleFleet deliveryFleet = new VehicleFleet("Delivery Division");
        deliveryFleet.add(new VehicleLeaf(VehicleFactory.createVehicle("car")));
        deliveryFleet.add(new VehicleLeaf(VehicleFactory.createVehicle("car")));

        VehicleFleet executiveFleet = new VehicleFleet("Executive Division");
        executiveFleet.add(new VehicleLeaf(VehicleFactory.createVehicle("sports")));
        executiveFleet.add(new VehicleLeaf(VehicleFactory.createVehicle("electric")));

        mainFleet.add(deliveryFleet);
        mainFleet.add(executiveFleet);
        mainFleet.add(new VehicleLeaf(VehicleFactory.createVehicle("bicycle")));

        System.out.println("Fleet Structure:");
        mainFleet.displayInfo(0);

        System.out.println("\n=== 5. DECORATOR PATTERN (Structural) ===");
        // Start with basic car
        IVehicle basicCar =  VehicleFactory.createVehicle("base_car");
        System.out.println("Basic: " + basicCar.getType());

        // Add GPS
        DVehicle dv = (DVehicle) basicCar;
        VehicleDecorator carWithGPS = new GPSDecorator(dv);
        System.out.println("\nEnhanced: " + carWithGPS.getType());
        System.out.println("Total Price: $" + carWithGPS.getPrice());

        // Add Turbo to GPS car
        VehicleDecorator fullyLoaded = new TurboDecorator(carWithGPS);
        System.out.println("\nFully loaded: " + fullyLoaded.getType());
        System.out.println("Total Price: $" + fullyLoaded.getPrice());

        // Add Armor too
        VehicleDecorator military = new ArmorDecorator(fullyLoaded);
        System.out.println("\nMilitary spec: " + military.getType());
        System.out.println("Total Price: $" + military.getPrice());

        System.out.println("\nMoving military vehicle:");
        military.move(logger);


        System.out.println("\n=== 6. ITERATOR PATTERN (Behavioral) ===");

// 1. Create fleet collection using Factory pattern
        VehicleFleetCollection fleetCollection = new VehicleFleetCollection();
        fleetCollection.addVehicle(VehicleFactory.createVehicle("car"));      // Factory integration
        fleetCollection.addVehicle(VehicleFactory.createVehicle("car"));
        fleetCollection.addVehicle(VehicleFactory.createVehicle("bicycle"));
        fleetCollection.addVehicle(VehicleFactory.createVehicle("sports"));
        fleetCollection.addVehicle(VehicleFactory.createVehicle("electric"));
        fleetCollection.addVehicle(VehicleFactory.createVehicle("car"));
        fleetCollection.addVehicle(VehicleFactory.createVehicle("gas"));      // Adapter-compatible vehicle

// 2. Create notifier client

        List<IVehicle> allVehicles = fleetCollection.getVehicles();
        System.out.println("Here is all what is inside fleet collection:");

        for (IVehicle oneVehicle : allVehicles) {
            System.out.println(oneVehicle.getType());   // relies on toString()
        }

        VehicleNotifier notifier = new VehicleNotifier();

// 3. Iterate specific vehicle types
        VehicleIterator carIterator = fleetCollection.createTypeIterator("Car");
        notifier.notifyMaintenance(carIterator, "Oil change required");

// 4. Iterate all vehicles
        VehicleIterator allIterator = fleetCollection.createAllIterator();
        notifier.performInspection(allIterator);

// 5. Iterate electric vehicles
        VehicleIterator electricIterator = fleetCollection.createTypeIterator("Electric Car");
        notifier.notifyMaintenance(electricIterator, "Battery health check");

        logger.log("\n=== Vehicle Management System Ended ===");


        IVehicle vehicle = VehicleFactory.createVehicle("sports");
        VehicleContext state = new VehicleContext(vehicle.getType());
        state.start();
        state.accelerate();
        String result = state.getCurrentState();
        System.out.println("current state ->  " + result);
        state.stop();


        VehicleDriver driver = new VehicleDriver("Car");
        driver.setDrivingStrategy(new NormalDriving());
        driver.performDrive();

// Switch to sport
        driver.setDrivingStrategy(new SportDriving());
        driver.performDrive();

// Switch to eco
        driver.setDrivingStrategy(new EcoDriving());
        driver.performDrive();
    }
}