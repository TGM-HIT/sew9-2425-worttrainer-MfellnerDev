package org.worttrainer.controller;

import org.worttrainer.model.FileHandler;
import org.worttrainer.model.WortEintrag;
import org.worttrainer.model.WortListe;
import org.worttrainer.model.WortTrainer;

import java.io.IOException;
import java.util.List;

public class WortTrainerController {

    public static void main(String[] args) {
        try {

            // Erstelle eine Instanz des WortTrainers
            WortTrainer wortTrainer = new WortTrainer();
            // Lade die WortEinträge aus der JSON-Datei
            FileHandler.loadTrainer(wortTrainer);

            // Starte die GUI (start-Methode im WortTrainer)
            wortTrainer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden der JSON-Datei.");
        }
    }
}
