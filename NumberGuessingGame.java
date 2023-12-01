import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMInterface {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static int currentUserId = -1;
    public static void main(String[] args) {
        initializeAccounts();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.out.println("Exiting ATM System. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (authenticateUser(accountNumber, pin)) {
            currentUserId = accountNumber;
            showMainMenu(scanner);
        } else {
            System.out.println("Invalid account number or PIN. Please try again.");
        }
    }

    private static boolean authenticateUser(int accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        return account != null && account.getPin() == pin;
    }

    private static void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawCash(scanner);
                    break;
                case 3:
                    depositCash(scanner);
                    break;
                case 4:
                    transferFunds(scanner);
                    break;
                case 5:
                    System.out.println("Logging out. Thank you!");
                    currentUserId = -1;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        Account account = accounts.get(currentUserId);
        System.out.println("Your balance is: " + account.getBalance());
    }

    private static void withdrawCash(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        Account account = accounts.get(currentUserId);
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Amount withdrawn successfully. Remaining balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    private static void depositCash(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        Account account = accounts.get(currentUserId);
        account.deposit(amount);
        System.out.println("Amount deposited successfully. Updated balance: " + account.getBalance());
    }

    private static void transferFunds(Scanner scanner) {
        System.out.print("Enter the recipient's account number: ");
        int recipientAccountNumber = scanner.nextInt();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        Account sender = accounts.get(currentUserId);
        Account recipient = accounts.get(recipientAccountNumber);

        if (recipient != null && sender.getBalance() >= amount) {
            sender.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Funds transferred successfully.");
        } else {
            System.out.println("Transfer failed. Please check recipient's account number or insufficient funds.");
        }
    }

    private static void initializeAccounts() {
        accounts.put(123456, new Account(123456, 7890, 1000.0));
        accounts.put(789012, new Account(654321, 7890, 2000.0));
    }
}

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
