package org.worttrainer.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

import static java.lang.System.exit;

public class WortTrainer {

    private List<WortEintrag> wortListe;
    private TrainerStats trainerStats;

    public WortTrainer(List<WortEintrag> wortListe) {
        this.wortListe = wortListe;
        this.trainerStats = new TrainerStats();
    }

    public WortTrainer() {}

    public void start() {
        for (WortEintrag eintrag : wortListe) {
            // Zeige das Bild an
            ImageIcon imageIcon = loadImageFromURL(eintrag.getPictureUrl());
            JLabel imageLabel = new JLabel(imageIcon);

            // Eingabefeld für die Antwort
            JTextField inputField = new JTextField(10);

            // Panel für die Anzeige
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(imageLabel);
            panel.add(inputField);

            // Zeige das Panel in einem JOptionPane
            int option = JOptionPane.showConfirmDialog(null, panel, "Welches Tier ist das?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String userInput = inputField.getText().trim();
                if (checkAnswer(userInput, eintrag.getWord())) {
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    trainerStats.increaseCorrectAnswers();
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Die richtige Antwort war: " + eintrag.getWord());
                    trainerStats.increaseWrongAnswers();
                }

                // Statistiken aktualisieren
                updateStats();
            }

            int saveOption = JOptionPane.showConfirmDialog(null, "Möchten Sie den aktuellen Fortschritt speichern?", "Speichern", JOptionPane.YES_NO_OPTION);
            if (saveOption == JOptionPane.YES_OPTION) {
                // Fortschritt speichern
                saveProgress();
            }
        }
    }

    private ImageIcon loadImageFromURL(URL imageUrl) {
        try {
            Image image = new ImageIcon(imageUrl).getImage();
            Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Skaliere das Bild
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(); // Leeres Bild im Fehlerfall
        }
    }

    private boolean checkAnswer(String userInput, String correctAnswer) {
        return userInput.equalsIgnoreCase(correctAnswer);
    }

    private void updateStats() {
        String statsMessage = "Statistiken:\n" +
                "Richtige Antworten: " + trainerStats.getCorrectAnswers() + "\n" +
                "Falsche Antworten: " + trainerStats.getWrongAnswers() + "\n" +
                "Insgesamt gestellte Fragen: " + trainerStats.getTotalASkedQuestions();
        JOptionPane.showMessageDialog(null, statsMessage, "Statistiken", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveProgress() {
        // Verwende FileHandler, um den aktuellen Fortschritt zu speichern
        try {
            FileHandler.saveTrainer(this);
            JOptionPane.showMessageDialog(null, "Fortschritt erfolgreich gespeichert.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Speichern fehlgeschlagen.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<WortEintrag> getWortListe() {
        return wortListe;
    }

    public TrainerStats getTrainerStats() {
        return trainerStats;
    }

    public void setWortListe(List<WortEintrag> wortListe) {
        this.wortListe = wortListe;
    }

    public void setTrainerStats(TrainerStats trainerStats) {
        this.trainerStats = trainerStats;
    }
}
