package model;

import observer.BankSystem;
import observer.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank client.
 * Manages personal info, accounts, and connected devices.
 */
public class Client {
    private static int nextId = 1;
    private int clientId;
    private String lastname;
    private String firstname;
    private String name;
    private String email;
    private String phone;
    private List<Account> accounts;
    private BankSystem bank = BankSystem.getInstance();

    public Client(String lastname, String firstname, String email, String phone) {
        clientId = nextId++;
        this.lastname = lastname;
        this.firstname = firstname;
        this.name = lastname + " " + firstname;
        this.email = email;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    public void addDevice(Device device) {
        bank.addDevice(device);
    }

    public void deleteDevice(Device device) {
        bank.deleteDevice(device);
    }

    public void seeDevices() {
        System.out.println(lastname + " " + firstname + " logged in:\n");
        bank.seeDevices();
    }

    public void createAccount(Account account) {
        accounts.add(account);
    }

    public void deleteAccount(Account account) {
        if (accounts.isEmpty()) {
            System.out.println("No account to delete.");
        } else {
            accounts.remove(account);
        }
    }

    public String getName() {
        return name;
    }

    public void showClientInfo() {
        System.out.println("──────────────── Client Info ────────────────");
        System.out.printf("ID: %d%n", clientId);
        System.out.printf("Name: %s%n", name);
        System.out.printf("Email: %s%n", email);
        System.out.printf("Phone: %s%n", phone);
        System.out.println("────────────────────────────────────────────");
    }

    public void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\n[ACCOUNTS] No accounts yet for " + name + ".");
        } else {
            System.out.println("\n[ACCOUNTS] Accounts for " + name + ":");
            for (Account a : accounts) {
                a.showAccountInfo();
            }
        }
    }
}