package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankSingletonTest {

    @Test
    @Order(1)
    void test1_BankSingletonClassExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");
            assertNotNull(cls, "BankSingleton class should exist.");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_BankSingletonConstructorIsPrivate() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");
            Constructor<?> constructor = cls.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()), "BankSingleton constructor should be private.");
        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have a private no-argument constructor.");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_BankSingletonIsSingleton() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            // Check for static getInstance method
            Method getInstanceMethod = cls.getDeclaredMethod("getInstance");
            assertTrue(Modifier.isStatic(getInstanceMethod.getModifiers()), "getInstance method should be static.");
            assertEquals(cls, getInstanceMethod.getReturnType(), "getInstance should return an instance of BankSingleton.");

            // Ensure only one instance is created
            Object instance1 = getInstanceMethod.invoke(null);
            Object instance2 = getInstanceMethod.invoke(null);
            assertSame(instance1, instance2, "getInstance should always return the same instance.");

        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have a static getInstance method.");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        } catch (Exception e) {
            fail("Error testing singleton behavior: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    void test4_BankSingletonManagesUserAccounts() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            Field usersField = cls.getDeclaredField("users");

            assertNotNull(usersField, "BankSingleton should have a users field.");
            assertTrue(Modifier.isPrivate(usersField.getModifiers()), "users field should be private.");
            assertEquals(java.util.List.class, usersField.getType(), "users field should be of type List.");

        } catch (NoSuchFieldException e) {
            fail("BankSingleton should have a users field to manage user accounts.");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        }
    }
}
