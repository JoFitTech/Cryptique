package com.jofittech.cryptique;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;
import java.util.Random;

public class Cryptique implements PasswordValidator {

    /*
     * 
     * 
     * Ideen:
     * 
     * -Zahlen
     * -Zahlen addieren zu Ergebnis X
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

    Random random = new Random();
    int zufallszahl = random.nextInt(16) + 10; //random.nextInt(16) generiert Zahl 0-15. +10 verschiebst Bereich, Zahl --> 10-25 liegt (10 + 0 bis 10 + 15).
    int versuche = 0;

    @Override
    public ValidationResult validate(String passwordtovalidate) {

        System.out.println("Your password-input is: " + passwordtovalidate);

        boolean numberAdd = numberAdd(passwordtovalidate);
        boolean lengthCheck = passwordtovalidate.length() >= 8;
        boolean bandcheck = containsBandName(passwordtovalidate);
        boolean numbercheck = containsNumber(passwordtovalidate);
        boolean capitalletter = containsCapitalLetter(passwordtovalidate);
        boolean specialcharacter = containsSpecialCharacter(passwordtovalidate);
        boolean coursecheck = containsCourse(passwordtovalidate);

        boolean allchecks = lengthCheck && bandcheck && numbercheck && capitalletter && specialcharacter && coursecheck;

        if (!bandcheck) {
            System.out.println("Password does not contain a band name!");
        }

        if (!lengthCheck) {
            System.out.println("Password is too short!");
        } else {
            System.out.println("Password is long enough!");
        }

        if (!numbercheck) {
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

    public boolean containsNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                System.out.println("Good job! Your password contains a number!");
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
        while (versuche < 3 && !passwortGueltig) {
            System.out.println("Die Summe der Zahlen muss " + zufallszahl + " ergeben):");
            String passwort = scanner.nextLine();

            if (istPasswortGueltig(passwort, zufallszahl)) {
                System.out.println("Passwort ist gültig!");
                passwortGueltig = true;
            } else {
                versuche++;
                System.out.println("Ungültiges Passwort. Versuche übrig bis eine neue Zahl generiert wird: " + (3 - versuche));
            }
        }
    }

}
