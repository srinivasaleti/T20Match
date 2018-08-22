package service;

import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        this.scoreBoardService = new ScoreBoardService(new ScoreBoard(), 10, 4);
    }

    @Test
    public void shouldIncrementCurrentScoreByOneRuns() {
        this.scoreBoardService.addScore(1);

        assertEquals(1, this.scoreBoardService.getCurrentScore());
    }

    @Test
    public void shouldIncrementCurrentScoreByTenRuns() {
        this.scoreBoardService.addScore(1);
        this.scoreBoardService.addScore(6);
        this.scoreBoardService.addScore(3);

        assertEquals(10, this.scoreBoardService.getCurrentScore());
    }

    @Test
    public void shouldMakeOnePlayerAsOut() {
        this.scoreBoardService.incrementNoOfOuts();

        assertEquals(1, this.scoreBoardService.getNumberOfOuts());
    }

    @Test
    public void shouldMakeThreePlayersAsOut() {
        this.scoreBoardService.incrementNoOfOuts();
        this.scoreBoardService.incrementNoOfOuts();
        this.scoreBoardService.incrementNoOfOuts();

        assertEquals(3, this.scoreBoardService.getNumberOfOuts());
    }

    @Test
    public void shouldReturnFalseIfScoreNotLevel() {
        this.scoreBoardService.addScore(2);
        this.scoreBoardService.addScore(6);

        assertFalse(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfScoreLevel() {
        this.scoreBoardService.addScore(2);
        this.scoreBoardService.addScore(6);
        this.scoreBoardService.addScore(2);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamScoresMoreThanRequiredRuns() {
        this.scoreBoardService.addScore(2);
        this.scoreBoardService.addScore(6);
        this.scoreBoardService.addScore(2);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfAllOut() {
        this.scoreBoardService.incrementNoOfOuts();
        this.scoreBoardService.incrementNoOfOuts();
        this.scoreBoardService.incrementNoOfOuts();
        this.scoreBoardService.incrementNoOfOuts();

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

}
