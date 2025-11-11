package observer;

/**
 * Observer interface.
 * Represents any device capable of receiving bank notifications.
 */
public interface Device {
    void update(String message);
}
