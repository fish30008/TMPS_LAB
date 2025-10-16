### **Patterns Implemented**

* **Observer Pattern**
* **Strategy Pattern**
* **Command Pattern**

---

## **1. Observer Pattern — Vehicle Event Notifications**

### **Concept**

The **Observer Pattern** establishes a **one-to-many relationship** between objects so that when one object changes state, all its dependents are notified automatically.

In this project, the *subject* (publisher) is the `VehicleEventCenter`, and the *observers* (subscribers) are dashboards that react to vehicle events (e.g., engine started).

### **Code Flow**

* **Interface**: `VehicleListener` defines `onEvent(String eventName)` — the method each observer must implement.

* **Subject (Observable)**:

  * `VehicleEventCenter` holds a list of `VehicleListener` subscribers.
  * Methods:

    * `subscribe()` → add observers
    * `unsubscribe()` → remove observers
    * `broadcast(event)` → notify all observers

* **Concrete Observers**:

  * `VehicleDashboard` and `SedanDashboard` implement the interface and react to events differently (printing customized messages).

* **Usage**:

  ```java
  suv.addListener(new VehicleDashboard());
  suv.startEngine();  // triggers broadcast -> notifies dashboards
  ```

### **Purpose**

This allows **loose coupling** between the vehicle’s internal logic and any external components (e.g., dashboards, loggers, telemetry).
Adding or removing observers doesn’t affect the core vehicle logic.

---

## **2. Strategy Pattern — Braking Behaviors**

### **Concept**

The **Strategy Pattern** enables selecting an algorithm (or behavior) at runtime.
Instead of hardcoding a braking mechanism inside the `Automobile` class, different braking strategies are **encapsulated** as independent classes implementing a common interface.

### **Code Flow**

* **Interface**:
  `BrakingMode` → defines a single method `applyBrake()`.

* **Concrete Strategies**:

  * `NormalBraking` — standard smooth braking
  * `SmartABSBraking` — intelligent, ABS-based braking
  * `Drift` — humorous drift-style brake

* **Context**:
  The `Automobile` class holds a reference to a `BrakingMode`.
  It delegates the braking behavior to this strategy:

  ```java
  new EngageBrakesCommand(brakingMode).run();
  ```

* **Runtime Change**:

  ```java
  sedan.changeBrakingMode(new SmartABSBraking());
  sedan.pressBrakes();
  ```

### **Purpose**

* Enables **dynamic behavior change** without modifying existing code.
* Encourages **Open/Closed Principle** — new braking strategies can be added without changing `Automobile`.
* Clean separation of logic → improves maintainability and testability.

---

## **3. Command Pattern — Vehicle Operations as Commands**

### **Concept**

The **Command Pattern** encapsulates a request as an object, allowing parameterization of clients with different requests, queuing, and undo/redo operations.

Each command represents a **vehicle action** — starting the engine or engaging brakes.

### **Code Flow**

* **Interface**: `VehicleCommand` defines `run()`.

* **Concrete Commands**:

  * `StartEngineCommand` — encapsulates logic to start the engine and notify observers via the event center.
  * `EngageBrakesCommand` — encapsulates braking logic (delegating to the current braking strategy).

* **Execution**:

  ```java
  new StartEngineCommand(eventCenter).run();
  new EngageBrakesCommand(brakingMode).run();
  ```

### **Purpose**

* Decouples **command initiation** (UI or user action) from **execution logic**.
* Enables **flexibility**, such as adding command history or macros later.
* Promotes **clean separation of concerns**.

---

## **4. Integrated Flow**

### **Main.java Demonstration**

```java
Automobile sedan = new Automobile(new NormalBraking());
Automobile suv = new Automobile(new SmartABSBraking());

// Register multiple observers
suv.addListener(new VehicleDashboard());
suv.addListener(new VehicleDashboard());

// Observer Pattern in action
suv.startEngine();  // notifies dashboards

// Command + Strategy combined
sedan.startEngine();  
sedan.pressBrakes();  

// Switch braking strategy dynamically
sedan.changeBrakingMode(new SmartABSBraking());
sedan.pressBrakes();

// Apply Drift braking
sedan.changeBrakingMode(new Drift());
sedan.pressBrakes();
```

**Output Example:**

```
=== Sedan Operation Flow ===
Ignition initiated...
Braking system engaged: Performing smooth braking at 70% pressure.

=== SUV Operation Flow ===
Ignition initiated...
Dashboard notification: Vehicle engine started
Dashboard notification: Vehicle engine started
Dashboard notification: Vehicle engine started
Braking system engaged: Executing smart ABS braking with controlled pulses.

=== Sedan switches to Smart ABS Braking ===
Braking system engaged: Executing smart ABS braking with controlled pulses.
Braking system engaged: Ia criciu macan , vi - ....
```


## **6. Summary**

This project elegantly demonstrates **three fundamental Behavioral Design Patterns** working together:

* The **Observer Pattern** allows multiple dashboards to react automatically to events.
* The **Strategy Pattern** enables vehicles to switch braking logic dynamically.
* The **Command Pattern** encapsulates and modularizes vehicle actions (start, brake).

Each pattern promotes **extensibility**, **reusability**, and **maintainable architecture**, aligning with the **SOLID principles** of object-oriented design.

---
