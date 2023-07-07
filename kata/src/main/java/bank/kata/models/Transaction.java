package bank.kata.models;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private double amount;
    private double balance;
    private String operation;

    public Transaction(LocalDate date, double amount, double balance, String operation) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return date + "\t" + amount + "\t" + balance + "\t" + operation;
    }
}
