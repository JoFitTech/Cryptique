package com.jofittech;

import java.io.Console;

import com.cthiebaud.passwordvalidator.ValidationResult;
import com.jofittech.cryptique.Cryptique;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //SpringApplication.run(Main.class, args);
        System.out.println("Programm has started! \n");

        Console console = System.console();
        char[] password = console.readPassword("Enter your password: ");
        String passwordString = new String(password);

        Cryptique validator = new Cryptique();
        ValidationResult result = validator.validate(passwordString);
        System.out.println("\nResult: " + result.message());
    }
}
