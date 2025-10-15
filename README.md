
# SOLID Principles in Java 

## 3 letters -> S and O and L -> **SRP**, **OCP**, and **LSP**
---


### 1️⃣ **S – Single Responsibility Principle (SRP)**
> A class should have only one reason to change.

#### ✅ Implementation
- `Car` handles **vehicle behavior** (driving).
- `CarRepository` handles **data persistence** (saving to database).

#### 💡 Code Example
```java
class Car {
    public void drive() {
        System.out.println("Driving car...");
    }
}

class CarRepository {
    public void save(Car car) {
        System.out.println("Saving car to database...");
    }
}
````

Each class focuses on **one responsibility**, making maintenance simpler and reducing coupling.

---

### **O – Open/Closed Principle (OCP)**

> Classes should be **open for extension** but **closed for modification**.

#### Implementation

* Base class `Vehicle` defines a general `move()` method.
* New types (`Car`, `Boat`, `ElectricCar`) extend `Vehicle` **without modifying existing code**.
* `VehicleMover` uses polymorphism to work with any `Vehicle`.

#### Code Example

```java
abstract class Vehicle {
    public abstract void move();
}


class Boat extends Vehicle {
    @Override
    public void move() {
        System.out.println("Boat is sailing on the water.");
    }
}

class VehicleMover {
    public void makeItMove(Vehicle vehicle) {
        vehicle.move(); // works for any vehicle type
    }
}
```

 You can add new vehicles (like `Plane`) by simply extending `Vehicle`, with **no changes to existing classes**.

---

### **L – Liskov Substitution Principle (LSP)**

> Subtypes must be substitutable for their base types without breaking functionality.

#### Implementation

* `ElectricCar` extends `Car` and behaves consistently — it can **replace** `Car` anywhere in the code.
* No unexpected exceptions or altered logic.

#### Code Example

```java

class ElectricCar extends Car {
    @Override
    public void move() {
        System.out.println("Electric car is driving silently...");
    }
}
```

✅ Client code doesn’t need to know if it’s a `Car` or an `ElectricCar`; both behave correctly.

---

## 🧠 Main Program

```java

public class main {
    public static void main(String[] args) {
        VehicleMover mover = new VehicleMover();

        Car car = new Car();
        Vehicle boat = new Boat();
        Vehicle plane = new Plane();

        mover.makeItMove(boat);
        mover.makeItMove(plane);

        car.drive();
        Car car2 = new ElectricCar();
        car2.drive();
        CarRepository repo = new CarRepository();
        repo.save((Car) car);
    }
}

```

### Output

```
Lab1.Boat is sailing on the water.
Lab1.Plane is flying in the sky.
Driving car...
Driving electric car silently...
Saving car to database...
```

---

## Conclusion

This example shows how SOLID principles lead to:

* Cleaner and more maintainable code
* Easier extension with new features
* Reduced risk of breaking existing functionality

```
