package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        this.scoreBoard = new ScoreBoard();
    }

    @Test
    void shouldReturnZeroAsInitialScore() {
        assertEquals(0, this.scoreBoard.getCurrentScore());
    }

    @Test
    void shouldSetCurrentScoreToFiveRuns() {
        this.scoreBoard.setCurrentScore(5);

        assertEquals(this.scoreBoard.getCurrentScore(), 5);
    }

    @Test
    void shouldSetCurrentScoreToSixRuns() {
        this.scoreBoard.setCurrentScore(6);

        assertEquals(this.scoreBoard.getCurrentScore(), 6);
    }

    @Test
    void shouldReturnZeroAsInitialNoOfOuts() {
        assertEquals(0, this.scoreBoard.getNoOfOuts());
    }

    @Test
    void shouldSetNoOfOutsAsFive() {
        this.scoreBoard.setNoOfOuts(5);

        assertEquals(this.scoreBoard.getNoOfOuts(), 5);
    }

    @Test
    void shouldSetNoOfOutsAsSix() {
        this.scoreBoard.setNoOfOuts(6);

        assertEquals(this.scoreBoard.getNoOfOuts(), 6);
    }

    @Test
    void shouldSetNoOfBallsFacedToTen() {
        this.scoreBoard.setNoOfBallsFaced(10);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 10);
    }

    @Test
    void shouldSetNoOfBallsFacedToTwenty() {
        this.scoreBoard.setNoOfBallsFaced(20);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 20);
    }

    @Test
    void shouldSetTotalOversAsOne() {
        this.scoreBoard.setTotalOvers(1);

        assertEquals(this.scoreBoard.getTotalOvers(), 1);
    }

    @Test
    void shouldSetTotalOversAsTwo() {
        this.scoreBoard.setTotalOvers(2);

        assertEquals(this.scoreBoard.getTotalOvers(), 2);
    }

    @Test
    void shouldSetTotalWicketsAsOne() {
        this.scoreBoard.setTotalWickets(1);

        assertEquals(this.scoreBoard.getTotalWickets(), 1);
    }

    @Test
    void shouldSetTotalWicketsAsTwo() {
        this.scoreBoard.setTotalWickets(2);

        assertEquals(this.scoreBoard.getTotalWickets(), 2);
    }

    @Test
    void shouldSetRequiredScoreAsTwo() {
        this.scoreBoard.setRequiredScore(2);

        assertEquals(this.scoreBoard.getRequiredScore(), 2);
    }

    @Test
    void shouldSetRequiredScoreAsFive() {
        this.scoreBoard.setRequiredScore(5);

        assertEquals(this.scoreBoard.getRequiredScore(), 5);
    }

    @Test
    public void shouldSetCurrentPlayerAsSrinu() {
        Player striker = new Player("Srinu");

        scoreBoard.setCurrentPlayer(striker);

        assertEquals(scoreBoard.getCurrentPlayer(), striker);
    }

    @Test
    public void shouldSetCurrentPlayerAsKohli() {
        Player striker = new Player("Kohli");

        scoreBoard.setCurrentPlayer(striker);

        assertEquals(scoreBoard.getCurrentPlayer(), striker);
    }

    @Test
    public void setCurrentBallStatusAsOut() {
        scoreBoard.setCurrentBallStatus(Score.OUT);

        assertEquals(scoreBoard.getCurrentBallStatus(), Score.OUT);
    }

    @Test
    public void setCurrentBallStatusAsTWORuns() {
        scoreBoard.setCurrentBallStatus(Score.TWO);

        assertEquals(scoreBoard.getCurrentBallStatus(), Score.TWO);
    }

}
