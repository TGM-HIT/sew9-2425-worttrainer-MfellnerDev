package org.worttrainer.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
/**
 *
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class WortEintrag {
    private String word;
    private URL url;
    private URL pictureUrl;

    public WortEintrag(String word, String url, String pictureUrl) throws MalformedURLException {
        if (word == null || word.isEmpty() && url == null || !this.checkUrl(url) || pictureUrl == null || !this.checkUrl(pictureUrl)) {
            throw new IllegalArgumentException("Word or URL Invalid/Empty!");
        }
        this.word = word;
        this.url = new URL(url);
        this.pictureUrl = new URL(pictureUrl);
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

    public URL getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(URL pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "WortEintrag{" +
                "word='" + word + '\'' +
                ", url=" + url +
                ", pictureUrl=" + pictureUrl +
                '}';
    }
}
