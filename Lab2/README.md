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
public class VehicleBuilder {
    private String type = "car";
    public VehicleBuilder setType(String type) {
        this.type = type;
        return this;
    }
    public IVehicle build() {
        return VehicleFactory.createVehicle(type);
    }
}
```

**Flow:**

* `VehicleBuilder` receives parameters incrementally
* When `.build()` is called, it uses `VehicleFactory` to create the object

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

