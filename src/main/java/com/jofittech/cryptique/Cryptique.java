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

    // Validation result for an invalid result
    ValidationResult invalidresult = new ValidationResult(false, "The password is invalid!");
    // Validation result for a valid result
    ValidationResult validresult = new ValidationResult(true, "The password is valid!");

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

        System.out.println();
        System.out.println("Your password-input is: " + passwordtovalidate);
        System.out.println();

        boolean lengthCheck = passwordtovalidate.length() >= 8;
        boolean bandcheck = containsBandName(passwordtovalidate);
        boolean capitalletter = containsCapitalLetter(passwordtovalidate);
        boolean lowercaseletter = containsLowerCaseLetter(passwordtovalidate);
        boolean specialcharacter = containsSpecialCharacter(passwordtovalidate);
        boolean coursecheck = containsCourse(passwordtovalidate);
        boolean numberAddition = numberAdd(passwordtovalidate);
        boolean datecheck = containsDate(passwordtovalidate);

        System.out.println();
        boolean blacklisted = isBlacklisted(passwordtovalidate);
        System.out.println();
        boolean allchecks = lengthCheck && bandcheck && numberAddition && capitalletter && lowercaseletter && specialcharacter && coursecheck && !blacklisted && datecheck;

        if (!blacklisted) {
            System.out.println("Great! Password does not contain a blacklisted word!");
        }

        System.out.println();
        System.out.println("These validations aren't checked!: ");
        System.out.println();

        // debugging logs for every check
        if (!bandcheck) {
            System.out.println("Password does not contain a band name of one of the biggeset bands of all time (according to ChatGPT ;) )!");
        }

        if (!lengthCheck) {
            System.out.println("Password is too short!");
        }

        if (!numberAddition) {
            System.out.println("The digits in your password do not add up to 20!");
        }

        if (!capitalletter) {
            System.out.println("Password does not contain a capital letter!");
        }

        if (!lowercaseletter) {
            System.out.println("Password does not contain a lowercase letter!");
        }

        if (!specialcharacter) {
            System.out.println("Password does not contain a special character!");
        }

        if (!coursecheck) {
            System.out.println("Password does not contain a name of the course WI24A3!");
        }

        // checks if all conditions are true and returns the valid or invalid result, depending on the "true" conditions
        if (allchecks) {
            System.out.println("Great! There are no unchecked validations!");
            System.out.println();
            return validresult;
        } else {
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
                System.out.println("Good job! Your password contains min. one of the biggest bands of all time! test (according to ChatGPT ;) ) ");
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
                System.out.println("Good job! Your password contains a capital letter!");
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
                System.out.println("Good job! Your password contains a lowercase letter!");
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
                System.out.println("Good job! Your password contains a special character!");
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
        for (String course : courseNames) {
            if (password.toUpperCase().contains(course.toUpperCase())) {
                System.out.println("Good job! Your password contains min. one of the names of the course WI24A3!");
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
        if (summe == zufallszahl) {
            System.out.println("Good job! Your password contains a number that adds up to " + zufallszahl);
            return true;
        }
        return false;
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
                System.out.println("Good Job! Your password contains todays date");
                return true;
            } else {
                System.out.println("Your password contains the day of the month but not the month");
                return false;
            }
        } else {
            System.out.println("Your password does not contain todays date");
            return false;
        }

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
                System.out.println("!!ATTENTION!! Your password contains a blacklisted word!");
                return true;
            }
        }
        return false;
    }
}
