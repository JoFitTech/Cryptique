package com.jofittech.cryptique;

import org.springframework.stereotype.Service;

@Service
public class CrosswordPuzzleService {
    private CrosswordPuzzle crosswordPuzzle;

    // @param Größe des Kreuzworträtsels
    public void initializePuzzle(int size) {
        crosswordPuzzle = new CrosswordPuzzle(size);
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
    public void addWord(String word, int row, int column, boolean isAcross) {
        crosswordPuzzle.setWord(word, row, column, isAcross);
    }

    // Gibt das Kreuzworträtsel zurück
    // @return Das Kreuzworträtsel
    public char[][] getPuzzle() {
        return crosswordPuzzle.getPuzzle();
    }
}