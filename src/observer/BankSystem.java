package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete subject.
 * Manages all connected devices and broadcasts account updates.
 */
public class BankSystem implements NotificationSystem {
    private static BankSystem instance;
    List<Device> devices = new ArrayList<>();

    public static BankSystem getInstance() {
        if (instance == null) {
            instance = new BankSystem();
        }
        return instance;
    }

    @Override
    public void addDevice(Device device) {
        devices.add(device);
    }

    @Override
    public void deleteDevice(Device device) {
        devices.remove(device);
    }

    @Override
    public void seeDevices() {
        int id = 1;
        for (Device o : devices) {
            System.out.println("Device: " + id++ + " " + o);
        }
    }

    @Override
    public void notifyObservers(String message) {
        for (Device o : devices) {
            o.update(message);
        }
    }

    /**
     * Notifies all devices about a transaction without bonus info.
     */
    public void balanceChange(String clientName, String accountName, String operation, double amount, double balance) {
        String message = String.format(
                "Client: %s | Account: %s | Operation: %s | Amount: %.2f ₸ | Balance left: %.2f ₸",
                clientName, accountName, operation, amount, balance
        );
        notifyObservers(message);
    }

    /**
     * Notifies all devices about a transaction with bonus applied(only for payments).
     */
    public void balanceChange(String clientName, String accountName, String operation, double amount, double balance, double bonusAmount) {
        String message = String.format(
                "Client: %s | Account: %s | Operation: %s | Amount: %.2f ₸ | Bonus: +%.2f ₸ | Balance left: %.2f ₸",
                clientName, accountName, operation, amount, bonusAmount, balance
        );
        notifyObservers(message);
    }
}
