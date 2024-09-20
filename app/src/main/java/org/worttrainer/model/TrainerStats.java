package org.worttrainer.model;

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

    @Override
    public String toString() {
        return "TrainerStats{" +
                "correctAnswers=" + correctAnswers +
                ", wrongAnswers=" + wrongAnswers +
                ", totalASkedQuestions=" + totalASkedQuestions +
                '}';
    }
}
