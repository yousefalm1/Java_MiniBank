package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountCreationTest {

    @Test
    @Order(1)
    void test1_CreateUserMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            Method createUserMethod = cls.getMethod("createUser", String.class, String.class);

            assertNotNull(createUserMethod, "BankSingleton should have a createUser method.");
            assertEquals(Class.forName("Bank.User"), createUserMethod.getReturnType(), "createUser method should return a User instance.");

        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have a createUser method with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton or User class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_CreateUserMethodModifiers() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");

            Method createUserMethod = cls.getMethod("createUser", String.class, String.class);

            assertTrue(Modifier.isPublic(createUserMethod.getModifiers()), "createUser method should be public.");

        } catch (NoSuchMethodException e) {
            fail("BankSingleton should have a createUser method with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("BankSingleton class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_UserConstructorExists() {
        try {
            Class<?> userClass = Class.forName("Bank.User");
            Constructor<?> constructor = userClass.getConstructor(String.class, String.class);
            assertNotNull(constructor, "User should have a constructor with parameters (String username, String password).");
        } catch (NoSuchMethodException e) {
            fail("User should have a constructor with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(4)
    void test4_CreateUserEnsuresUniqueUsernames() {
        try {
            Class<?> cls = Class.forName("Bank.BankSingleton");
            Method getInstanceMethod = cls.getMethod("getInstance");
            Object bankSingleton = getInstanceMethod.invoke(null);

            Method createUserMethod = cls.getMethod("createUser", String.class, String.class);

            // Create a user
            createUserMethod.invoke(bankSingleton, "testUser", "testPass");

            // Attempt to create another user with the same username
            assertThrows(InvocationTargetException.class, () -> {
                createUserMethod.invoke(bankSingleton, "testUser", "newPass");
            }, "Should throw an exception when creating a user with a duplicate username.");

        } catch (Exception e) {
            fail("Error testing account creation with duplicate username: " + e.getMessage());
        }
    }
}
