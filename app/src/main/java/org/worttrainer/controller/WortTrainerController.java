package org.worttrainer.controller;

import org.worttrainer.model.FileHandler;
import org.worttrainer.model.WortTrainer;

import java.io.IOException;

/**
 * Worttrainer Controller - Controller class for the Worttrainer game
 *
 * @author Manuel Fellner
 * @version 07.10.2024
 */
public class WortTrainerController {

    public static void main(String[] args) {
        try {

            WortTrainer wortTrainer = new WortTrainer();

            // load the previous data
            FileHandler.loadTrainer(wortTrainer);

            // start the worttrainer game
            wortTrainer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden der JSON-Datei.");
        }
    }
}
