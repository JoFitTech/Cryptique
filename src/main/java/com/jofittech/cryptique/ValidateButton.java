package com.jofittech.cryptique;

import com.cthiebaud.passwordvalidator.ValidationResult;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;

public class ValidateButton {

    private PasswordField passwordField; // Referenz auf das Passwortfeld

    public ValidateButton(PasswordField passwordField) {
        this.passwordField = passwordField; // Initialisiere das Passwortfeld
    }

    @SuppressWarnings("unused")
    public Button createButton() {
        Button validateButton = new Button("Passwort validieren", event -> {
            String password = passwordField.getValue();

            // Erstelle eine Instanz von Cryptique und validiere das Passwort
            Cryptique validator = new Cryptique();
            ValidationResult result = validator.validate(password);

            Notification.show(result.isValid() ? "Passwort ist gültig!" : "Passwort ist ungültig!", 3000,
                    Notification.Position.BOTTOM_CENTER);
        });

        passwordField.addKeyDownListener(Key.ENTER, event -> validateButton.click());
        return validateButton;
    }
}
