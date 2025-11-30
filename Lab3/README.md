## ⚡ Adapter Pattern

### **Intent**

The **Adapter Pattern** allows two incompatible interfaces to work together.
It acts as a **bridge** between an existing class (that cannot be changed) and a new system that expects a different interface.

---

### **Classes in the Example**

* **`Rechargeable`** → Target interface (the new system expects electric-style plug-in vehicles).
* **`FuelPowered`** → Existing interface (used by traditional fuel vehicles).
* **`DieselTruck`** → Concrete class that implements `FuelPowered`.
* **`RechargeAdapter`** → The Adapter that translates calls from `Rechargeable` to `FuelPowered`.

---

### **Codeflow Explanation**

1. The `DieselTruck` class has a method `fillTank()` — the *old behavior*.
2. The `Rechargeable` interface expects `plugIn()` — the *new behavior*.
3. The `RechargeAdapter` holds a reference to a `FuelPowered` object.
4. When `plugIn()` is called on the adapter:

   ```java
   public void plugIn() {
       System.out.print("Adapter converts electrical input into fuel process: ");
       fuelMachine.fillTank();
   }
   ```

   → It translates the electric recharge action into a fuel-tank operation.
5. In the main demo:

   ```java
   Rechargeable hybridTruck = new RechargeAdapter(new DieselTruck());
   hybridTruck.plugIn();
   ```

   The adapter seamlessly integrates an old-style `DieselTruck` into a new electric interface.

---

### **Flow Summary**

> **Electric system (plugIn)** → **Adapter converts call** → **Old system (fillTank)**

This is a perfect example of **interface compatibility without modifying existing code**.

---

## 🧱 Composite Pattern

### **Intent**

The **Composite Pattern** lets you treat **individual objects** and **groups of objects** uniformly.
It is used to represent **part-whole hierarchies**, such as a machine built from multiple components.

---

### **Classes in the Example**

* **`MachineComponent`** → Abstract base class for both parts and units.
* **`MachinePart`** → Represents *leaf* objects (no subcomponents).
* **`MachineUnit`** → Represents *composite* objects (can contain other components).

---

### **Codeflow Explanation**

1. Each `MachineComponent` can call `showDetails(level)` to display itself.
2. A `MachineUnit` maintains a list of `MachineComponent` children:

   ```java
   private List<MachineComponent> subComponents = new ArrayList<>();
   ```
3. It can `attach()` other components — either parts or nested units.
4. When you call:

   ```java
   vehicleBody.showDetails(0);
   ```

   the following recursive flow happens:

   * `MachineUnit` prints its label: “Vehicle Body”.
   * It iterates through its children.
   * For each child:

     * If it’s a `MachinePart`, it prints directly.
     * If it’s another `MachineUnit`, it **recursively** calls its own `showDetails()`.

This recursive structure allows **infinite nesting** of units and parts.

---

### **Flow Summary**

> **Vehicle Body**
> ├── **Chassis Frame (part)**
> └── **Power Unit (unit)**
>     ├── **Compressor (part)**
>     └── **Cooling Fan (part)**

---

### **Key Advantage**

The client code (`Demo.main`) treats both `MachinePart` and `MachineUnit` the same way:

```java
vehicleBody.showDetails(0);
```

No special logic or type-checking is required — that’s the **essence of Composite**.

---

## 🎨 Decorator Pattern

### **Intent**

The **Decorator Pattern** dynamically adds new functionality to an object **without altering its structure**.
It’s a flexible alternative to subclassing for feature extension.

---

### **Classes in the Example**

* **`VehicleBase`** → Base abstract class defining `description()` and `price()`.
* **`SimpleVan`** → Concrete base vehicle (the “core” object).
* **`VehicleUpgrade`** → Abstract decorator class wrapping a `VehicleBase`.
* **`LeatherSeats`**, **`NavigationSystem`** → Concrete decorators adding new features.

---

### **Codeflow Explanation**

1. Start with a base vehicle:

   ```java
   VehicleBase deliveryVan = new SimpleVan();
   ```

   → `"Standard Van"`, base cost `$25000`.

2. Decorate it step-by-step:

   ```java
   deliveryVan = new LeatherSeats(deliveryVan);
   deliveryVan = new NavigationSystem(deliveryVan);
   ```

   Each decorator wraps the previous one:

   ```
   NavigationSystem(
       LeatherSeats(
           SimpleVan
       )
   )
   ```

3. When calling:

   ```java
   deliveryVan.description();
   ```

   Each decorator **adds its feature** and delegates to the wrapped object.
   Execution stack:

   * `NavigationSystem.description()` → calls inner `LeatherSeats.description()`
   * `LeatherSeats.description()` → calls inner `SimpleVan.description()`
   * Output: `"Standard Van + Leather Seats + GPS Navigation"`

4. Similarly for price:

   * `SimpleVan.price()` → 25000
   * `LeatherSeats` adds +1200
   * `NavigationSystem` adds +950
     → Final: `$27,150`

---

### **Flow Summary**

> **Base Object (SimpleVan)** → wrapped by **LeatherSeats** → wrapped by **NavigationSystem**
> → Result: *Dynamic combination of features and total cost.*
# Vehicle Management System Lab Report

## Design Patterns Implementation

### **1. State Pattern (Behavioral)**
**Purpose**: Manage vehicle state transitions

