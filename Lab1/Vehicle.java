package Lab1;

// Base class - Classes should be open for extension but closed for modification.
abstract class Vehicle {
    public abstract void move();
}


class Boat extends Vehicle {
    public void move() {
        System.out.println("Lab1.Boat is sailing on the water.");
    }
}

// Polymorphic usage
class VehicleMover {
    public void makeItMove(
            Vehicle vehicle) {
        vehicle.move();
    }
}

class Plane extends Vehicle {
    public void move() {
        System.out.println("Lab1.Plane is flying in the sky.");
    }
}