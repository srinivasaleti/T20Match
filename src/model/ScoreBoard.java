package model;

public class ScoreBoard {
    private int currentScore;
    private int noOfOuts;

    public ScoreBoard() {
        this.currentScore = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getNoOfOuts() {
        return this.noOfOuts;
    }

    public void setNoOfOuts(int noOfOuts) {
        this.noOfOuts = noOfOuts;
    }
}
