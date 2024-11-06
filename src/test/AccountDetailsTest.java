package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDetailsTest {

    @Test
    @Order(1)
    void test1_UserConstructorExists() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            Constructor<?> constructor = cls.getConstructor(String.class, String.class);
            assertNotNull(constructor, "User should have a constructor with parameters (String username, String password).");
        } catch (NoSuchMethodException e) {
            fail("User should have a constructor with parameters (String username, String password).");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_DisplayAccountDetailsMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Method displayAccountDetailsMethod = cls.getMethod("displayAccountDetails");

            assertNotNull(displayAccountDetailsMethod, "User should have a displayAccountDetails method.");

        } catch (NoSuchMethodException e) {
            fail("User should have a displayAccountDetails method with no parameters.");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_DisplayAccountDetailsMethodModifiers() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Method displayAccountDetailsMethod = cls.getMethod("displayAccountDetails");

            assertTrue(Modifier.isPublic(displayAccountDetailsMethod.getModifiers()), "displayAccountDetails method should be public.");

        } catch (NoSuchMethodException e) {
            fail("User should have a displayAccountDetails method with no parameters.");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }
}
