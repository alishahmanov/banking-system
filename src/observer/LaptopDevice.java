package observer;

/**
 * Concrete observer representing a laptop device.
 * Receives and prints bank notifications.
 */
public class LaptopDevice implements Device {
    @Override
    public void update(String message) {
        System.out.println("Laptop notification:\n" + message);
    }

    @Override
    public String toString() {
        return "Laptop";
    }
}
