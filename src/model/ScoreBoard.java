package model;

public class ScoreBoard {
    private int currentScore;
    private int noOfOuts;
    private int noOfBallsFaced;
    private int totalOvers;
    private int requiredScore;
    private int totalWickets;
    private Player currentPlayer;
    private Score currentBallStatus;

    public ScoreBoard() {
        this.currentScore = 0;
        this.noOfBallsFaced = 0;
        this.requiredScore = 0;
        this.totalWickets = 0;
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

    public int getNoOfBallsFaced() {
        return noOfBallsFaced;
    }

    public void setNoOfBallsFaced(int noOfBallsFaced) {
        this.noOfBallsFaced = noOfBallsFaced;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    public int getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Score getCurrentBallStatus() {
        return currentBallStatus;
    }

    public void setCurrentBallStatus(Score currentBallStatus) {
        this.currentBallStatus = currentBallStatus;
    }

}
