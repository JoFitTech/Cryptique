package com.jofittech.cryptique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crossword")
public class CrosswordPuzzleController {

    @Autowired
    private CrosswordPuzzleService crosswordPuzzleService;

    // @param Die Größe des Kreuzworträtsels
    @PostMapping("/initialize")
    public void initializePuzzle(@RequestParam int size) {
        crosswordPuzzleService.initializePuzzle(size);
    }

    /**
     * Fügt ein Wort ins Kreuzworträtsel ein.
     *
     * @param word     Das zu platzierende Wort.
     * @param row      Die Startreihe des Wortes.
     * @param column   Die Startspalte des Wortes.
     * @param isAcross True für horizontal, False für vertikal.
     */
    @PostMapping("/addWord")
    public void addWord(@RequestParam String word, @RequestParam int row,
            @RequestParam int column, @RequestParam boolean isAcross) {
        crosswordPuzzleService.addWord(word, row, column, isAcross);
    }

    // @return Gibt das Kreuzworträtsel als 2D zurück
    @GetMapping("/puzzle")
    public char[][] getPuzzle() {
        return crosswordPuzzleService.getPuzzle();
    }
}
