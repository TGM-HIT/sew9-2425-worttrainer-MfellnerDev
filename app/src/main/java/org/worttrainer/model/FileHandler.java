package org.worttrainer.model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class FileHandler {
    public static void saveTrainer(WortTrainer trainer, String filePath) throws IOException {
        JSONObject json = new JSONObject();
        JSONArray pairsArray = new JSONArray();

        for (WortEintrag pair : trainer.getWortListe()) {
            JSONObject pairJson = new JSONObject();
            pairJson.put("word", pair.getWord());
            pairJson.put("imageUrl", pair.getUrl().toString());
            pairsArray.put(pairJson);
        }

        json.put("wordPairs", pairsArray);
        json.put("correctGuesses", trainer.getCorrectGuesses());
        json.put("incorrectGuesses", trainer.getIncorrectGuesses());

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(json.toString());
        }
    }

    public static WortTrainer loadTrainer(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject json = new JSONObject(content);

        WortTrainer trainer = new WortTrainer();
        JSONArray pairsArray = json.getJSONArray("wordPairs");

        for (int i = 0; i < pairsArray.length(); i++) {
            JSONObject pairJson = pairsArray.getJSONObject(i);
            trainer.addWortEintrag(new WortEintrag(pairJson.getString("word"), pairJson.getString("imageUrl")));
        }

        trainer.setCorrectGuesses(json.getInt("correctGuesses"));
        trainer.setIncorrectGuesses(json.getInt("incorrectGuesses"));

        return trainer;
    }
}
