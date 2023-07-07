package bank.kata.controllers;


import bank.kata.exceptions.IncorrectAmountException;
import bank.kata.exceptions.InsufficientFundsException;
import bank.kata.models.Account;
import bank.kata.views.AccountView;

public class AccountController {

	private Account account;
    private AccountView view;

    public AccountController(Account account, AccountView view){
       this.account = account;
       this.view = view;
    }
    
    public double getBalance() {
		return account.getBalance();
	}
	public void deposit(double amount) throws IncorrectAmountException {
		account.deposit(amount);
    }

    public void withdraw(double amount) throws IncorrectAmountException, InsufficientFundsException {
    	account.withdraw(amount);
    }
    
    public void updateView(){                
        view.printStatement(account.getTransactions());
     } 
    
}
