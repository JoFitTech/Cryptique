package com.jofittech.cryptique;

import java.time.LocalDate;
import java.util.Set;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

/**
 * The {@code Cryptique} class implements a password validator. It checks
 * passwords against various criteria, such as length, inclusion of special
 * characters, uppercase and lowercase letters, number sums, course names,
 * blacklist checks, and more.
 */
public class Cryptique implements PasswordValidator {

    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String green = "\u001B[32m";

    // Validation result for an invalid result
    ValidationResult invalidresult = new ValidationResult(false, red + "The password is invalid!" + reset);
    // Validation result for a valid result
    ValidationResult validresult = new ValidationResult(true, green + "The password is valid!" + reset);

    // a list of the biggest bands of all time (according to ChatGPT ;))
    String bands = "TheBeatles TheRollingStones LedZeppelin Queen PinkFloyd TheWho U2 ACDC TheEagles Nirvana TheBeachBoys Metallica GunsNRoses FleetwoodMac TheDoors REM Radiohead Aerosmith RedHotChiliPeppers TheClash";
    /**
     * Array of band names split from the {@code bands} string.
     */
    String bandNames[] = bands.split(" ");

    // a list of our course (WI24A3)
    String course = "Albrandt Baierle Baumann Berggold Buller Demirel Ehnle Gerlinger Gundel Hager Kollmann Krahl Lautner Mögerle Mottl Mumm Mustajbegovic Neumann Oláh Oturucu Poensgen Ranft Reger Scheibe Schirmbeck Schklar Schmelz Ulbrich Xenopoulos Zipse Zoumpoulakis";
    /**
     * Array of course names split from the {@code course} string.
     */
    String courseNames[] = course.split(" ");

    // a list of commonly used passwords that the password should not contain
    private final Set<String> blacklist = Set.of("password", "123456", "admin", "qwerty", "passwort", "qwertz");

    // getting todays date
    LocalDate todaysDate = LocalDate.now();
    // getting the value of the current month 
    int month = todaysDate.getMonthValue();
    // getting the value of the current day
    int day = todaysDate.getDayOfMonth();

    //Random random = new Random(); (not currently used)
    int zufallszahl = 20;// int zufallszahl = random.nextInt(16) + 10; //random.nextInt(16) generiert Zahl 0-15. +10 verschiebst Bereich, Zahl --> 10-25 liegt (10 + 0 bis 10 + 15).
    //int versuche = 0; (not currently used)