#### Implementation:
- **`VehicleState` Interface**: Defines `start()`, `stop()`, `accelerate()`, `getStateName()`
- **Concrete States**:
  - `StoppedState`: Vehicle is powered off
  - `IdleState`: Engine running but stationary
  - `MovingState`: Vehicle in motion
- **`VehicleContext`**: Maintains current state and delegates operations

#### State Transitions:
```
StoppedState → (start) → IdleState → (accelerate) → MovingState
MovingState → (stop) → IdleState → (stop) → StoppedState
```

#### Usage Example:
```java
IVehicle vehicle = VehicleFactory.createVehicle("sports");
VehicleContext state = new VehicleContext(vehicle.getType());
state.start();          // Engine starting... Vehicle is now IDLE
state.accelerate();     // Accelerating... Vehicle is now MOVING
state.stop();           // Braking... Vehicle is now IDLE
```

---

### **2. Strategy Pattern (Behavioral)**
**Purpose**: Implement interchangeable driving algorithms

#### Implementation:
- **`DrivingStrategy` Interface**: Defines `drive()` and `getStrategyName()`
- **Concrete Strategies**:
  - `EcoDriving`: Fuel-efficient, low-speed driving
  - `NormalDriving`: Balanced performance and comfort
  - `SportDriving`: High-performance, aggressive driving
  - `OffRoadDriving`: Terrain-optimized driving
- **`VehicleDriver` Context**: Uses and switches between strategies

#### Usage Example:
```java
VehicleDriver driver = new VehicleDriver("Car");
driver.setDrivingStrategy(new NormalDriving());
driver.performDrive();  // Normal driving mode

driver.setDrivingStrategy(new SportDriving());
driver.performDrive();  // Sport driving mode
```

---

### **3. Iterator Pattern (Behavioral)**
**Purpose**: Provide flexible traversal of vehicle collections

#### Implementation:
- **`VehicleIterator` Interface**: `hasNext()`, `getNext()`, `reset()`
- **Concrete Iterators**:
  - `AllVehiclesIterator`: Sequential iteration through all vehicles
  - `TypeFilterIterator`: Filters vehicles by type with lazy initialization
- **`VehicleCollection` Interface**: Factory for creating iterators
- **`VehicleFleetCollection`: Manages vehicle storage and iterator creation
- **`VehicleNotifier` Client**: Uses iterators for operations

#### Usage Example:
```java
VehicleFleetCollection fleet = new VehicleFleetCollection();
fleet.addVehicle(VehicleFactory.createVehicle("car"));
fleet.addVehicle(VehicleFactory.createVehicle("electric"));

// Filtered iteration
VehicleIterator carIterator = fleet.createTypeIterator("Car");
notifier.notifyMaintenance(carIterator, "Oil change required");

// Complete iteration
VehicleIterator allIterator = fleet.createAllIterator();
notifier.performInspection(allIterator);
```

---

### **4. Factory Pattern (Creational)**
**Purpose**: Centralize vehicle object creation

#### Implementation:
- **`VehicleFactory`**: Static factory method `createVehicle(String type)`
- **Supported Types**: "car", "sports", "electric", "gas", "bicycle"

#### Usage Example:
```java
IVehicle car = VehicleFactory.createVehicle("car");
IVehicle sportsCar = VehicleFactory.createVehicle("sports");
```

---

## Pattern Integration & System Workflow

### **Complete System Flow:**
1. **Creation**: Vehicles created via Factory Pattern
2. **Storage**: Vehicles managed in Fleet Collection
3. **Traversal**: Iterators provide flexible access patterns
4. **State Management**: Each vehicle maintains state machine
5. **Behavior**: Driving strategies define vehicle behavior

### **Key Benefits Demonstrated:**

#### **1. Maintainability**
- Each pattern encapsulates specific concerns
- Easy to extend (add new states, strategies, iterators)
- Minimal conditional logic

#### **2. Flexibility**
- Runtime strategy switching
- Multiple iteration algorithms
- Dynamic state transitions

#### **3. Scalability**
- New vehicle types easily added
- Additional states/strategies integrate seamlessly
- Collection operations remain consistent

#### **4. Code Reusability**
- Iterators reusable across different operations
- Strategies applicable to multiple vehicle types
- State logic independent of vehicle type

---

## Output Example
```
=== 6. ITERATOR PATTERN (Behavioral) ===

[Car - NORMAL MODE]
  🚗 Normal driving mode:
     - Speed: Moderate (60 km/h)
     - Fuel efficiency: Optimal
     - Comfort: High

[Car - SPORT MODE]
  🏎️ Sport driving mode:
     - Speed: High (120 km/h)
     - Fuel efficiency: Low
     - Acceleration: Maximum
     - Engine response: Aggressive

=== Vehicle Management System Ended ===
```

---

## Design Principles Applied

| Principle | Implementation |
|-----------|----------------|
| **Single Responsibility** | Each class has one clear purpose |
| **Open/Closed** | Easy to extend without modifying existing code |
| **Dependency Inversion** | Depend on abstractions, not concretions |
| **Interface Segregation** | Focused interfaces for specific clients |
| **Liskov Substitution** | All implementations substitutable for interfaces |

---

## Conclusion
This lab successfully demonstrates three behavioral patterns (State, Strategy, Iterator) working together with a creational pattern (Factory) to create a flexible, maintainable vehicle management system. Each pattern addresses specific concerns while integrating seamlessly to provide comprehensive vehicle lifecycle management.
