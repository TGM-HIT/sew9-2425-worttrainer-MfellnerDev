package org.worttrainer.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class FileHandler {

    // Pfad zur Datei
    private static final String FILE_PATH = "./app/src/main/resources/wortEintraege.json";

    // Trainer-Daten speichern (Wortliste und Statistik)
    public static void saveTrainer(WortTrainer trainer) throws IOException {
        // JSON-Objekt für die gesamte Struktur
        JSONObject trainerObj = new JSONObject();

        // WortEinträge speichern
        JSONArray wortEintraegeArray = new JSONArray();
        List<WortEintrag> wortListe = trainer.getWortListe();
        for (WortEintrag wortEintrag : wortListe) {
            JSONObject wortEintragObj = new JSONObject();
            wortEintragObj.put("word", wortEintrag.getWord());
            wortEintragObj.put("url", wortEintrag.getUrl());
            wortEintragObj.put("pictureUrl", wortEintrag.getPictureUrl());
            wortEintraegeArray.put(wortEintragObj);
        }
        trainerObj.put("wortEintraege", wortEintraegeArray);

        // Statistik speichern (ohne Array, direktes Objekt)
        JSONObject statsObj = new JSONObject();
        statsObj.put("Incorrect answers", trainer.getTrainerStats().getWrongAnswers());
        statsObj.put("Correct answers", trainer.getTrainerStats().getCorrectAnswers());
        statsObj.put("Total questions asked", trainer.getTrainerStats().getTotalASkedQuestions());
        trainerObj.put("statistics", statsObj);

        // In Datei schreiben
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(trainerObj.toString(4));  // Formatierter JSON-Output
            System.out.println("Worttrainer erfolgreich gespeichert!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Trainer-Daten laden (Wortliste und Statistik)
    public static void loadTrainer(WortTrainer trainer) throws IOException {
        // Dateiinhalt als String lesen
        String jsonString = readFileAsString(FILE_PATH);

        // JSON-Objekt aus dem String parsen
        JSONObject jsonObject = new JSONObject(jsonString);

        // WortEinträge laden
        JSONArray wortEintraegeArray = jsonObject.getJSONArray("wortEintraege");
        List<WortEintrag> wortEintragList = new ArrayList<>();
        for (int i = 0; i < wortEintraegeArray.length(); i++) {
            JSONObject wortEintragObj = wortEintraegeArray.getJSONObject(i);
            String word = wortEintragObj.getString("word");
            String url = wortEintragObj.getString("url");
            String pictureUrl = wortEintragObj.getString("pictureUrl");

            WortEintrag wortEintrag = new WortEintrag(word, url, pictureUrl);
            wortEintragList.add(wortEintrag);
        }
        trainer.setWortListe(wortEintragList);  // Geladene Wortliste dem Trainer zuweisen

        // Statistik laden
        JSONObject statsObj = jsonObject.getJSONObject("statistics");
        int correctAnswers = statsObj.getInt("Correct answers");
        int wrongAnswers = statsObj.getInt("Incorrect answers");
        int totalQuestions = statsObj.getInt("Total questions asked");

        TrainerStats stats = new TrainerStats();
        stats.setCorrectAnswers(correctAnswers);
        stats.setWrongAnswers(wrongAnswers);
        stats.setTotalASkedQuestions(totalQuestions);

        trainer.setTrainerStats(stats);
    }

    // Hilfsmethode, um eine Datei als String zu lesen
    private static String readFileAsString(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("Datei konnte nicht geladen werden: " + filePath);
            e.printStackTrace();
        }
        return "";
    }
}
