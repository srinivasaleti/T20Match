package service;

import model.ScoreBoard;

public class ScoreBoardService {

    private static final int OFFSET = 1;
    private final ScoreBoard scoreBoard;
    private final int requiredScore;
    private final int teamSize;

    public ScoreBoardService(ScoreBoard scoreBoard, int requiredScore, int teamSize) {
        this.scoreBoard = scoreBoard;
        this.requiredScore = requiredScore;
        this.teamSize = teamSize;
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

    public boolean isMatchFinish() {
         return (this.getCurrentScore() >= this.requiredScore) || this.getNumberOfOuts() == teamSize;
    }

}
