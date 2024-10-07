import org.junit.jupiter.api.Test;
import org.worttrainer.model.WortEintrag;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class WortTrainerTest {

    @Test
    public void testValidWortEintrag() throws MalformedURLException {
        WortEintrag wortEintrag = new WortEintrag("Katze", "https://example.com/katze.jpg");
        assertEquals("Katze", wortEintrag.getWord());
        assertEquals(new URL("https://example.com/katze.jpg"), wortEintrag.getUrl());
    }

    @Test
    public void testEmptyWordThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag("", "https://example.com/tiger.jpg");
        });
        assertEquals("Word cannot be null or empty!", exception.getMessage());
    }

    @Test
    public void testInvalidUrlThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag("Tiger", "invalid-url");
        });
        assertEquals("URL is invalid!", exception.getMessage());
    }

    @Test
    public void testSetWord() {
        WortEintrag wortEintrag = new WortEintrag("Hund", "https://example.com/hund.jpg");
        wortEintrag.setWord("Löwe");
        assertEquals("Löwe", wortEintrag.getWord());
    }

    @Test
    public void testSetUrl() throws MalformedURLException {
        WortEintrag wortEintrag = new WortEintrag("Hund", "https://example.com/hund.jpg");
        wortEintrag.setUrl(new URL("https://example.com/loewe.jpg"));
        assertEquals(new URL("https://example.com/loewe.jpg"), wortEintrag.getUrl());
    }

    @Test
    public void testCheckValidUrl() {
        WortEintrag wortEintrag = new WortEintrag("Elefant", "https://example.com/elefant.jpg");
        assertTrue(wortEintrag.checkUrl("https://example.com/elefant.jpg"));
    }

    @Test
    public void testCheckInvalidUrl() {
        WortEintrag wortEintrag = new WortEintrag("Elefant", "https://example.com/elefant.jpg");
        assertFalse(wortEintrag.checkUrl("invalid-url"));
    }
}
