package com.jofittech.cryptique;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

public class Cryptique implements PasswordValidator {

    /*
    Problem: Password zu kurz aber trotzdem valid
    
    
    Ideen: 
    
    -bands: eventuell muss merh als eine band erhalten sein
    -kursliste
    -Zahlen
    -Zahlen addieren zu Ergebnis X
    -Sternzeichenzeug


     */
    ValidationResult validresult = new ValidationResult(true, "The password is invalid!");

    String text = """
TheBeatles TheRollingStones LedZeppelin Queen PinkFloyd TheWho U2 ACDC TheEagles 
Nirvana TheBeachBoys Metallica GunsNRoses FleetwoodMac TheDoors REM Radiohead Aerosmith 
RedHotChiliPeppers TheClash
                """;
    String arr[] = text.split(" ");

    @Override
    public ValidationResult validate(String passwordtovalidate) {

        boolean check;
        boolean textCheck = false;

        System.out.println("Your password-input is: " + passwordtovalidate);

        if (passwordtovalidate.length() > 8) {
            check = validresult.isValid();
            System.out.println("Password is long enough! Good Job!");

        } else {
            check = !validresult.isValid();
            System.out.println("Password is to short! Please enter a password with min. eight characters!");

        }

        for (String arr1 : arr) {
            if (passwordtovalidate.toUpperCase().contains(arr1.toUpperCase())) {
                check = validresult.isValid();
                System.out.println("Good job! Your password contains min. one of the biggest bands of all time! (according to ChatGPT ;) ) ");
                System.out.println(arr1);
                textCheck = true;
                break;
            } else {
                textCheck = false;
            }
        }

        if (!textCheck) {
            check = !validresult.isValid();
            System.out.println("Please enter a password which contains min. one of the biggest bands of all time! (according to ChatGPT ;) )");
        }

        if (check) {
            System.out.println("Password is valid!");
        } else {
            System.out.println(validresult.message());
        }

        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Progamm ends'");
        return validresult;

    }

}
