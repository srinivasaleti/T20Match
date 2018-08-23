package service;

import model.ScoreBoard;

import java.io.PrintStream;

import static model.Score.OUT;

public class CommentaryService {

    private static String BALL_FORMAT = "%d.%d %s scores %d run\n";
    private static String OUT_FORMAT = "%d.%d %s is out\n";
    private static String END_OF_OVER_FORMAT = "%d overs left. %d runs to win\n";

    private PrintStream outputStream;

    public CommentaryService(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void announce(ScoreBoard scoreBoard) {
        String playerName = playerName(scoreBoard);
        int noOfBallsFaced = scoreBoard.getNoOfBallsFaced();
        int noOfOversDone = noOfBallsFaced / 6;
        int numberOfBallsFacedInCurrentOver = noOfBallsFaced % 6;
        int currentBallScore = scoreBoard.getCurrentBallStatus().getValue();

        if (scoreBoard.getCurrentBallStatus() == OUT) {
            outputStream.printf(OUT_FORMAT, noOfOversDone, numberOfBallsFacedInCurrentOver, playerName);
        } else {
            outputStream.printf(BALL_FORMAT, noOfOversDone, numberOfBallsFacedInCurrentOver, playerName, currentBallScore);
        }
        if (noOfBallsFaced % 6 == 0) {
            outputStream.println();
            int remainingOvers = scoreBoard.getTotalOvers() - (noOfBallsFaced / 6);
            int remainingRuns = scoreBoard.getRequiredScore() - scoreBoard.getCurrentScore();
            outputStream.printf(END_OF_OVER_FORMAT, remainingOvers, remainingRuns);
        }
    }

    private String playerName(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentPlayer().getName();
    }

    public void announceResults(ScoreBoard scoreBoard) {
        int currentScore = scoreBoard.getCurrentScore();
        int requiredScore = scoreBoard.getRequiredScore();
        int noOfOuts = scoreBoard.getNoOfOuts();
        int totalWickets = scoreBoard.getTotalWickets();
        if (currentScore >= requiredScore) {
            outputStream.println("Won By " + (totalWickets - noOfOuts + 1) + " Wickets");
            return;
        }
        if (currentScore == requiredScore - 1) {
            outputStream.println("Match Draw");
            return;
        }
        outputStream.println("Loose By " + (requiredScore - currentScore) + " Runs");
    }
}