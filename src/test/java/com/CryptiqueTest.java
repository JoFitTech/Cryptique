package com;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cthiebaud.passwordvalidator.ValidationResult;
import com.jofittech.cryptique.Cryptique;

class CryptiqueTest {

    private Cryptique cryptique;

    @BeforeEach
    void setUp() {
        cryptique = new Cryptique();
    }

    @Test
    void testValidateValidPassword() {
        String validPassword = "Queen@25115Albrandt15";
        ValidationResult result = cryptique.validate(validPassword);
        assertTrue(result.isValid(), "Password should be valid.");
    }

    @Test
    void testValidateInvalidPasswordShort() {
        String invalidPassword = "Short1!";
        ValidationResult result = cryptique.validate(invalidPassword);
        assertFalse(result.isValid(), "Password should be invalid because it is too short.");
    }

    @Test
    void testContainsBandName() {
        assertTrue(cryptique.containsBandName("TheBeatles123!"), "Password should contain a band name.");
        assertFalse(cryptique.containsBandName("RandomPassword123!"), "Password should not contain a band name.");
    }

    @Test
    void testContainsCourseName() {
        assertTrue(cryptique.containsCourse("WelcomeAlbrandt@2024"), "Password should contain a course name.");
        assertFalse(cryptique.containsCourse("NoCourseNameHere!"), "Password should not contain a course name.");
    }

    @Test
    void testContainsCapitalLetter() {
        assertTrue(cryptique.containsCapitalLetter("HasCapitalLetter"), "Password should contain at least one capital letter.");
        assertFalse(cryptique.containsCapitalLetter("nocapitalletter"), "Password should not contain a capital letter.");
    }

    @Test
    void testContainsLowerCaseLetter() {
        assertTrue(cryptique.containsLowerCaseLetter("HasLowercase"), "Password should contain at least one lowercase letter.");
        assertFalse(cryptique.containsLowerCaseLetter("NOLOWERCASE"), "Password should not contain a lowercase letter.");
    }

    @Test
    void testContainsSpecialCharacter() {
        assertTrue(cryptique.containsSpecialCharacter("Special@Character"), "Password should contain a special character.");
        assertFalse(cryptique.containsSpecialCharacter("NoSpecialCharacter"), "Password should not contain a special character.");
    }

    @Test
    void testNumberAdd() {
        // Assume zufallszahl is 20
        assertTrue(cryptique.numberAdd("Password123455"), "Password should have digits summing up to the predefined value.");
        assertFalse(cryptique.numberAdd("Password11"), "Password digits do not sum up to the predefined value.");
    }

    @Test
    void testContainsDate() {
        LocalDate today = LocalDate.now();
        String day = String.valueOf(today.getDayOfMonth());
        String month = String.valueOf(today.getMonthValue());
        assertTrue(cryptique.containsDate("Password" + day + month + "!"), "Password should contain today's date.");
        assertFalse(cryptique.containsDate("Password2023!"), "Password should not contain today's date.");
    }

    @Test
    void testIsBlacklisted() {
        assertTrue(cryptique.isBlacklisted("Password123"), "Password should be blacklisted.");
        assertFalse(cryptique.isBlacklisted("SafePasswor!"), "Password should not be blacklisted.");
    }

    @Test
    void testValidateBlacklistedPassword() {
        String blacklistedPassword = "123456!";
        ValidationResult result = cryptique.validate(blacklistedPassword);
        assertFalse(result.isValid(), "Password should be invalid because it contains a blacklisted word.");
    }
}
