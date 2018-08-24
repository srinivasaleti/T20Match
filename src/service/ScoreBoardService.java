package service;

import model.Player;
import model.Score;
import model.ScoreBoard;

public class ScoreBoardService {

    private static final int OFFSET = 1;
    private final ScoreBoard scoreBoard;

    public ScoreBoardService(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void update(Player player, Score score) {
        this.updateNumberOfBallsFacedByPlayer(player);
        this.updateCurrentScoreBoardStatus(player, score);
        if (score == Score.OUT) {
            player.goesToPavilions();
            this.updateWickets();
            return;
        }
        this.updateRunsOnScoreBoard(score);
        this.updatePlayerScore(player, score);
    }

    public boolean isMatchFinish() {
        return isScoredAllRuns() || isAllOut() || isAllOversDone();
    }

    public boolean isEndOfTheOver() {
        return this.scoreBoard.getNoOfBallsFaced() % 6 == 0;
    }

    public ScoreBoard scoreBoard() {
        return this.scoreBoard;
    }

    private void updateCurrentScoreBoardStatus(Player player, Score score) {
        int noOfBallsFaced = this.scoreBoard.getNoOfBallsFaced();
        this.scoreBoard.setCurrentBallStatus(score);
        this.scoreBoard.setCurrentPlayer(player);
        this.scoreBoard.setNoOfBallsFaced(noOfBallsFaced + OFFSET);
    }

    private void updatePlayerScore(Player player, Score score) {
        int playerScore = player.getScore();
        int runs = score.getValue();
        player.updateScore(playerScore + runs);
    }

    private void updateRunsOnScoreBoard(Score score) {
        int currentScore = this.scoreBoard.getCurrentScore();
        int runs = score.getValue();
        this.scoreBoard.setCurrentScore(currentScore + runs);
    }

    private void updateNumberOfBallsFacedByPlayer(Player player) {
        int noOfBallsFacedSoFar = player.ballsFaced();
        player.setBallsFaced(noOfBallsFacedSoFar + OFFSET);
    }

    private void updateWickets() {
        int noOfOuts = this.scoreBoard.getNoOfOuts();
        this.scoreBoard.setNoOfOuts(noOfOuts + OFFSET);
    }

    private boolean isScoredAllRuns() {
        return this.scoreBoard.getCurrentScore() >= this.scoreBoard.getRequiredScore();
    }

    private boolean isAllOut() {
        return this.scoreBoard.getNoOfOuts() == this.scoreBoard.getTotalWickets();
    }

    private boolean isAllOversDone() {
        int totalBallsFaced = this.scoreBoard.getNoOfBallsFaced();
        int totalBallsNeedToFace = this.scoreBoard.getTotalOvers() * 6;
        return totalBallsFaced == totalBallsNeedToFace;
    }

}
