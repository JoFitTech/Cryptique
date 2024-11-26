package com.jofittech;

//import org.springframework.boot.SpringApplication;
import com.cthiebaud.passwordvalidator.ValidationResult;
import com.jofittech.cryptique.Cryptique;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //SpringApplication.run(Main.class, args);
        System.out.println("Programm has started!");

        Cryptique validator = new Cryptique();
        ValidationResult result = validator.validate("QueenLautner261155!");
        System.out.println();
        System.out.println("Result: " + result.message());
    }
}
