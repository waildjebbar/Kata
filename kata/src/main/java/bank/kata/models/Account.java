package bank.kata.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bank.kata.exceptions.IncorrectAmountException;
import bank.kata.exceptions.InsufficientFundsException;

public class Account {
    
	private double balance;
	private List<Transaction> transactions;

	public Account() {
        balance = 0;
        transactions = new ArrayList<>();
    }

    public double getBalance() {
		return balance;
	}
    
    public List<Transaction> getTransactions() {
		return transactions;
	}

	public void deposit(double amount) throws IncorrectAmountException {
		validateAmount(amount);
        balance += amount;
        System.out.println("The deposit operation of the amount: "+amount+" was successful");
        transactions.add(new Transaction(LocalDate.now(), amount, balance, "Deposit"));
    }

    public void withdraw(double amount) throws IncorrectAmountException, InsufficientFundsException {
    	validateAmount(amount);
        validateSufficientFunds(amount);
        balance -= amount;
        System.out.println("The withdrawal operation of the amount: "+amount+" was successful");
        transactions.add(new Transaction(LocalDate.now(), -amount, balance, "Withdrawal"));
    }
    
    private void validateAmount(double amount) throws IncorrectAmountException {
        if (amount <= 0) {
            throw new IncorrectAmountException("Invalid amount. Amount must be greater than zero.");
        }
    }

    private void validateSufficientFunds(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Cannot withdraw more than the available balance.");
        }
    }
}



