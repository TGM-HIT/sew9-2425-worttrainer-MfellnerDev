package org.worttrainer.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class WortListe {
    private List<WortEintrag> wortListe;

    public WortListe (List<WortEintrag> wortListe)  {
        Objects.requireNonNull(wortListe, "Wortliste must not be null");

        this.wortListe = wortListe;
    }

    public void addWortEintrag(WortEintrag wortEintrag) {
        wortListe.add(wortEintrag);
    }

    public WortEintrag getWortEintrag(int index)  {
        return wortListe.get(index);
    }

    public List<WortEintrag> getWortListe() {
        return wortListe;
    }

    public void deleteWortEintrag(int index)  {
        wortListe.remove(index);
    }
}
