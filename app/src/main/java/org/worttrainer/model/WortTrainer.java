package org.worttrainer.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Fellner
 * @version 20.09.2024
 */
public class WortTrainer {
    public List<WortEintrag> wortListe;
    public WortEintrag currentWortEintrag;
    private TrainerStats trainerStats = new TrainerStats();

    public void initialization()  {
        try {
            wortListe = FileHandler.loadWorteintraege();
        } catch (Exception e)  {
            this.wortListe = new ArrayList<WortEintrag>();
            this.wortListe.add(new WortEintrag("Hund", "https://de.wikipedia.org/wiki/Hunde"));
        }

        this.currentWortEintrag = this.wortListe.getFirst();
    }

    public List<WortEintrag> getWortListe() {
        return wortListe;
    }

    public void setWortListe(List<WortEintrag> wortListe) {
        this.wortListe = wortListe;
    }

    public WortEintrag getCurrentWortEintrag() {
        return currentWortEintrag;
    }

    public void setCurrentWortEintrag(WortEintrag currentWortEintrag) {
        this.currentWortEintrag = currentWortEintrag;
    }

    public TrainerStats getTrainerStats() {
        return trainerStats;
    }

    public void setTrainerStats(TrainerStats trainerStats) {
        this.trainerStats = trainerStats;
    }
}
