package Lab3.domain.builder;

public class Vehicle {
    private String type;
    private String wheels;
    private String engine;
    private String color;

    public void setType(String type) { this.type = type; }
    public void setWheels(String wheels) { this.wheels = wheels; }
    public void setEngine(String engine) { this.engine = engine; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", wheels='" + wheels + '\'' +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}