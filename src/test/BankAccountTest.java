package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankAccountTest {

    @Test
    @Order(1)
    void test1_BankAccountClassExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            assertNotNull(cls, "BankAccount class should exist.");
        } catch (ClassNotFoundException e) {
            fail("BankAccount class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_BankAccountConstructorExists() {
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
    @Order(3)
    void test3_BankAccountAttributesExist() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");

            Field accountNumberField = cls.getDeclaredField("accountNumber");
            Field balanceField = cls.getDeclaredField("balance");
            Field transactionHistoryField = cls.getDeclaredField("transactionHistory");

            assertNotNull(accountNumberField, "BankAccount should have an accountNumber attribute.");
            assertNotNull(balanceField, "BankAccount should have a balance attribute.");
            assertNotNull(transactionHistoryField, "BankAccount should have a transactionHistory attribute.");
        } catch (Exception e) {
            fail("BankAccount should have accountNumber, balance, and transactionHistory attributes.");
        }
    }

    @Test
    @Order(4)
    void test4_BankAccountAttributeTypes() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");

            Field accountNumberField = cls.getDeclaredField("accountNumber");
            Field balanceField = cls.getDeclaredField("balance");
            Field transactionHistoryField = cls.getDeclaredField("transactionHistory");

            assertEquals(String.class, accountNumberField.getType(), "accountNumber should be of type String.");
            assertEquals(double.class, balanceField.getType(), "balance should be of type double.");

            // Assuming transactionHistory is List<Transaction>
            ParameterizedType transactionHistoryType = (ParameterizedType) transactionHistoryField.getGenericType();
            assertEquals(List.class, transactionHistoryField.getType(), "transactionHistory should be of type List.");
            assertEquals(Class.forName("Bank.Transaction"), transactionHistoryType.getActualTypeArguments()[0], "transactionHistory should be a List of Transaction objects.");

        } catch (Exception e) {
            fail("Error checking attribute types.");
        }
    }

    @Test
    @Order(5)
    void test5_BankAccountEncapsulation() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");

            Field accountNumberField = cls.getDeclaredField("accountNumber");
            Field balanceField = cls.getDeclaredField("balance");
            Field transactionHistoryField = cls.getDeclaredField("transactionHistory");

            assertTrue(Modifier.isPrivate(accountNumberField.getModifiers()), "accountNumber should be private.");
            assertTrue(Modifier.isPrivate(balanceField.getModifiers()), "balance should be private.");
            assertTrue(Modifier.isPrivate(transactionHistoryField.getModifiers()), "transactionHistory should be private.");

        } catch (Exception e) {
            fail("Error checking encapsulation.");
        }
    }

    @Test
    @Order(6)
    void test6_BankAccountGettersSettersExist() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");

            Method getAccountNumber = cls.getMethod("getAccountNumber");
            Method setAccountNumber = cls.getMethod("setAccountNumber", String.class);
            Method getBalance = cls.getMethod("getBalance");
            Method setBalance = cls.getMethod("setBalance", double.class);
            Method getTransactionHistory = cls.getMethod("getTransactionHistory");
            Method setTransactionHistory = cls.getMethod("setTransactionHistory", List.class);

            assertNotNull(getAccountNumber, "BankAccount should have getAccountNumber method.");
            assertNotNull(setAccountNumber, "BankAccount should have setAccountNumber method.");
            assertNotNull(getBalance, "BankAccount should have getBalance method.");
            assertNotNull(setBalance, "BankAccount should have setBalance method.");
            assertNotNull(getTransactionHistory, "BankAccount should have getTransactionHistory method.");
            assertNotNull(setTransactionHistory, "BankAccount should have setTransactionHistory method.");

        } catch (NoSuchMethodException e) {
            fail("BankAccount should have getters and setters for all attributes.");
        } catch (ClassNotFoundException e) {
            fail("BankAccount class should exist.");
        }
    }

    @Test
    @Order(7)
    void test7_BankAccountMethodsExist() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");

            Method depositMethod = cls.getMethod("deposit", double.class);
            Method withdrawalMethod = cls.getMethod("withdrawal", double.class);
            Method displayTransactionHistoryMethod = cls.getMethod("displayTransactionHistory");

            assertNotNull(depositMethod, "BankAccount should have a deposit method.");
            assertNotNull(withdrawalMethod, "BankAccount should have a withdrawal method.");
            assertNotNull(displayTransactionHistoryMethod, "BankAccount should have a displayTransactionHistory method.");
        } catch (NoSuchMethodException e) {
            fail("BankAccount should have deposit, withdrawal, and displayTransactionHistory methods.");
        } catch (ClassNotFoundException e) {
            fail("BankAccount class should exist.");
        }
    }

    @Test
    @Order(8)
    void test8_DepositMethodUpdatesBalanceAndTransactionHistory() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            Object bankAccount = constructor.newInstance("123456");

            Method depositMethod = cls.getMethod("deposit", double.class);
            Method getBalanceMethod = cls.getMethod("getBalance");
            Method getTransactionHistoryMethod = cls.getMethod("getTransactionHistory");

            depositMethod.invoke(bankAccount, 100.0);
            double balance = (double) getBalanceMethod.invoke(bankAccount);
            assertEquals(100.0, balance, 0.001, "Balance should be updated after deposit.");

            List<?> transactionHistory = (List<?>) getTransactionHistoryMethod.invoke(bankAccount);
            assertEquals(1, transactionHistory.size(), "Transaction history should have one entry after deposit.");

            Object transaction = transactionHistory.get(0);
            Class<?> transactionClass = Class.forName("Bank.Transaction");
            Method getTypeMethod = transactionClass.getMethod("getType");
            Method getAmountMethod = transactionClass.getMethod("getAmount");

            String type = (String) getTypeMethod.invoke(transaction);
            double amount = (double) getAmountMethod.invoke(transaction);

            assertEquals("deposit", type.toLowerCase(), "Transaction type should be 'deposit'.");
            assertEquals(100.0, amount, 0.001, "Transaction amount should be 100.0.");

        } catch (Exception e) {
            fail("Error testing deposit method: " + e.getMessage());
        }
    }

    @Test
    @Order(9)
    void test9_WithdrawalMethodUpdatesBalanceAndTransactionHistory() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            Object bankAccount = constructor.newInstance("123456");

            Method depositMethod = cls.getMethod("deposit", double.class);
            Method withdrawalMethod = cls.getMethod("withdrawal", double.class);
            Method getBalanceMethod = cls.getMethod("getBalance");
            Method getTransactionHistoryMethod = cls.getMethod("getTransactionHistory");

            depositMethod.invoke(bankAccount, 200.0);
            withdrawalMethod.invoke(bankAccount, 50.0);
            double balance = (double) getBalanceMethod.invoke(bankAccount);
            assertEquals(150.0, balance, 0.001, "Balance should be updated after withdrawal.");

            List<?> transactionHistory = (List<?>) getTransactionHistoryMethod.invoke(bankAccount);
            assertEquals(2, transactionHistory.size(), "Transaction history should have two entries.");

            Object transaction = transactionHistory.get(1);
            Class<?> transactionClass = Class.forName("Bank.Transaction");
            Method getTypeMethod = transactionClass.getMethod("getType");
            Method getAmountMethod = transactionClass.getMethod("getAmount");

            String type = (String) getTypeMethod.invoke(transaction);
            double amount = (double) getAmountMethod.invoke(transaction);

            assertEquals("withdrawal", type.toLowerCase(), "Transaction type should be 'withdrawal'.");
            assertEquals(50.0, amount, 0.001, "Transaction amount should be 50.0.");

        } catch (Exception e) {
            fail("Error testing withdrawal method: " + e.getMessage());
        }
    }

    @Test
    @Order(10)
    void test10_WithdrawalMethodPreventsOverdraft() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Constructor<?> constructor = cls.getConstructor(String.class);
            Object bankAccount = constructor.newInstance("123456");

            Method withdrawalMethod = cls.getMethod("withdrawal", double.class);
            Method getBalanceMethod = cls.getMethod("getBalance");

            // Try to withdraw without sufficient funds
            withdrawalMethod.invoke(bankAccount, 50.0);
            double balance = (double) getBalanceMethod.invoke(bankAccount);
            assertEquals(0.0, balance, 0.001, "Balance should not go negative.");

        } catch (InvocationTargetException e) {
            // Assuming the method throws an exception on insufficient funds
            Throwable cause = e.getCause();
            assertTrue(cause instanceof IllegalArgumentException, "Should throw IllegalArgumentException on insufficient funds.");
            assertEquals("Insufficient funds", cause.getMessage(), "Exception message should be 'Insufficient funds'.");
        } catch (Exception e) {
            fail("Error testing withdrawal method: " + e.getMessage());
        }
    }

    @Test
    @Order(11)
    void test11_DisplayTransactionHistoryMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankAccount");
            Method displayTransactionHistoryMethod = cls.getMethod("displayTransactionHistory");

            assertNotNull(displayTransactionHistoryMethod, "BankAccount should have a displayTransactionHistory method.");

        } catch (NoSuchMethodException e) {
            fail("BankAccount should have a displayTransactionHistory method.");
        } catch (ClassNotFoundException e) {
            fail("BankAccount class should exist.");
        }
    }
}