    /**
     * Validates a given password against multiple criteria.
     *
     * @param passwordtovalidate the password to validate
     * @return a {@code ValidationResult} indicating whether the password is
     * valid
     */
    @Override
    public ValidationResult validate(String passwordtovalidate) {

        try {

            boolean lengthCheck = passwordtovalidate.length() >= 12;
            boolean bandcheck = containsBandName(passwordtovalidate);
            boolean capitalletter = containsCapitalLetter(passwordtovalidate);
            boolean lowercaseletter = containsLowerCaseLetter(passwordtovalidate);
            boolean specialcharacter = containsSpecialCharacter(passwordtovalidate);
            boolean coursecheck = containsCourse(passwordtovalidate);
            boolean numberAddition = numberAdd(passwordtovalidate);
            boolean datecheck = containsDate(passwordtovalidate);

            boolean blacklisted = isBlacklisted(passwordtovalidate + "\n");

            boolean allchecks = lengthCheck && bandcheck && numberAddition && capitalletter && lowercaseletter && specialcharacter && coursecheck && !blacklisted && datecheck;

            if (!allchecks) {
                System.out.println("These validations aren't checked: ");
                System.out.println();
            }

            // debugging logs for every check
            if (!bandcheck) {
                System.out.println(red + "Bandnames: " + reset + "Password does not contain a band name of one of the biggeset bands of all time (according to ChatGPT ;) )!");
            }

            if (!lengthCheck) {
                System.out.println(red + "Length: " + reset + "Password is too short! (minimum 12 characters)");
            }

            if (!datecheck) {
                System.out.println(red + "Date: " + reset + "Your password does not contain todays date! (dd/mm)");
            }

            if (!numberAddition) {
                System.out.println(red + "Add-Up: " + reset + "The digits in your password do not add up to 20!");
            }

            if (!capitalletter) {
                System.out.println(red + "Capital-Letter: " + reset + "Password does not contain a capital letter!");
            }

            if (!lowercaseletter) {
                System.out.println(red + "Lowercase-Letter: " + reset + "Password does not contain a lowercase letter!");
            }

            if (!specialcharacter) {
                System.out.println(red + "Special-Character: " + reset + "Password does not contain a special character!");
            }

            if (!coursecheck) {
                System.out.println(red + "Course-Surname: " + reset + "Password does not contain a surname of the course WI24A3!");
            }

            if (blacklisted) {
                System.out.println(red + "Blacklisted: " + reset + "Password contains a blacklisted word!");
            }

            // checks if all conditions are true and returns the valid or invalid result, depending on the "true" conditions
            if (allchecks) {
                System.out.println(green + "Great! There are no unchecked validations!" + reset);
                return validresult;
            } else {
                return invalidresult;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(red + "Error: " + reset + e.getMessage());
            return invalidresult;
        }

    }

    /**
     * Checks if the password contains the name of a popular band.
     *
     * @param password the password to check
     * @return {@code true} if the password contains a band name, otherwise
     * {@code false}
     */
    public boolean containsBandName(String password) {
        for (String band : bandNames) {
            if (password.toUpperCase().contains(band.toUpperCase())) {
                //System.out.println("Good job! Your password contains min. one of the biggest bands of all time! test (according to ChatGPT ;) ) ");
                // System.out.println(band);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the password contains at least one uppercase letter.
     *
     * @param password the password to check
     * @return {@code true} if the password contains an uppercase letter,
     * otherwise {@code false}
     */
    public boolean containsCapitalLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                //System.out.println("Good job! Your password contains a capital letter!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the password contains at least one lowercase letter.
     *
     * @param password the password to check
     * @return {@code true} if the password contains a lowercase letter,
     * otherwise {@code false}
     */
    public boolean containsLowerCaseLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                //System.out.println("Good job! Your password contains a lowercase letter!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the password contains at least one special character.
     *
     * @param password the password to check
     * @return {@code true} if the password contains a special character,
     * otherwise {@code false}
     */
    public boolean containsSpecialCharacter(String password) {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~ ";
        for (int i = 0; i < password.length(); i++) {
            if (specialCharacters.contains(String.valueOf(password.charAt(i)))) {
                //System.out.println("Good job! Your password contains a special character!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the password contains the name of a course participant.
     *
     * @param password the password to check
     * @return {@code true} if the password contains a course name, otherwise
     * {@code false}
     */
    public boolean containsCourse(String password) {
        for (String courseWI : courseNames) {
            if (password.toUpperCase().contains(courseWI.toUpperCase())) {
                //System.out.println("Good job! Your password contains min. one of the surnames of the course WI24A3!");
                // System.out.println(course);
                return true;
            }
        }
        return false;

    }

    /**
     * Checks if the sum of the digits in the password matches the predefined
     * random sum.
     *
     * @param password the password to check
     * @return {@code true} if the sum of digits matches the target, otherwise
     * {@code false}
     */
    public boolean numberAdd(String password) {
        int summe = 0;

        // System.out.println(zufallszahl);
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                summe += Character.getNumericValue(password.charAt(i));

            }
        }
        return summe == zufallszahl;
    }

    /**
     * Checks if the password contains the current date.
     *
     * @param password the password to check
     * @return {@code true} if the password contains the current day and month,
     * otherwise {@code false}
     */
    public boolean containsDate(String password) {

        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month);
        if (password.toLowerCase().contains(dayString)) {
            if (password.toLowerCase().contains(monthString)) {
                //System.out.println("Good Job! Your password contains todays date");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the password contains a word from the blacklist.
     *
     * @param password the password to check
     * @return {@code true} if the password contains a blacklisted word,
     * otherwise {@code false}
     */
    public boolean isBlacklisted(String password) {
        for (String word : blacklist) {
            if (password.toLowerCase().contains(word)) {
                System.out.println(red + "Blacklist: " + reset + blacklist + "\n");
                return true;
            }
        }
        return false;
    }
}
