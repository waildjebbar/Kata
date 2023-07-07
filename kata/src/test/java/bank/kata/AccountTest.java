package bank.kata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bank.kata.controllers.AccountController;
import bank.kata.exceptions.IncorrectAmountException;
import bank.kata.exceptions.InsufficientFundsException;
import bank.kata.models.Account;
import bank.kata.views.AccountView;


class AccountTest {
	
	private AccountController controller;

    @BeforeEach
    void setUp() {
    	Account account = new Account();
        AccountView view = new AccountView();
        controller = new AccountController(account, view);
    }
    
	@Nested
    class DepositTests {

        @Test
        void deposit_ValidAmount_ShouldIncreaseBalance() throws IncorrectAmountException {
            controller.deposit(1000);
            Assertions.assertEquals(1000, controller.getBalance(), 0.001);
        }

        @Test
        void deposit_NegativeAmount_ShouldThrowIncorrectAmountException() {
            Assertions.assertThrows(IncorrectAmountException.class, () -> controller.deposit(-500));
            Assertions.assertEquals(0, controller.getBalance(), 0.001);
        }
    }

    
	@Nested
    class WithdrawalTests {

        @Test
        void withdraw_ValidAmount_ShouldDecreaseBalance() throws IncorrectAmountException, InsufficientFundsException {
        	controller.deposit(1000);
        	controller.withdraw(500);
            Assertions.assertEquals(500, controller.getBalance(), 0.001);
        }

        @Test
        void withdraw_NegativeAmount_ShouldThrowIncorrectAmountException() throws IncorrectAmountException {
        	controller.deposit(1000);
            Assertions.assertThrows(IncorrectAmountException.class, () -> controller.withdraw(-200));
            Assertions.assertEquals(1000, controller.getBalance(), 0.001);
        }

        @Test
        void withdraw_AmountExceedsBalance_ShouldThrowInsufficientFundsException() throws IncorrectAmountException {
        	controller.deposit(500);
            Assertions.assertThrows(InsufficientFundsException.class, () -> controller.withdraw(1000));
            Assertions.assertEquals(500, controller.getBalance(), 0.001);
        }
    }
	
    @Nested
    class StatementTests {

        @Test
        void printStatement_NoTransactions_ShouldPrintNoTransactionsMessage() {
            String expectedOutput = "No transactions to display.";
            Assertions.assertEquals(expectedOutput, TestUtils.getPrintedOutput(() -> controller.updateView()));
        }

        @Test
        void printStatement_WithTransactions_ShouldPrintStatement() throws IncorrectAmountException, InsufficientFundsException {
        	controller.deposit(1000);
        	controller.withdraw(500);
            String expectedOutput = "Here are all the operations you made :\nDate\t\tAmount\tBalance\tOperation\n" +
                    LocalDate.now() + "\t1000.0\t1000.0\tDeposit\n" +
                    LocalDate.now() + "\t-500.0\t500.0\tWithdrawal";
            Assertions.assertEquals(expectedOutput, TestUtils.getPrintedOutput(() -> controller.updateView()));
        }
    }
}

class TestUtils {
    static String getPrintedOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        action.run();
        System.setOut(originalOut);
        return outputStream.toString().trim();
    }

}