// Handles only how a Car behaves
package Lab1;

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
