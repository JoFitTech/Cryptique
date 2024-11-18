package com.jofittech.cryptique;

import org.apache.catalina.startup.Tool;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    // @SuppressWarnings("unused")
    public MainView() {

        // LAYOUT
        // Zentrieren Sie das Layout
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        // Setzt die Höhe des Layouts auf 100vh
        setHeight("100vh");

        // PASSWORT-VALIDIERUNG
        // Erstelle ein Passwortfeld für die Eingabe
        PasswordField passwordField = new PasswordField("Passwort eingeben");

        // Erstelle eine Instanz von ValidateButton und erhalte den Button
        ValidateButton validateButtonInstance = new ValidateButton(passwordField);
        Button validateButton = validateButtonInstance.createButton();

        TooltipManual tooltipManualInstance = new TooltipManual();
        Tooltip tooltipManual = tooltipManualInstance.createTooltipManual();

        // Füge die Komponenten zum Layout hinzu
        add(passwordField, validateButton, tooltipManual);f
    }
}
