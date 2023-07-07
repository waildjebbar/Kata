package bank.kata.views;

import java.util.List;

import bank.kata.models.Transaction;

public class AccountView {

    public void printStatement(List<Transaction> transactions) {
    	
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }

        System.out.print("Here are all the operations you made :\nDate\t\tAmount\tBalance\tOperation\n");
        for (Transaction transaction : transactions) {
            System.out.print(transaction+"\n");
        }
    }
}
