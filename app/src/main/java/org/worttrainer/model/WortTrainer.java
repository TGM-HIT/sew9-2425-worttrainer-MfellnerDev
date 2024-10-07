package org.worttrainer.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * Worttrainer game - Guess the word with a simple picture
 *
 * @author Manuel Fellner
 * @version 07.10.2024
 */
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
            ImageIcon imageIcon = loadImageFromURL(eintrag.getUrl());
            JLabel imageLabel = new JLabel(imageIcon);

            // Eingabefeld für die Antwort
            JTextField inputField = new JTextField(10);

            // Panel für die Anzeige
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(imageLabel);
            panel.add(inputField);

            // show the question with the picture
            int option = JOptionPane.showConfirmDialog(null, panel, "Welches Tier ist das?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String userInput = inputField.getText().trim();
                // validate user input
                if (checkAnswer(userInput, eintrag.getWord())) {
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    trainerStats.increaseCorrectAnswers();
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Die richtige Antwort war: " + eintrag.getWord());
                    trainerStats.increaseWrongAnswers();
                }
                // save the statistics
                updateStats();
            }
            // save the progress
            saveProgress();
        }
    }

    private ImageIcon loadImageFromURL(URL imageUrl) {
        try {
            Image image = new ImageIcon(imageUrl).getImage();
            Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.out.println("Error when trying to create a picture from the image url: " + e.getMessage());
            return new ImageIcon();
        }
    }

    private boolean checkAnswer(String userInput, String correctAnswer) {
        return userInput.equalsIgnoreCase(correctAnswer);
    }

    private void updateStats() {
        String statsMessage = "Statistiken:\n" +
                "Richtige Antworten: " + trainerStats.getCorrectAnswers() + "\n" +
                "Falsche Antworten: " + trainerStats.getWrongAnswers() + "\n" +
                "Insgesamt gestellte Fragen: " + trainerStats.getTotalAskedQuestions();
        JOptionPane.showMessageDialog(null, statsMessage, "Statistiken", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveProgress() {
        try {
            FileHandler.saveTrainer(this);
        } catch (Exception e) {
            System.out.println("Error when trying to save players progress: " + e.getMessage());
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
