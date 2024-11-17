package com.jofittech.cryptique;

import com.cthiebaud.passwordvalidator.ValidationResult;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Route("")
public class MainView extends VerticalLayout {

    private final RestTemplate restTemplate = new RestTemplate();
    private Grid<String[]> puzzleGrid;
    private int puzzleSize;

    @SuppressWarnings("unused")
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

        // Schaltfläche zum Validieren des Passworts
        Button validateButton = new Button("Passwort validieren", event -> {
            String password = passwordField.getValue();

            // Erstelle eine Instanz von Cryptique und validiere das Passwort
            Cryptique validator = new Cryptique();
            ValidationResult result = validator.validate(password);

            Notification.show(result.isValid() ? "Passwort ist gültig!" : "Passwort ist ungültig!", 3000,
                    Notification.Position.BOTTOM_CENTER);

            // Zeige das Ergebnis als Benachrichtigung an
            // if (result.isValid()) {
            // Notification.show("Passwort ist gültig!", 3000,
            // Notification.Position.BOTTOM_CENTER);
            // } else {
            // Notification.show("Passwort ist ungültig!", 3000,
            // Notification.Position.BOTTOM_CENTER);
            // }
        });

        // Füge einen KeyListener zum Passwortfeld hinzu
        passwordField.addKeyDownListener(Key.ENTER, event -> validateButton.click());

        // Puzzle-Größe Eingabefeld
        IntegerField sizeField = new IntegerField("Puzzle-Größe");
        sizeField.setMin(5);
        sizeField.setMax(20);
        sizeField.setValue(5);

        Button initializeButton = new Button("Puzzle erzeugen", event -> initializePuzzle(sizeField.getValue()));

        // Eingabefelder für das Wort udn seine Position
        TextField wordField = new TextField("Wort");
        IntegerField rowField = new IntegerField("Zeile");
        IntegerField columnField = new IntegerField("Spalte");
        rowField.setMin(0);
        columnField.setMin(0);

        Button addButton = new Button("Wort hinzufügen", e -> addWord(
                wordField.getValue(), rowField.getValue(), columnField.getValue(), true));

        // Puzzle Grid
        puzzleGrid = new Grid<String[]>();
        puzzleGrid.addColumn(row -> Arrays.toString(row)).setHeader("Puzzle");
        puzzleGrid.setHeight("300px");

        // Füge die Komponenten zum Layout hinzu
        add(passwordField, validateButton, sizeField, initializeButton,
                wordField, rowField, columnField, addButton, puzzleGrid);
    }

    private void initializePuzzle(int size) {
        this.puzzleSize = size;
        String url = "http://localhost:8080/api/crossword/initialize?size=" + size;
        restTemplate.postForObject(url, null, Void.class);
        loadPuzzle();
    }

    private void addWord(String word, int row, int column, boolean isAcross) {
        String url = String.format("http://localhost:8080/api/crossword/addWord?word=%s&row=%d&column=%d&isAcross=%b",
                word, row, column, isAcross);

        try {
            restTemplate.postForObject(url, null, Void.class);
            loadPuzzle();
        } catch (Exception e) {
            Notification.show("Fehler beim Hinzufügen des Wortes: " + e.getMessage());
        }
    }

    private void loadPuzzle() {
        String url = "http://localhost:8080/api/crossword/puzzle";
        char[][] puzzle = restTemplate.getForObject(url, char[][].class);
        if (puzzle != null) {
            updateGrid(puzzle);
        }
    }

    /*
     * private void updateGrid(char[][] puzzle) {
     * puzzleGrid.setItems(
     * Arrays.stream(puzzle)
     * .flatMap(row -> Arrays.stream(row) // Flacher Stream aller Zeichen
     * .mapToObj(String::valueOf)) // char -> String
     * .toList() // List<String>
     * );
     * }
     */
}
