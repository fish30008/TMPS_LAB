package just_test;


interface IVehicle{
    void moove();
}

abstract class Vehicle implements IVehicle{

    private int speed;

    Vehicle(int speed){
        this.speed = speed;
    }

    @Override
    public void moove(){
        System.out.println("Moove with speed of - >" + this.speed + " < - ");
    }
}

class VehicleChild extends Vehicle{

    VehicleChild(int speed){
        super(speed);
    }

    public void moove(){
        System.out.println("Slow");
    }

}



public class Main{

    public static void main(String[] args) {

        IVehicle vehicle = new VehicleChild(100);
        vehicle.moove();
        System.out.println(" - ");
    }


}