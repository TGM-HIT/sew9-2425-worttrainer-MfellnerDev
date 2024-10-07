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
 * @version 07.10.2024
 */
public class FileHandler {

    // path to the save file, only in the project itself
    private static final String FILE_PATH = "/src/main/resources/wortEintraege.json";

    /**
     * Save the trainer data to a JSON File
     * @param trainer trainer instance
     * @throws IOException
     */
    public static void saveTrainer(WortTrainer trainer) throws IOException {
        JSONObject trainerObj = new JSONObject();

        // Save WortEinträge
        JSONArray wortEintraegeArray = new JSONArray();
        List<WortEintrag> wortListe = trainer.getWortListe();
        for (WortEintrag wortEintrag : wortListe) {
            JSONObject wortEintragObj = new JSONObject();
            wortEintragObj.put("word", wortEintrag.getWord());
            wortEintragObj.put("url", wortEintrag.getUrl());
            wortEintraegeArray.put(wortEintragObj);
        }
        trainerObj.put("wortEintraege", wortEintraegeArray);

        // save the statistics
        JSONObject statsObj = new JSONObject();
        statsObj.put("Incorrect answers", trainer.getTrainerStats().getWrongAnswers());
        statsObj.put("Correct answers", trainer.getTrainerStats().getCorrectAnswers());
        statsObj.put("Total questions asked", trainer.getTrainerStats().getTotalASkedQuestions());
        trainerObj.put("statistics", statsObj);

        // write to file
        try (FileWriter file = new FileWriter(new java.io.File(".").getCanonicalPath() + FILE_PATH)) {
            file.write(trainerObj.toString(4));
        } catch (IOException e) {
            System.out.println("Cannot save game! Some error occured: " + e.getMessage());
        }
    }

    /**
     * Load the trainer data from JSON
     * @param trainer trainer instance to save the data
     * @throws IOException
     */
    public static void loadTrainer(WortTrainer trainer) throws IOException {
        String jsonString = readFileAsString(new java.io.File(".").getCanonicalPath() + FILE_PATH);

        JSONObject jsonObject = new JSONObject(jsonString);

        // load WortEinträge
        JSONArray wortEintraegeArray = jsonObject.getJSONArray("wortEintraege");
        List<WortEintrag> wortEintragList = new ArrayList<>();
        for (int i = 0; i < wortEintraegeArray.length(); i++) {
            JSONObject wortEintragObj = wortEintraegeArray.getJSONObject(i);
            String word = wortEintragObj.getString("word");
            String url = wortEintragObj.getString("url");

            WortEintrag wortEintrag = new WortEintrag(word, url);
            wortEintragList.add(wortEintrag);
        }
        trainer.setWortListe(wortEintragList);

        // load statistics
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
    private static String readFileAsString(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("Datei konnte nicht geladen werden: " + filePath + ", " + e.getMessage());
        }
        return "";
    }
}
