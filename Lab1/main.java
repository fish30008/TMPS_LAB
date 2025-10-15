package Lab1;

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
