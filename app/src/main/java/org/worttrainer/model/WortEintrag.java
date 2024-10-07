package org.worttrainer.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * Worteintrag model
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class WortEintrag {
    private String word;
    private URL url;

    public WortEintrag(String word, String url) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty!");
        }
        if (url == null || !this.checkUrl(url)) {
            throw new IllegalArgumentException("URL is invalid!");
        }
        try {
            this.word = word;
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL provided!", e);
        }
    }


    public boolean checkUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WortEintrag{" +
                "word='" + word + '\'' +
                ", url=" + url +
                '}';
    }
}
