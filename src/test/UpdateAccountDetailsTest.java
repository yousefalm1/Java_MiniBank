package test;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateAccountDetailsTest {

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
    void test2_UpdateUsernameMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Method updateUsernameMethod = cls.getMethod("updateUsername", String.class);

            assertNotNull(updateUsernameMethod, "User should have an updateUsername method.");

        } catch (NoSuchMethodException e) {
            fail("User should have an updateUsername method with parameter (String newUsername).");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(3)
    void test3_UpdatePasswordMethodExists() {
        try {
            Class<?> cls = Class.forName("Bank.User");

            Method updatePasswordMethod = cls.getMethod("updatePassword", String.class);

            assertNotNull(updatePasswordMethod, "User should have an updatePassword method.");

        } catch (NoSuchMethodException e) {
            fail("User should have an updatePassword method with parameter (String newPassword).");
        } catch (ClassNotFoundException e) {
            fail("User class should exist.");
        }
    }

    @Test
    @Order(4)
    void test4_UpdateUsernameMethodUpdatesUsername() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            Constructor<?> constructor = cls.getConstructor(String.class, String.class);
            Object user = constructor.newInstance("oldUsername", "password");

            Method updateUsernameMethod = cls.getMethod("updateUsername", String.class);
            Method getUsernameMethod = cls.getMethod("getUsername");

            updateUsernameMethod.invoke(user, "newUsername");
            String username = (String) getUsernameMethod.invoke(user);

            assertEquals("newUsername", username, "Username should be updated to newUsername.");

        } catch (Exception e) {
            fail("Error testing updateUsername method: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    void test5_UpdatePasswordMethodUpdatesPassword() {
        try {
            Class<?> cls = Class.forName("Bank.User");
            Constructor<?> constructor = cls.getConstructor(String.class, String.class);
            Object user = constructor.newInstance("username", "oldPassword");

            Method updatePasswordMethod = cls.getMethod("updatePassword", String.class);

            updatePasswordMethod.invoke(user, "newPassword");
            // Since getPassword should not exist, we need to check via other means
            Field passwordField = cls.getDeclaredField("password");
            passwordField.setAccessible(true);
            String password = (String) passwordField.get(user);

            assertEquals("newPassword", password, "Password should be updated to newPassword.");

        } catch (Exception e) {
            fail("Error testing updatePassword method: " + e.getMessage());
        }
    }
}
