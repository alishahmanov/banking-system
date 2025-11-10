import model.Account;
import model.AccountType;
import model.Client;
import observer.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 🏦 Welcome to our Bank System ===\n");

        Client client = new Client("Sabulla", "Diana", "diana@bank.kz", "+77009890450");
        client.showClientInfo();

        Device phone = new MobilePhoneDevice();
        Device laptop = new LaptopDevice();
        client.addDevice(phone);
        client.addDevice(laptop);

        client.seeDevices();

        Account savings = new Account(client, AccountType.SAVINGS, "Dream account");
        Account deposit = new Account(client, AccountType.DEPOSIT, "Big goal deposit");

        client.createAccount(savings);
        client.createAccount(deposit);
        client.showAccounts();

        System.out.println("\n=== 💸 Performing operations ===");
        savings.deposit(120000);
        System.out.println();
        deposit.deposit(300000);
        System.out.println();
        savings.pay(20000);
        System.out.println();
        deposit.withdraw(100000);

        System.out.println("\n=== 📊 Final account info ===");
        client.showAccounts();
    }
}