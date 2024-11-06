package test;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateAccountTest {

    @Test
    @Order(1)
    void test1_BankAccountConstructorExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            assertNotNull(constructor, "BankAccount should have a constructor with a String parameter (accountNumber).");
        } catch (NoSuchMethodException e) {
            fail("BankAccount should have a constructor with a String parameter (accountNumber).");
        } catch (ClassNotFoundException e) {
            fail("BankAccount class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_DepositMethodUpdatesBalance() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            Object bankAccount = constructor.newInstance("123456");

            Method depositMethod = cls.getMethod("deposit", double.class);
            Method getBalanceMethod = cls.getMethod("getBalance");

            depositMethod.invoke(bankAccount, 100.0);
            double balance = (double) getBalanceMethod.invoke(bankAccount);

            assertEquals(100.0, balance, 0.001, "After depositing 100, balance should be 100.");

        } catch (Exception e) {
            fail("Error testing deposit method: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void test3_WithdrawalMethodUpdatesBalance() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            Object bankAccount = constructor.newInstance("123456");

            Method depositMethod = cls.getMethod("deposit", double.class);
            Method withdrawalMethod = cls.getMethod("withdrawal", double.class);
            Method getBalanceMethod = cls.getMethod("getBalance");

            depositMethod.invoke(bankAccount, 200.0);
            withdrawalMethod.invoke(bankAccount, 50.0);
            double balance = (double) getBalanceMethod.invoke(bankAccount);

            assertEquals(150.0, balance, 0.001, "After depositing 200 and withdrawing 50, balance should be 150.");

        } catch (Exception e) {
            fail("Error testing withdrawal method: " + e.getMessage());
        }
    }
}
