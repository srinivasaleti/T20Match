package service;

import model.ScoreBoard;

public class ScoreBoardService {

    private static final int OFFSET = 1;
    private ScoreBoard scoreBoard;

    public ScoreBoardService(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void addScore(int runs) {
        int currentScore = this.scoreBoard.getCurrentScore();
        this.scoreBoard.setCurrentScore(currentScore + runs);
    }

    public int getCurrentScore() {
        return this.scoreBoard.getCurrentScore();
    }

    public void incrementNoOfOuts() {
        this.scoreBoard.setNoOfOuts(this.scoreBoard.getNoOfOuts() + OFFSET);
    }

    public int getNumberOfOuts() {
        return this.scoreBoard.getNoOfOuts();
    }
}
