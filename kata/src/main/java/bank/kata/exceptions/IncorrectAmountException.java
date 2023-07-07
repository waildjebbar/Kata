package bank.kata.exceptions;

public class IncorrectAmountException extends Exception{ 
    public IncorrectAmountException(String errorMessage) {
        super(errorMessage);
    }
}
