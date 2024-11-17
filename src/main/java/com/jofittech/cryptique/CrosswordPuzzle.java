package com.jofittech.cryptique;

import java.util.Arrays;

// Diese Klasse stellt ein Kreuzworträtsel dar
public class CrosswordPuzzle {
    private char[][] puzzle;

    // @param size Die Größe des Kreuzworträtsels
    public CrosswordPuzzle(int size) {
        this.puzzle = new char[size][size];
        for (char[] row : puzzle) {
            Arrays.fill(row, ' ');
        }
    }

    // Gibt das Kreuzworträtsel zurück
    public char[][] getPuzzle() {
        return puzzle;
    }

    /**
     * Setzt ein Wort im Kreuzworträtsel an die angegebene Position.
     *
     * @param word     Das zu platzierende Wort.
     * @param row      Startreihe des Wortes.
     * @param column   Startspalte des Wortes.
     * @param isAcross True, wenn das Wort horizontal platziert werden soll; False
     *                 für vertikal.
     * @throws IllegalArgumentException Wenn das Wort nicht passt oder Konflikte
     *                                  entstehen.
     */
    public void setWord(String word, int row, int column, boolean isAcross) {
        if (isAcross) {
            if (column + word.length() > puzzle[0].length) {
                throw new IllegalArgumentException("Das Wort passt nicht");
            }

            for (int i = 0; i < word.length(); i++) {
                if (puzzle[row][column + i] != ' ' && puzzle[row][column + i] != word.charAt(i)) {
                    throw new IllegalArgumentException("Das Wort kollidiert mit bestehenden Buchstaben.");
                }
                puzzle[row][column + i] = word.charAt(i);
            }
        } else {
            if (row + word.length() > puzzle.length) {
                throw new IllegalArgumentException("Das Wort passt nicht an die angegebene Position.");
            }

            for (int i = 0; i < word.length(); i++) {
                if (puzzle[row + i][column] != ' ' && puzzle[row + i][column] != word.charAt(i)) {
                    throw new IllegalArgumentException("Das Wort kollidiert mit bestehenden Buchstaben.");
                }
                puzzle[row + i][column] = word.charAt(i);
            }
        }
    }

    // @return Eine Zeichenkette, die das Kreuzworträtsel darstellt
    public void printPuzzle() {
        for (char[] row : puzzle) {
            System.out.println(Arrays.toString(row));
        }
    }
}