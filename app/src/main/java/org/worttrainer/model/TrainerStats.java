package org.worttrainer.model;

/**
 * Statistics for the Worttrainer game
 *
 * @author Manuel Fellner
 * @version 07.10.2024
 */
public class TrainerStats {
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private int totalASkedQuestions = 0;

    public void increaseCorrectAnswers() {
        correctAnswers++;
        totalASkedQuestions++;
    }

    public void increaseWrongAnswers() {
        wrongAnswers++;
        totalASkedQuestions++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public int getTotalASkedQuestions() {
        return totalASkedQuestions;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void setTotalASkedQuestions(int totalASkedQuestions) {
        this.totalASkedQuestions = totalASkedQuestions;
    }

    @Override
    public String toString() {
        return "TrainerStats{" +
                "correctAnswers=" + correctAnswers +
                ", wrongAnswers=" + wrongAnswers +
                ", totalASkedQuestions=" + totalASkedQuestions +
                '}';
    }
}
