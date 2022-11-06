package atmProject;

import java.util.Scanner;

import static atmProject.atmFileOperations.writeLog;
import static java.lang.System.exit;

public class atmUI extends atmException {

    private static final String message = """
            ----------------------
            |   Welcome to ATM   |
            ----------------------""";

    public atmUI(){
        super(message);
    }

    public static void atmUIlogin() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        atmCustomerClass customer = atmFileOperations.readCustomerFile(username, password);
        int counter = 1;
        while (customer == null) {
            System.out.println("Wrong username or password. Please try again.");
            System.out.print("Please enter your username: ");
            username = scanner.nextLine();
            System.out.print("Please enter your password: ");
            password = scanner.nextLine();
            customer = atmFileOperations.readCustomerFile(username, password);
            counter++;
            if (counter == 3) {
                System.out.println("You entered wrong username or password for 3 times. System will exit.");
                exit(0);
            }
        }
        welcomeUser(customer);
    }

    public static void welcomeUser(atmCustomerClass customer) {
        String log = "Successful login attempt by " + customer.getName() + " with balance " + customer.getBalance();
        writeLog(log);
        System.out.println("Welcome " + customer.getName());
        showMenu(customer);
    }

    public static void showMenu(atmCustomerClass customer) {

        Scanner atmScanner = new Scanner(System.in);
        int balance = (int) customer.getBalance();
        System.out.println("""
                |--------------------------------|
                |   1. Check Balance             |
                |   2. Deposit Money             |
                |   3. Withdraw Money            |
                |   4. Transfer money            |
                |   5. Transfer money using EFT  |
                |   6. Exit                      |
                |--------------------------------|""");

        if (balance == 0) {
            System.out.println("Your balance is zero. Please deposit money first.");
            atmOperations.deposit(customer);
        } else {
            System.out.println("Please enter your choice: ");
        }

        try {
            int choice = atmScanner.nextInt();
            if (isChoiceValid(choice)) {
                throw new atmException("Please enter a number between 1 and 6.");
            }
            else {
                switch (choice) {
                    case 1 -> checkBalance(customer);
                    case 2 -> depositMoney(customer);
                    case 3 -> withdrawMoney(customer);
                    case 4 -> transferMoney(customer);
                    case 5 -> transferMoneyEFT(customer);
                    case 6 -> {
                        System.out.println("Thank you for using our ATM.");
                        exit(0);
                    }
                }
            }
        } catch (atmException e) {
            System.out.println(e.getMessage());
            showMenu(customer);
        } catch (Exception isDigit){
            System.out.println("Please enter a digit.");
            showMenu(customer);
        }
    }

    private static void transferMoney(atmCustomerClass customer) {
        atmOperations.transferMoney(customer);
    }

    private static void transferMoneyEFT(atmCustomerClass customer) {
        atmOperations.transferMoneyEFT(customer);
    }

    private static void checkBalance(atmCustomerClass customer) {
        atmOperations.balance(customer);
    }

    private static void withdrawMoney(atmCustomerClass customer) {
        atmOperations.withdraw(customer);
    }

    private static void depositMoney(atmCustomerClass customer) {
        atmOperations.deposit(customer);
    }
}
