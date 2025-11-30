package Lab3.utilities;

public class VehicleLogger {
    private static VehicleLogger instance;

    private VehicleLogger() {}

    public static synchronized VehicleLogger getInstance() {
        if (instance == null) {
            instance = new VehicleLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}