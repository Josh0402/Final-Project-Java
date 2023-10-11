package com.example.demo;
// From UML I changed in the savings class: totalPay to Transfer as this would allow me to combine the two and let the user change how much is saved or spent, then shows total output of both.
import java.util.Scanner;
// declaring multiple functions to be used in the code
public class JobFinal {
    // Declared variables as private per comments on UML
    private final String userName;
    private final String workTitle;
    private final int yearsWorked;
    private final int hourlyWage;
    private double savingsBalance;
    private double spendingBalance;

    // Sets up first instance of the code and establishes multiple variables
    public JobFinal(String userName, String workTitle, int yearsWorked, int hourlyWage, double savingsBalance, double spendingBalance) {
        this.userName = userName;
        this.workTitle = workTitle;
        this.yearsWorked = yearsWorked;
        this.hourlyWage = hourlyWage;
        this.savingsBalance = savingsBalance;
        this.spendingBalance = spendingBalance;
    }

    // To have the user verify all his information matches
    public static JobFinal authenticate(JobFinal[] acc, String userName, String workTitle, int yearsWorked) {
        for (JobFinal a : acc) {
            if (a.userName.equals(userName) && a.workTitle.equals(workTitle) && a.yearsWorked == yearsWorked)
                return a;
        }
        return null;
    }

    // Here shows the wages between Hourly, Monthly, and Annually
    public static class wage {}
    public void wage() {
        System.out.print("Hourly wage is: ");
        System.out.println(hourlyWage);

        System.out.print("Annual wage is: ");
        System.out.println(hourlyWage * 25 * 50);

        System.out.print("Monthly wage is: ");
        System.out.println((hourlyWage * 25 * 50) / 12.0);
    }

    // Has the user declare how much money he wants to take out and spend
    public void withdraw(double amount) {
        if (amount <= spendingBalance) {
            spendingBalance -= amount;
            System.out.println("Withdraw successful. Current spending balance: " + spendingBalance);
        } else {
            System.out.println("Withdraw amount exceeds the total spending balance in the account");
        }
    }

    // Has the user declare how much money he wants to put in to save
    public void deposit(double amount) {
        savingsBalance += amount;
        System.out.println("Deposit successful. Current savings balance: " + savingsBalance);
    }

    // Function to transfer money in between each saving and their spending's account
    public void transfer(double amount, JobFinal obj) {
        if (amount <= savingsBalance) {
            savingsBalance -= amount;
            obj.savingsBalance += amount;
            System.out.println("Transfer Successful");
        } else {
            System.out.println("Transfer amount exceeds your account balance");
        }
    }

    // Has user establish an overall user Balance that holds savings and spending amounts
    public static class Balance {}
    public double getSavingsBalance() {
        return savingsBalance;
    }

    public double getSpendingBalance() {
        return spendingBalance;
    }

    // Test class to have user correlate all the information they provided in their job
    // Used acc[0] for my JavaFX test but can be used with any of the other acc
    public static void main(String[] args) {
        JobFinal[] acc = new JobFinal[5];
        acc[0] = new JobFinal("Joshua", "Doctor", 1, 25, 75000.00, 100.00);
        acc[1] = new JobFinal("Scot", "Engineer", 2, 25, 80000.00, 100.00);
        acc[2] = new JobFinal("Staton", "Actor", 3, 25, 85000.00, 100.00);
        acc[3] = new JobFinal("John", "Teacher", 4, 25, 90000.00, 100.00);
        acc[4] = new JobFinal("Josh", "Musician", 5, 25, 95000.00, 100.00);

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter User name: ");
            String userName = sc.nextLine();
            System.out.println("Enter Users Job Title: ");
            String workTitle = sc.nextLine();
            System.out.println("Enter Users years worked: ");
            int yearsWorked = sc.nextInt();
            JobFinal a = JobFinal.authenticate(acc, userName, workTitle, yearsWorked);

            if (a != null) {
                System.out.println("User found.");
                while (true) {
                    System.out.println("""
                            1. Calculate Wage
                            2. Withdraw Money
                            3. Deposit Money
                            4. Transfer Money
                            5. Savings Balance
                            6. Spending Balance
                            7. Logout""");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            a.wage();
                            break;
                        case 2:
                            System.out.println("Enter amount you want to withdraw: ");
                            double withdrawAmount = sc.nextDouble();
                            a.withdraw(withdrawAmount);
                            break;
                        case 3:
                            System.out.println("Enter amount you want to deposit: ");
                            double depositAmount = sc.nextDouble();
                            a.deposit(depositAmount);
                            break;
                        case 4:
                            System.out.println("Enter account with years worked to which you want to transfer: ");
                            yearsWorked = sc.nextInt();
                            System.out.println("Enter the amount: ");
                            double transferAmount = sc.nextDouble();
                            a.transfer(transferAmount, acc[yearsWorked - 1]);
                            break;
                        case 5:
                            System.out.println("Current savings account balance: " + a.getSavingsBalance());
                            break;
                        case 6:
                            System.out.println("Current spending account balance: " + a.getSpendingBalance());
                            break;
                        case 7:
                            System.exit(0);
                        default:
                            System.out.println("Invalid input");
                    }
                }
            } else {
                System.out.println("User not found.");
            }
        }
    }
}
