package Bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {

    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

//    Deposit Money
    public void deposit(double amount){

        if( amount > 0) {
            balance = balance + amount;
            transactionHistory.add(new Transaction(new Date(), "Deposit", amount));
            System.out.println("You just deposited " + amount + " to your bank account your current balance is " + balance);
        }
        else {
            System.out.println("You can deposit under 0 to you account unless do you want to go into debt");
        }
    }


//    Withdraw Money
    public void withdrawal(double amount) {

        if(amount > 0 && amount <= balance ) {
            balance = balance - amount;
            transactionHistory.add(new Transaction(new Date(), "Withdrawal", amount));
            System.out.println("You just withdrew " + amount + " Your current balance is " + balance);
        }
        else{
            System.out.println("You either entered a wrong withdraw amount or don't have enough balance");
        }
    }


// Show Transaction History

    public void displayTransactionHistory(){
        if (transactionHistory.isEmpty()) {
            System.out.println("You have no transaction history");
        }
        else {
            for (Transaction transaction : transactionHistory){
                System.out.println(transaction);
            }
        }
    }






    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}