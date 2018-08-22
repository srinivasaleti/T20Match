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

}
