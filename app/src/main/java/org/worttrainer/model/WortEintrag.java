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
        if (word == null || word.isEmpty() && url == null || !this.checkUrl(url)) {
            throw new IllegalArgumentException("Word or URL Invalid/Empty!");
        }
        try  {
            this.word = word;
            this.url = new URL(url);
        } catch (MalformedURLException e)  {
            System.out.println("Error! Could not parse the URL");
        }
    }

    public boolean checkUrl(String url)  {
        try  {
            new URI(url);
            return true;
        }  catch (Exception e) {
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
