package test;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

    @Test
    @Order(1)
    void test1_UserClassExists() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            assertNotNull(cls, "User class should exist.");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(2)
    void test2_UserConstructorExists() {
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
    @Order(3)
    void test3_UserAttributesExist() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Field usernameField = cls.getDeclaredField("username");
            Field passwordField = cls.getDeclaredField("password");
            Field accountField = cls.getDeclaredField("account");

            assertNotNull(usernameField, "User should have a username attribute.");
            assertNotNull(passwordField, "User should have a password attribute.");
            assertNotNull(accountField, "User should have an account attribute.");
        } catch (Exception e) {
            fail("User should have username, password, and account attributes.");
        }
    }

    @Test
    @Order(4)
    void test4_UserAttributeTypes() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Field usernameField = cls.getDeclaredField("username");
            Field passwordField = cls.getDeclaredField("password");
            Field accountField = cls.getDeclaredField("account");

            assertEquals(String.class, usernameField.getType(), "username should be of type String.");
            assertEquals(String.class, passwordField.getType(), "password should be of type String.");
            assertEquals(Class.forName("Bank.BankAccount"), accountField.getType(), "account should be of type BankAccount.");

        } catch (Exception e) {
            fail("Error checking attribute types.");
        }
    }

    @Test
    @Order(5)
    void test5_UserEncapsulation() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Field usernameField = cls.getDeclaredField("username");
            Field passwordField = cls.getDeclaredField("password");
            Field accountField = cls.getDeclaredField("account");

            assertTrue(Modifier.isPrivate(usernameField.getModifiers()), "username should be private.");
            assertTrue(Modifier.isPrivate(passwordField.getModifiers()), "password should be private.");
            assertTrue(Modifier.isPrivate(accountField.getModifiers()), "account should be private.");

        } catch (Exception e) {
            fail("Error checking encapsulation.");
        }
    }

    @Test
    @Order(6)
    void test6_UserGettersSettersExist() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Method getUsername = cls.getMethod("getUsername");
            Method setUsername = cls.getMethod("setUsername", String.class);
            Method getAccount = cls.getMethod("getAccount");
            Method setAccount = cls.getMethod("setAccount", Class.forName("Bank.BankAccount"));

            assertNotNull(getUsername, "User should have getUsername method.");
            assertNotNull(setUsername, "User should have setUsername method.");
            assertNotNull(getAccount, "User should have getAccount method.");
            assertNotNull(setAccount, "User should have setAccount method.");

        } catch (NoSuchMethodException e) {
            fail("User should have getters and setters for username and account.");
        } catch (ClassNotFoundException e) {
            fail("BankAccount or User class not found.");
        }
    }

    @Test
    @Order(7)
    void test7_PasswordEncapsulation() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            Field passwordField = cls.getDeclaredField("password");

            assertTrue(Modifier.isPrivate(passwordField.getModifiers()), "Password field should be private.");
            assertFalse(Modifier.isStatic(passwordField.getModifiers()), "Password field should not be static.");
            assertFalse(Modifier.isPublic(passwordField.getModifiers()), "Password field should not be public.");

        } catch (NoSuchFieldException e) {
            fail("User should have a password field.");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(8)
    void test8_UpdateUsernameMethodUpdatesUsername() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            Constructor<?> constructor = cls.getConstructor(String.class, String.class);
            Object user = constructor.newInstance("oldUsername", "password");

            Method setUsernameMethod = cls.getMethod("setUsername", String.class);
            Method getUsernameMethod = cls.getMethod("getUsername");

            setUsernameMethod.invoke(user, "newUsername");
            String username = (String) getUsernameMethod.invoke(user);

            assertEquals("newUsername", username, "Username should be updated to newUsername.");

        } catch (Exception e) {
            fail("Error testing username update: " + e.getMessage());
        }
    }
}
