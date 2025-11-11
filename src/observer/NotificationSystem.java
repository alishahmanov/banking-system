package observer;

/**
 * Subject interface.
 * Defines methods for managing observers (devices).
 */
public interface NotificationSystem {
    void addDevice(Device device);

    void deleteDevice(Device device);

    void seeDevices();

    void notifyObservers(String message);
}
