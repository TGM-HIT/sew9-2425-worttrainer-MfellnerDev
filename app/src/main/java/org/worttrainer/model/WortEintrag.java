package org.worttrainer.model;

import java.net.URI;
import java.net.URL;
import java.util.Objects;
/**
 *
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class WortEintrag {
    String word;
    URL url;

    public WortEintrag(String word, String url) {
        if (word == null || word.isEmpty() && url == null || !this.checkUrl(url)) {
            throw new IllegalArgumentException("Word or URL Invalid/Empty!");
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
                ", url='" + url + '\'' +
                '}';
    }
}
