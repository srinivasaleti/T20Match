package service;

import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        this.scoreBoardService = new ScoreBoardService(new ScoreBoard());
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
}
