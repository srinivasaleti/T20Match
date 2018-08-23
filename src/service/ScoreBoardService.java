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
        int noOfBallsFaced = this.scoreBoard.getNoOfBallsFaced();
        this.scoreBoard.setNoOfBallsFaced(noOfBallsFaced + OFFSET);
        if (score == Score.OUT) {
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
        return isScoredAllRuns() || isAllOut() || isAllOversDone();
    }

    private boolean isScoredAllRuns() {
        return this.getCurrentScore() >= this.requiredScore;
    }

    private boolean isAllOut() {
        return this.getNumberOfOuts() == teamSize - 1;
    }

    private boolean isAllOversDone() {
        int totalBallsFaced = this.scoreBoard.getNoOfBallsFaced();
        int totalBallsNeedToFace = this.scoreBoard.getTotalOvers() * 6;
        return totalBallsFaced == totalBallsNeedToFace;
    }

}
