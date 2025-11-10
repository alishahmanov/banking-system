package observer;

/**
 * Concrete observer representing a mobile phone device.
 * Receives and prints bank notifications.
 */
public class MobilePhoneDevice implements Device {
   @Override
    public void update(String message) {
        System.out.println("Mobile phone notification:\n" + message);
    }

    @Override
    public String toString() {
        return "Mobile phone";
    }
}
