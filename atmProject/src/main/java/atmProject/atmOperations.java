package atmProject;

import java.util.Scanner;

import static atmProject.atmFileOperations.writeLog;
import static atmProject.atmUI.showMenu;

public class atmOperations {
    public static void deposit(atmCustomerClass customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount: ");
        int amount = scanner.nextInt();
        double balance = customer.getBalance();
        balance += amount;
        customer.setBalance(balance);
        System.out.println("Your new balance is " + balance);
        writeLog("Deposit operation by " + customer.getName() + " with amount " + amount + " and new balance " + balance);
        showMenu(customer);
    }

    public static void withdraw(atmCustomerClass customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount: ");
        int amount = scanner.nextInt();
        double balance = customer.getBalance();
        if (amount > balance) {
            System.out.println("You can not withdraw more money than your balance.");
            showMenu(customer);
        } else if (customer.getBalance() == 0) {
            System.out.println("You need to deposit money first.");
            deposit(customer);
        }else {
            balance -= amount;
            customer.setBalance(balance);
            System.out.println("Your new balance is " + balance);
            writeLog("Withdraw operation by " + customer.getName() + " with amount " + amount + " and new balance " + balance);
            showMenu(customer);
        }
    }
    public static void balance(atmCustomerClass customer) {
        System.out.println("Your balance is " + customer.getBalance());
        showMenu(customer);
    }

    public static void sendFakeMail(){
        Scanner emailScanner = new Scanner(System.in);
        System.out.println("Please enter the email address: ");
        String email = emailScanner.nextLine();
        writeLog("E-mail sent to " + email);
        System.out.println("Mail sent to your email address.");
    }

    public static void transferMoney(atmCustomerClass customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the IBAN number: ");
        String iban = scanner.nextLine();
        System.out.println("Please enter the amount: ");
        int amount = scanner.nextInt();
        try {
            if (amount > customer.getBalance()) {
                throw new atmException("You can not send money more than your balance.");
            } else {
                System.out.println("Money transfer is successful.");
                sendFakeMail();
                customer.setBalance(customer.getBalance() - amount);
                writeLog("Transfer operation by " + customer.getName() + " with amount " + amount + " and new balance " + customer.getBalance());
                showMenu(customer);
            }
        } catch (atmException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void transferMoneyEFT(atmCustomerClass customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the IBAN number: ");
        String iban = scanner.nextLine();
        System.out.println("Please enter the amount: ");
        int amount = scanner.nextInt();
        try {
            if (amount > customer.getBalance()) {
                throw new atmException("You can not send money more than your balance.");
            } else {
                System.out.println("EFT is successful.");
                sendFakeMail();
                writeLog("EFT operation by " + customer.getName() + " with amount " + amount + " and new balance " + customer.getBalance());
                customer.setBalance(customer.getBalance() - amount);
                showMenu(customer);
            }
        } catch (atmException e) {
            System.out.println(e.getMessage());
        }
    }
}
