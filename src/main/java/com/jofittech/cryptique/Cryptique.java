package com.jofittech.cryptique;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

public class Cryptique implements PasswordValidator {

    /*
    
    
    Ideen: 
    
    -bands: eventuell muss mehr als eine band erhalten sein
    -kursliste
    -Zahlen
    -Zahlen addieren zu Ergebnis X
    -Sternzeichenzeug


     */
    ValidationResult invalidresult = new ValidationResult(false, "The password is invalid!");
    ValidationResult validresult = new ValidationResult(true, "The password is valid!");

    String text = " TheBeatles TheRollingStones LedZeppelin Queen PinkFloyd TheWho U2 ACDC TheEagles Nirvana TheBeachBoys Metallica GunsNRoses FleetwoodMac TheDoors REM Radiohead Aerosmith RedHotChiliPeppers TheClash";
    String bandNames[] = text.split(" ");

    @Override
    public ValidationResult validate(String passwordtovalidate) {

        System.out.println("Your password-input is: " + passwordtovalidate);

        boolean lengthCheck = passwordtovalidate.length() >= 8;
        boolean bandcheck = containsBandName(passwordtovalidate);
        boolean numbercheck = containsNumber(passwordtovalidate);

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

        if (bandcheck && lengthCheck && numbercheck) {
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
                System.out.println("Good job! Your password contains min. one of the biggest bands of all time! (according to ChatGPT ;) ) ");
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
}
