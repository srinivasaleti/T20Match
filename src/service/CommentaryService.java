package service;

import model.ScoreBoard;

import java.io.PrintStream;

public class CommentaryService {

    private static String BALL_FORMAT = "%d.%d %s scores %d run\n";

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

        outputStream.printf(BALL_FORMAT, noOfOversDone, numberOfBallsFacedInCurrentOver, playerName, currentBallScore);
    }

    private String playerName(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentPlayer().getName();
    }

}