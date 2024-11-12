package com.jofittech;

import com.jofittech.cryptique.Cryptique;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        //creating new object of the implementation class which validates the password
        Cryptique validator = new Cryptique();

        System.out.println();
        // System.out.println("Hello World!");
        // System.out.println();

        System.out.println();
        // Aufruf der validate-Methode und Speichern des Rückgabewerts
        validator.validate("queen");
        System.out.println();
    }
}
