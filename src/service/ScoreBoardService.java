package service;

import model.Player;
import model.Score;
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


    public void updateScore(Player player, Score score) {
        if(score == Score.OUT) {
            player.goesToPavilions();
            int noOfOuts = this.scoreBoard.getNoOfOuts();
            this.scoreBoard.setNoOfOuts(noOfOuts + OFFSET);
            return;
        }
        int runs = score.getValue();
        int currentScore = this.scoreBoard.getCurrentScore();
        this.scoreBoard.setCurrentScore(currentScore + runs);
        int currentPlayerScore = player.getScore();
        player.updateScore(currentPlayerScore + runs);
    }

    public int getCurrentScore() {
        return this.scoreBoard.getCurrentScore();
    }

    public int getNumberOfOuts() {
        return this.scoreBoard.getNoOfOuts();
    }

    public boolean isMatchFinish() {
         return (this.getCurrentScore() >= this.requiredScore) || this.getNumberOfOuts() == teamSize;
    }

}
