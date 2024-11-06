package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionTest {

    @Test
    @Order(1)
    void test1_TransactionClassExists() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");
            assertNotNull(cls, "Transaction class should exist.");
        } catch (ClassNotFoundException e) {
            fail("Transaction class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_TransactionConstructorExists() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");
            Constructor<?> constructor = cls.getConstructor(Date.class, String.class, double.class);
            assertNotNull(constructor, "Transaction should have a constructor with parameters (Date date, String type, double amount).");
        } catch (NoSuchMethodException e) {
            fail("Transaction should have a constructor with parameters (Date date, String type, double amount).");
        } catch (ClassNotFoundException e) {
            fail("Transaction class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_TransactionAttributesExist() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");

            Field dateField = cls.getDeclaredField("date");
            Field typeField = cls.getDeclaredField("type");
            Field amountField = cls.getDeclaredField("amount");

            assertNotNull(dateField, "Transaction should have a date attribute.");
            assertNotNull(typeField, "Transaction should have a type attribute.");
            assertNotNull(amountField, "Transaction should have an amount attribute.");
        } catch (Exception e) {
            fail("Transaction should have date, type, and amount attributes.");
        }
    }

    @Test
    @Order(4)
    void test4_TransactionAttributeTypes() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");

            Field dateField = cls.getDeclaredField("date");
            Field typeField = cls.getDeclaredField("type");
            Field amountField = cls.getDeclaredField("amount");

            assertEquals(Date.class, dateField.getType(), "date should be of type Date.");
            assertEquals(String.class, typeField.getType(), "type should be of type String.");
            assertEquals(double.class, amountField.getType(), "amount should be of type double.");

        } catch (Exception e) {
            fail("Error checking attribute types.");
        }
    }

    @Test
    @Order(5)
    void test5_TransactionEncapsulation() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");

            Field dateField = cls.getDeclaredField("date");
            Field typeField = cls.getDeclaredField("type");
            Field amountField = cls.getDeclaredField("amount");

            assertTrue(Modifier.isPrivate(dateField.getModifiers()), "date should be private.");
            assertTrue(Modifier.isPrivate(typeField.getModifiers()), "type should be private.");
            assertTrue(Modifier.isPrivate(amountField.getModifiers()), "amount should be private.");

        } catch (Exception e) {
            fail("Error checking encapsulation.");
        }
    }

    @Test
    @Order(6)
    void test6_TransactionGettersSettersExist() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");

            Method getDate = cls.getMethod("getDate");
            Method setDate = cls.getMethod("setDate", Date.class);
            Method getType = cls.getMethod("getType");
            Method setType = cls.getMethod("setType", String.class);
            Method getAmount = cls.getMethod("getAmount");
            Method setAmount = cls.getMethod("setAmount", double.class);

            assertNotNull(getDate, "Transaction should have getDate method.");
            assertNotNull(setDate, "Transaction should have setDate method.");
            assertNotNull(getType, "Transaction should have getType method.");
            assertNotNull(setType, "Transaction should have setType method.");
            assertNotNull(getAmount, "Transaction should have getAmount method.");
            assertNotNull(setAmount, "Transaction should have setAmount method.");

        } catch (NoSuchMethodException e) {
            fail("Transaction should have getters and setters for all attributes.");
        } catch (ClassNotFoundException e) {
            fail("Transaction class should exist.");
        }
    }

    @Test
    @Order(7)
    void test7_TransactionConstructorInitializesAttributes() {
        try {
            Class<?> cls = Class.forName("Bank.Transaction");
            Constructor<?> constructor = cls.getConstructor(Date.class, String.class, double.class);

            Date date = new Date();
            String type = "Deposit";
            double amount = 100.0;

            Object transaction = constructor.newInstance(date, type, amount);

            Method getDateMethod = cls.getMethod("getDate");
            Method getTypeMethod = cls.getMethod("getType");
            Method getAmountMethod = cls.getMethod("getAmount");

            assertEquals(date, getDateMethod.invoke(transaction), "Date should be initialized correctly.");
            assertEquals(type, getTypeMethod.invoke(transaction), "Type should be initialized correctly.");
            assertEquals(amount, getAmountMethod.invoke(transaction), "Amount should be initialized correctly.");

        } catch (Exception e) {
            fail("Error testing Transaction constructor: " + e.getMessage());
        }
    }
}
