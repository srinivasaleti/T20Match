package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;
    private Player player;

    @BeforeEach
    void setUp() {
        this.scoreBoardService = new ScoreBoardService(new ScoreBoard(), 10, 4);
        this.player = new Player("Srinu");
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByOneRun() {
        this.scoreBoardService.updateScore(player, Score.ONE);

        assertEquals(1, this.scoreBoardService.getCurrentScore());
        assertEquals(1, this.player.getScore());
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByTenRuns() {
        this.scoreBoardService.updateScore(player, Score.ONE);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertEquals(10, this.scoreBoardService.getCurrentScore());
        assertEquals(10, this.player.getScore());
    }

    @Test
    public void shouldMakePlayerAsOut() {
        this.scoreBoardService.updateScore(player, Score.OUT);

        assertTrue(player.out());
        assertEquals(1, this.scoreBoardService.getNumberOfOuts());
    }

    @Test
    public void shouldMakeThreePlayersAsOut() {
        Player player1 = new Player("Srinu");
        Player player2 = new Player("Kohli");
        Player player3 = new Player("Sachine");

        this.scoreBoardService.updateScore(player1, Score.OUT);
        this.scoreBoardService.updateScore(player2, Score.OUT);
        this.scoreBoardService.updateScore(player3, Score.OUT);

        assertEquals(3, this.scoreBoardService.getNumberOfOuts());
        assertTrue(player1.out());
        assertTrue(player2.out());
        assertTrue(player3.out());
    }

    @Test
    public void shouldReturnFalseIfScoreNotLevel() {
        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertFalse(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfScoreLevel() {
        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.TWO);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamScoresMoreThanRequiredRuns() {
        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamIsAllOut() {
        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.OUT);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

}
