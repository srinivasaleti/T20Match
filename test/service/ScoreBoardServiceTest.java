package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;
    private ScoreBoard scoreBoard;
    private Player player;

    @BeforeEach
    void setUp() {
        this.scoreBoard = new ScoreBoard();
        this.scoreBoardService = new ScoreBoardService(scoreBoard);
        this.player = new Player("Srinu");
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByOneRun() {
        this.scoreBoardService.updateScore(player, Score.ONE);

        assertEquals(1, this.scoreBoard.getCurrentScore());
        assertEquals(1, this.player.getScore());
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByTenRuns() {
        this.scoreBoardService.updateScore(player, Score.ONE);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertEquals(10, this.scoreBoard.getCurrentScore());
        assertEquals(10, this.player.getScore());
    }

    @Test
    public void shouldMakePlayerAsOut() {
        this.scoreBoardService.updateScore(player, Score.OUT);

        assertTrue(player.out());
        assertEquals(1, this.scoreBoard.getNoOfOuts());
    }

    @Test
    public void shouldMakeThreePlayersAsOut() {
        Player player1 = new Player("Srinu");
        Player player2 = new Player("Kohli");
        Player player3 = new Player("Sachine");

        this.scoreBoardService.updateScore(player1, Score.OUT);
        this.scoreBoardService.updateScore(player2, Score.OUT);
        this.scoreBoardService.updateScore(player3, Score.OUT);

        assertEquals(3, this.scoreBoard.getNoOfOuts());
        assertTrue(player1.out());
        assertTrue(player2.out());
        assertTrue(player3.out());
    }

    @Test
    public void shouldReturnFalseIfScoreNotLevel() {
        this.scoreBoard.setRequiredScore(10);
        this.scoreBoard.setTotalWickets(3);

        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertFalse(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfScoreLevel() {
        this.scoreBoard.setRequiredScore(10);

        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.TWO);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamScoresMoreThanRequiredRuns() {
        this.scoreBoard.setRequiredScore(10);

        this.scoreBoardService.updateScore(player, Score.TWO);
        this.scoreBoardService.updateScore(player, Score.SIX);
        this.scoreBoardService.updateScore(player, Score.THREE);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamIsAllOut() {
        this.scoreBoard.setRequiredScore(10);
        this.scoreBoard.setTotalWickets(3);

        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.OUT);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldIncreaseNoOfBallsFacedByOneRun() {
        this.scoreBoardService.updateScore(player, Score.OUT);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 1);
    }

    @Test
    public void shouldIncreaseNoOfBallsFacedByTwoRun() {
        this.scoreBoardService.updateScore(player, Score.OUT);
        this.scoreBoardService.updateScore(player, Score.ZERO);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 2);
    }

    @Test
    public void shouldReturnTrueIfAllOversFinished() {
        this.scoreBoard.setTotalOvers(1);

        this.scoreBoardService.updateScore(player, Score.ZERO);
        this.scoreBoardService.updateScore(player, Score.ZERO);
        this.scoreBoardService.updateScore(player, Score.ZERO);
        this.scoreBoardService.updateScore(player, Score.ZERO);
        this.scoreBoardService.updateScore(player, Score.ZERO);
        this.scoreBoardService.updateScore(player, Score.ZERO);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

}
