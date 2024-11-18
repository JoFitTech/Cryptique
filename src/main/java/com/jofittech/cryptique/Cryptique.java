package com.jofittech.cryptique;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

public class Cryptique implements PasswordValidator {

    /*
     * 
     * 
     * Ideen:
     * 
     * -Sternzeichenzeug
     * 
     * 
     */
    ValidationResult invalidresult = new ValidationResult(false, "The password is invalid!");
    ValidationResult validresult = new ValidationResult(true, "The password is valid!");

    String bands = " TheBeatles TheRollingStones LedZeppelin Queen PinkFloyd TheWho U2 ACDC TheEagles Nirvana TheBeachBoys Metallica GunsNRoses FleetwoodMac TheDoors REM Radiohead Aerosmith RedHotChiliPeppers TheClash";
    String bandNames[] = bands.split(" ");
    String course = "Albrandt Baierle Baumann Berggold Buller Demirel Ehnle Gerlinger Gundel Hager Kollmann Krahl Lautner Mögerle Mottl Mumm Mustajbegovic Neumann Oláh Oturucu Poensgen Ranft Reger Scheibe Schirmbeck Schklar Schmelz Ulbrich Xenopoulos Zipse Zoumpoulakis";
    String courseNames[] = course.split(" ");

    private final Set<String> blacklist = Set.of("password", "123456", "admin", "qwerty", "passwort", "qwertz");

    LocalDate todaysDate = LocalDate.now();
    int month = todaysDate.getMonthValue();
    int day = todaysDate.getDayOfMonth();

    Random random = new Random();
    int zufallszahl = 20;
    // int zufallszahl = random.nextInt(16) + 10; //random.nextInt(16) generiert
    // Zahl 0-15. +10 verschiebst Bereich, Zahl --> 10-25 liegt (10 + 0 bis 10 +
    // 15).
    int versuche = 0;

    @Override
    public ValidationResult validate(String passwordtovalidate) {

        System.out.println("Your password-input is: " + passwordtovalidate);

        boolean lengthCheck = passwordtovalidate.length() >= 8;
        boolean bandcheck = containsBandName(passwordtovalidate);
        boolean capitalletter = containsCapitalLetter(passwordtovalidate);
        boolean specialcharacter = containsSpecialCharacter(passwordtovalidate);
        boolean coursecheck = containsCourse(passwordtovalidate);
        boolean numberAddition = numberAdd(passwordtovalidate);
        boolean datecheck = containsDate(passwordtovalidate);
        boolean blacklisted = isBlacklisted(passwordtovalidate);
        boolean allchecks = lengthCheck && bandcheck && numberAddition && capitalletter && specialcharacter && coursecheck && !blacklisted && datecheck;

        if (!bandcheck) {
            System.out.println("Password does not contain a band name!");
        }

        if (!lengthCheck) {
            System.out.println("Password is too short!");
        } else {
            System.out.println("Password is long enough!");
        }

        if (!numberAddition) {
            System.out.println("Password does not contain a number!");
        }

        if (!capitalletter) {
            System.out.println("Password does not contain a capital letter!");
        }

        if (!specialcharacter) {
            System.out.println("Password does not contain a special character!");
        }

        if (!coursecheck) {
            System.out.println("Password does not contain a name of the course WI24A3!");
        }

        if (!blacklisted) {
            System.out.println("Password does not contain a blacklisted word!");
        }

        if (allchecks) {
            System.out.println("Perfect! Password is valid!");
            return validresult;
        } else {
            System.out.println(validresult.message());
        }

        return invalidresult;
    }

    public boolean containsBandName(String password) {
        for (String band : bandNames) {
            if (password.toUpperCase().contains(band.toUpperCase())) {
                System.out.println(
                        "Good job! Your password contains min. one of the biggest bands of all time! (according to ChatGPT ;) ) ");
                System.out.println(band);
                return true;
            }
        }
        return false;
    }

    public boolean containsCapitalLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                System.out.println("Good job! Your password contains a capital letter!");
                return true;
            }
        }
        return false;
    }

    public boolean containsSpecialCharacter(String password) {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
        for (int i = 0; i < password.length(); i++) {
            if (specialCharacters.contains(String.valueOf(password.charAt(i)))) {
                System.out.println("Good job! Your password contains a special character!");
                return true;
            }
        }
        return false;
    }

    public boolean containsCourse(String password) {
        for (String course : courseNames) {
            if (password.toUpperCase().contains(course.toUpperCase())) {
                System.out.println("Good job! Your password contains min. one of the names of the course WI24A3!");
                System.out.println(course);
                return true;
            }
        }
        return false;

    }

    public boolean numberAdd(String password) {
        int summe = 0;

        System.out.println(zufallszahl);

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

    // future implementation of the use of the date
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

    public boolean isBlacklisted(String password) {
        for (String word : blacklist) {
            if (password.toLowerCase().contains(word)) {
                System.out.println("Password contains a blacklisted word!");
                return true;
            }
        }
        return false;
    }
}
