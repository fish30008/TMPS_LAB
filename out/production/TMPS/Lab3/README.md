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

