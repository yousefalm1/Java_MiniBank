package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationTest {

    @Test
    @Order(1)
    void test1_BankSingletonAddsAndRetrievesUsers() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");
            Method getInstanceMethod = cls.getMethod("getInstance");
            Object bankSingleton = getInstanceMethod.invoke(null);

            Method createUserMethod = cls.getMethod("createUser", String.class, String.class);
            Method authenticateMethod = cls.getMethod("authenticate", String.class, String.class);

            // Create a user
            Object user = createUserMethod.invoke(bankSingleton, "testUser2", "testPass");

            // Authenticate the user
            Object authenticatedUser = authenticateMethod.invoke(bankSingleton, "testUser2", "testPass");

            assertNotNull(authenticatedUser, "Authenticated user should not be null.");
            assertEquals(user, authenticatedUser, "Authenticated user should match the created user.");

        } catch (InvocationTargetException e) {
            fail("Error during method invocation: " + e.getCause());
        } catch (Exception e) {
            fail("Error testing BankSingleton user management: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    void test2_AuthenticateMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            Method authenticateMethod = cls.getMethod("authenticate", String.class, String.class);

            assertNotNull(authenticateMethod, "BankSingleton should have an authenticate method.");
            assertEquals(Class.forName("Bank.User"), authenticateMethod.getReturnType(), "authenticate method should return a User instance.");

        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have an authenticate method with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton or User class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_AuthenticateMethodModifiers() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            Method authenticateMethod = cls.getMethod("authenticate", String.class, String.class);

            assertTrue(Modifier.isPublic(authenticateMethod.getModifiers()), "authenticate method should be public.");

        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have an authenticate method with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        }
    }

    @Test
    @Order(4)
    void test4_AuthenticateSucceedsWithCorrectCredentials() {
        try {
            Class<?> bankSingletonClass = Class.forName("Bank.BankSingleton");
            Method getInstanceMethod = bankSingletonClass.getMethod("getInstance");
            Object bankSingleton = getInstanceMethod.invoke(null);

            Method createUserMethod = bankSingletonClass.getMethod("createUser", String.class, String.class);
            createUserMethod.invoke(bankSingleton, "testUser3", "testPass");

            Method authenticateMethod = bankSingletonClass.getMethod("authenticate", String.class, String.class);
            Object authenticatedUser = authenticateMethod.invoke(bankSingleton, "testUser3", "testPass");

            assertNotNull(authenticatedUser, "Authentication should succeed with correct credentials.");

        } catch (Exception e) {
            fail("Error testing successful authentication: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    void test5_AuthenticateFailsWithIncorrectCredentials() {
        try {
            Class<?> bankSingletonClass = Class.forName("Bank.BankSingleton");
            Method getInstanceMethod = bankSingletonClass.getMethod("getInstance");
            Object bankSingleton = getInstanceMethod.invoke(null);

            Method createUserMethod = bankSingletonClass.getMethod("createUser", String.class, String.class);
            createUserMethod.invoke(bankSingleton, "testUser4", "testPass");

            Method authenticateMethod = bankSingletonClass.getMethod("authenticate", String.class, String.class);
            Object authenticatedUser = authenticateMethod.invoke(bankSingleton, "testUser4", "wrongPass");

            assertNull(authenticatedUser, "Authentication should fail with incorrect credentials.");

        } catch (Exception e) {
            fail("Error testing authentication failure: " + e.getMessage());
        }
    }

    @Test
    @Order(6)
    void test6_AuthenticateHandlesNullValues() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");
            Method getInstanceMethod = cls.getMethod("getInstance");
            Object bankSingleton = getInstanceMethod.invoke(null);

            Method authenticateMethod = cls.getMethod("authenticate", String.class, String.class);

            // Attempt to authenticate with null values
            Object authenticatedUser = authenticateMethod.invoke(bankSingleton, null, null);

            assertNull(authenticatedUser, "Authentication should fail with null credentials.");

        } catch (Exception e) {
            fail("Error testing authentication with null values: " + e.getMessage());
        }
    }
}
