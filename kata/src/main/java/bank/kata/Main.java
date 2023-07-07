package bank.kata;

import bank.kata.controllers.AccountController;
import bank.kata.exceptions.IncorrectAmountException;
import bank.kata.exceptions.InsufficientFundsException;
import bank.kata.models.Account;
import bank.kata.views.AccountView;

public class Main {

    public static void main(String[] args) throws IncorrectAmountException, InsufficientFundsException {
    	
    	//Create new bank account
    	Account account = new Account();

        //Create a view : to write account transactions details on console
        AccountView view = new AccountView();
        
        //Create a controller : to make our operations on the account
        AccountController controller = new AccountController(account, view);

        controller.deposit(1000);
        controller.withdraw(500);
        controller.deposit(200);
        controller.updateView();
      
    }

}
