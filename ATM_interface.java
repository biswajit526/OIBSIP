
import java.util.*;

class Account {
    static int accountNo;
    int pin;
    String name;
    String userID;
    static float balance = 0;
    static int transection = 0;
    static String transectionHistory = "";

    public void register() {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account holder name : ");
        this.name = sc.nextLine();
        System.out.print("Enter user ID : ");
        userID = sc.next();
        int length = 0;
        try {
            do {
                System.out.print("Enter your 4 digit secret pin : ");
                pin = sc.nextInt();
                length = String.valueOf(pin).length();
                if (length != 4) {
                    System.out.println("Please enter 4 digit pin.");
                }
            } while (length != 4);
        } catch (Exception e) {
            System.out.println("Pin should be positive integer value");
        }
        accountNo = rand.nextInt(10000,99999);

        System.out.print("deposit initial amount : ");
        balance = sc.nextFloat();
        System.out.println("Congratulations, your account has been created successful");
        System.out.println("your Account Number is: " + accountNo);

    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("Enter your user ID: ");
            String username = sc.next();
            if (username.equals(userID)) {
                while (!isLogin) {
                    System.out.print("Enter your 4 digit pin: ");
                    int password = sc.nextInt();
                    if (password == pin) {
                        System.out.println("Log in successful");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect password");
                    }
                }
            } else {
                System.out.println("!!!Incorrect user Id");
            }
        }

        return isLogin;
    }

}

class ATM {
    public void Withdraw() {
        Account ac = new Account();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount in multiple of 100 : ");
        float amount = sc.nextFloat();
        if (amount % 100 == 0) {
            try {
                if (Account.balance >= amount) {
                    Account.balance = Account.balance - amount;
                    Account.transection++;
                    String str = amount + " Rs Withdrawn\n";
                    Account.transectionHistory = Account.transectionHistory.concat(str);
                    System.out.println("Withdraw successful");
                    System.out.println("Available Balance: " + Account.balance);
                } else {
                    System.out.println("Insufficient Balance");
                }
            } catch (Exception e) {
                System.out.println("Error occured during withdraw money");
            }

        } else {
            System.out.println("Your amount is not in multiple of 100.");
            System.out.println("Try Again");
        }
        
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        Account ac = new Account();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        try {
            Account.balance += amount;
            Account.transection++;
            String str = amount + " Rs Deposit\n";
            Account.transectionHistory = Account.transectionHistory.concat(str);
            System.out.println("Successfully deposited");
            
        } catch (Exception e) {
            System.out.println("Error occured while deposit money");
        }

    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        Account ac = new Account();
        System.out.print("Enter receipent's account no. : ");
        int r_acc = sc.nextInt();
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        try {
        if (Account.balance >= amount) {
        if (amount <= 50000) {
        Account.transection++;
        Account.balance -= amount;
        System.out.println("Amount Successfully transfered to " + r_acc);
        String str = amount + " Rs transfered to " + r_acc + "\n";
        Account.transectionHistory = Account.transectionHistory.concat(str);

        } else {
        System.out.println("Sorry, you can't transfer more than 50000");
        }
        } else {
        System.out.println("Insufficient balance");
        }
        } catch (Exception e) {
        System.out.println("Error occured during money transfer");
        }
    }

    public void checkBalance() {
        Account ac = new Account();
        System.out.println("Available Balance is: " + Account.balance + "Rs");

    }

    public void history() {
        Account ac = new Account();
        if (Account.transection == 0) {
        System.out.println("Have not done any transection.");
        } else {
        System.out.println(Account.transectionHistory);
        }

    }
}

public class ATM_interface {

    public static int integerInput(int limit) {
        int input = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in); 
        while (!flag) {
            try {
                input = sc.nextInt();
                if (input >= 1 && input <= limit) { 
                    flag = true;
                } else {
                    System.out.println("Choose a number between 1 to " + limit);
                }
            } catch (InputMismatchException e) { 
                System.out.println("Enter only an integer value.");
                sc.nextLine(); 
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM at = new ATM();
        System.out.println("****************Welcome to SBI ATM*****************");
        System.out.println("1.Press 1 for Register. \n2.Press 2 for exit. ");
        System.out.print("Enter your choice: ");
        int choice = integerInput(2);
        if (choice == 1) {
            Account ac = new Account();
            ac.register();
            System.out.println("\n*******Please Log in again to use ATM*******");
            while (true) {
                System.out.println("1.Log in \n2.Exit");
                System.out.print("Enter your choice: ");
                int ch = integerInput(2);
                if (ch == 1) {
                    if (ac.login()) {
                        boolean isFinish = false;
                        while (!isFinish) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.CheckBalance \n5.Transection History \n6.Exit");
                            System.out.print("Enter your choice: ");
                            int choice2 = integerInput(6);
                            switch (choice2) {
                                case 1:
                                    at.Withdraw();
                                    break;
                                
                                case 2:
                                    at.deposit();
                                    break;
                                
                                case 3:
                                    at.transfer();
                                    break;

                                case 4:
                                    at.checkBalance();
                                    break;

                                case 5:
                                    at.history();
                                    break;
                                
                                case 6:
                                    isFinish = true;
                                    break;
                            
                                default:
                                    System.out.println("Wrong input.");
                                    break;
                            }
                        }
                    }
                    else{
                        System.exit(0);
                    }
                    
                }
                else{
                    System.exit(0);
                }
                
            }

        }

        else {
            System.exit(0);
        }
    }

}
