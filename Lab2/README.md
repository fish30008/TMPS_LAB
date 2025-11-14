### **Objective**

This project demonstrates **three Creational Design Patterns** — **Factory**, **Singleton**, and **Builder** — implemented in Java.
The goal is to manage **vehicle creation, configuration, and logging** efficiently, while maintaining code flexibility and scalability.

---

## 🏭 **1. Factory Pattern**

**Intent:**
To create `Vehicle` objects (Car, Bicycle) **without exposing instantiation logic** to the client.

**Implementation:**
The `VehicleFactory` class decides which concrete class to instantiate based on input parameters.

```java
public class VehicleFactory {
    public static IVehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car": return new Car();
            case "bicycle": return new Bicycle();
            default: throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
```

**Flow:**

* Client requests a type (`"car"`, `"bicycle"`)
* Factory creates and returns the correct object
* The client remains **independent of concrete classes**

**Benefit:**

* Simplifies object creation
* Promotes code extensibility (add new vehicle types easily)

---

## 🧠 **2. Singleton Pattern**

**Intent:**
Ensure **only one instance** of `VehicleLogger` exists throughout the program.

**Implementation:**

```java
public class VehicleLogger {
    private static VehicleLogger instance;
    private VehicleLogger() {}
    public static synchronized VehicleLogger getInstance() {
        if (instance == null) instance = new VehicleLogger();
        return instance;
    }
}
```

**Flow:**

* First call creates the instance
* Subsequent calls return the same shared instance

**Benefit:**

* Centralized logging system
* Thread-safe single access point for log messages

---

## 🏗️ **3. Builder Pattern**

**Intent:**
Simplify the **construction of complex objects** by building them step-by-step.

**Implementation:**

```java
class Vehicle {
    private String type;
    private String wheels;
    private String engine;
    private String color;

    public void setType(String type) { this.type = type; }
    public void setWheels(String wheels) { this.wheels = wheels; }
    public void setEngine(String engine) { this.engine = engine; }
    public void setColor(String color) { this.color = color; }
}


//here is concrete builder that implements our interface builder
class CarBuilder implements VehicleBuilder {
    private final Vehicle vehicle = new Vehicle();

    public void buildType(String type) {
        vehicle.setType(type);
    }
    public void buildWheels(String wheels) {
        vehicle.setWheels(wheels);
    }
    public void buildEngine(String engine) {
        vehicle.setEngine(engine);
    }
    public void buildColor(String color) {
        vehicle.setColor(color);
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}

    // ===== Director Class =====
    class VehicleDirector {
        private final VehicleBuilder builder;

        public VehicleDirector(VehicleBuilder builder) {
            this.builder = builder;
        }

        public Vehicle construct() {
            Scanner scanner = new Scanner(System.in);
            List<String> flags = new ArrayList<>();
            Map<String, String> flagValues = new HashMap<>();

            System.out.println("Enter which parts to build (type, wheels, engine, color). Type 'done' to finish:");
            while (true) {
                System.out.print("Part: ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("done")) break;
                flags.add(input);
            }

            // STEP 2: Enter values for those parts
            System.out.println("\nEnter values for selected parts:");
            for (String flag : flags) {
                System.out.print(flag + " value: ");
                String value = scanner.nextLine().trim();
                flagValues.put(flag, value);
            }

            // STEP 3: Build according to chosen parts and entered values
            for (String flag : flags) {
                switch (flag) {
                    case "type":
                        builder.buildType(flagValues.get("type"));
                        break;
                    case "wheels":
                        try {
                            builder.buildWheels(flagValues.get("wheels"));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number for wheels, skipping...");
                        }
                        break;
                    case "engine":
                        builder.buildEngine(flagValues.get("engine"));
                        break;
                    case "color":
                        builder.buildColor(flagValues.get("color"));
                        break;
                    default:
                        System.out.println("Unknown flag: " + flag);
                }
            }

            return builder.getVehicle();
        }
    }
```


**Benefit:**

* Separates construction logic from representation
* Flexible and readable object creation

---

## 🚀 **Code Flow Summary**

1. **Main class** demonstrates direct instantiation, factory creation, and builder-based construction.
2. **Logger (Singleton)** records messages globally.
3. **Factory** and **Builder** collaborate — Builder uses Factory to finalize vehicle creation.

---

## 🧩 **Conclusion**

This project integrates three core **Creational Design Patterns** cohesively:

* **Factory** abstracts creation logic
* **Singleton** ensures a single, global instance
* **Builder** provides structured, flexible object construction

