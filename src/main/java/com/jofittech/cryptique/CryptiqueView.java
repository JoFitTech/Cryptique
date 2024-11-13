package com.jofittech.cryptique;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.cthiebaud.passwordvalidator.ValidationResult;

@Route("")
public class CryptiqueView extends VerticalLayout {

    public CryptiqueView() {
        // Erstelle ein Passwortfeld für die Eingabe
        PasswordField passwordField = new PasswordField("Passwort eingeben");

        // Schaltfläche zum Validieren des Passworts
        Button validateButton = new Button("Passwort validieren", event -> {
            String password = passwordField.getValue();

            // Erstelle eine Instanz von Cryptique und validiere das Passwort
            Cryptique validator = new Cryptique();
            ValidationResult result = validator.validate(password);

            // Zeige das Ergebnis als Benachrichtigung an
            if (result.isValid()) {
                Notification.show("Passwort ist gültig!", 3000, Notification.Position.MIDDLE);
            } else {
                Notification.show("Passwort ist ungültig: " + result.message(), 3000, Notification.Position.MIDDLE);
            }
        });

        // Füge die Komponenten zum Layout hinzu
        add(passwordField, validateButton);
    }
}
