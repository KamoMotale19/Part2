/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package loginandsignup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class signupTest {

    private signup instance;

    @BeforeEach
    public void setUp() {
        instance = new signup();
    }

    @Test
    public void testCheckUsername_Valid() {
        assertTrue(instance.checkUsername("user_"), "Username with underscore and <= 5 chars should be valid");
    }

    @Test
    public void testCheckUsername_Invalid_NoUnderscore() {
        assertFalse(instance.checkUsername("user"), "Username without underscore should be invalid");
    }

    @Test
    public void testCheckUsername_Invalid_TooLong() {
        assertFalse(instance.checkUsername("username_123"), "Username longer than 5 chars should be invalid");
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(instance.checkPasswordComplexity("Strong1!"), "Password meeting all complexity rules should be valid");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoUppercase() {
        assertFalse(instance.checkPasswordComplexity("weakpass1!"), "Password without uppercase should be invalid");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoDigit() {
        assertFalse(instance.checkPasswordComplexity("Password!"), "Password without number should be invalid");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_TooShort() {
        assertFalse(instance.checkPasswordComplexity("Pw1!"), "Password shorter than 8 chars should be invalid");
    }

    @Test
    public void testCheckPhoneNumber_Valid() {
        assertTrue(instance.checkPhoneNumber("0831234567"), "Valid 10-digit phone number should pass");
    }

    @Test
    public void testCheckPhoneNumber_Invalid_Short() {
        assertFalse(instance.checkPhoneNumber("12345"), "Too short phone number should fail");
    }

    @Test
    public void testCheckPhoneNumber_Invalid_NonNumeric() {
        assertFalse(instance.checkPhoneNumber("083ABC1234"), "Phone number with letters should fail");
    }

    @Test
    public void testRegisterUser_Valid() {
        boolean result = instance.registerUser("user_", "Strong1!", "0831234567");
        assertTrue(result, "Valid username, password, and phone number should register successfully");
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        boolean result = instance.registerUser("user", "Strong1!", "0831234567");
        assertFalse(result, "Invalid username should cause registration to fail");
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        boolean result = instance.registerUser("user_", "weakpass", "0831234567");
        assertFalse(result, "Invalid password should cause registration to fail");
    }

    @Test
    public void testRegisterUser_InvalidPhoneNumber() {
        boolean result = instance.registerUser("user_", "Strong1!", "12345");
        assertFalse(result, "Invalid phone number should cause registration to fail");
    }

    @Test
    public void testReturnLoginStatus_Success() {
        String status = instance.returnLoginStatus("user_", "Strong1!");
        assertEquals("A successfull login", status);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        String status = instance.returnLoginStatus("user", "weak");
        assertEquals("A failed login", status);
    }
}
