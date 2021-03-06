package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;
    private ScoreBoard scoreBoard;
    private Player player;

    @BeforeEach
    void setUp() {
        this.scoreBoard = new ScoreBoard();
        this.scoreBoardService = new ScoreBoardService(scoreBoard);
        this.player = new Player("Srinu", mock(List.class));
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByOneRun() {
        this.scoreBoardService.update(player, Score.ONE);

        assertEquals(1, this.scoreBoard.getCurrentScore());
        assertEquals(1, this.player.getScore());
    }

    @Test
    public void shouldUpdateCurrentScoreAndPlayerScoreByTenRuns() {
        this.scoreBoardService.update(player, Score.ONE);
        this.scoreBoardService.update(player, Score.SIX);
        this.scoreBoardService.update(player, Score.THREE);

        assertEquals(10, this.scoreBoard.getCurrentScore());
        assertEquals(10, this.player.getScore());
    }

    @Test
    public void shouldMakePlayerAsOut() {
        this.scoreBoardService.update(player, Score.OUT);

        assertTrue(player.out());
        assertEquals(1, this.scoreBoard.getNoOfOuts());
    }

    @Test
    public void shouldMakeThreePlayersAsOut() {
        Player player1 = new Player("Srinu", mock(List.class));
        Player player2 = new Player("Kohli", mock(List.class));
        Player player3 = new Player("Sachine", mock(List.class));

        this.scoreBoardService.update(player1, Score.OUT);
        this.scoreBoardService.update(player2, Score.OUT);
        this.scoreBoardService.update(player3, Score.OUT);

        assertEquals(3, this.scoreBoard.getNoOfOuts());
        assertTrue(player1.out());
        assertTrue(player2.out());
        assertTrue(player3.out());
    }

    @Test
    public void shouldReturnFalseIfScoreNotLevel() {
        this.scoreBoard.setRequiredScore(10);
        this.scoreBoard.setTotalWickets(3);

        this.scoreBoardService.update(player, Score.TWO);
        this.scoreBoardService.update(player, Score.THREE);

        assertFalse(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfScoreLevel() {
        this.scoreBoard.setRequiredScore(10);

        this.scoreBoardService.update(player, Score.TWO);
        this.scoreBoardService.update(player, Score.SIX);
        this.scoreBoardService.update(player, Score.TWO);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamScoresMoreThanRequiredRuns() {
        this.scoreBoard.setRequiredScore(10);

        this.scoreBoardService.update(player, Score.TWO);
        this.scoreBoardService.update(player, Score.SIX);
        this.scoreBoardService.update(player, Score.THREE);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfTeamIsAllOut() {
        this.scoreBoard.setRequiredScore(10);
        this.scoreBoard.setTotalWickets(3);

        this.scoreBoardService.update(player, Score.OUT);
        this.scoreBoardService.update(player, Score.OUT);
        this.scoreBoardService.update(player, Score.OUT);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldIncreaseNoOfBallsFacedByOneRun() {
        this.scoreBoardService.update(player, Score.OUT);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 1);
    }

    @Test
    public void shouldIncreaseNoOfBallsFacedByTwoRun() {
        this.scoreBoardService.update(player, Score.OUT);
        this.scoreBoardService.update(player, Score.ZERO);

        assertEquals(this.scoreBoard.getNoOfBallsFaced(), 2);
    }

    @Test
    public void shouldReturnTrueIfAllOversFinished() {
        this.scoreBoard.setTotalOvers(1);

        this.scoreBoardService.update(player, Score.ZERO);
        this.scoreBoardService.update(player, Score.ZERO);
        this.scoreBoardService.update(player, Score.ZERO);
        this.scoreBoardService.update(player, Score.ZERO);
        this.scoreBoardService.update(player, Score.ZERO);
        this.scoreBoardService.update(player, Score.ZERO);

        assertTrue(this.scoreBoardService.isMatchFinish());
    }

    @Test
    public void shouldReturnTrueIfOverFinished() {
        this.scoreBoard.setNoOfBallsFaced(18);

        assertTrue(this.scoreBoardService.isEndOfTheOver());
    }


    @Test
    public void shouldReturnFalseIfOverNotFinished() {
        this.scoreBoard.setNoOfBallsFaced(17);

        assertFalse(this.scoreBoardService.isEndOfTheOver());
    }

    @Test
    public void shouldSetCurrentBallStatusAsONE() {
        this.scoreBoardService.update(player, Score.ONE);

        assertEquals(this.scoreBoard.getCurrentBallStatus(), Score.ONE);
    }

    @Test
    public void shouldSetCurrentBallStatusAsOUT() {
        this.scoreBoardService.update(player, Score.OUT);

        assertEquals(this.scoreBoard.getCurrentBallStatus(), Score.OUT);
    }

    @Test
    public void shouldSetCurrentPlayerOnScoreBoard() {
        this.scoreBoardService.update(player, Score.OUT);

        assertEquals(this.scoreBoard.getCurrentPlayer(), player);
    }

    @Test
    public void shouldReturnScoreBoard() {
        assertEquals(scoreBoard, this.scoreBoardService.scoreBoard());
    }

    @Test
    public void shouldUpdateBallsFacedByCurrentPlayerByOneRun() {
        player.setBallsFaced(2);
        this.scoreBoardService.update(player, Score.OUT);

        assertEquals(this.player.ballsFaced(), 3);
    }

    @Test
    public void shouldUpdateBallsFacedByCurrentPlayerByTwoRuns() {
        player.setBallsFaced(2);
        this.scoreBoardService.update(player, Score.OUT);
        this.scoreBoardService.update(player, Score.OUT);

        assertEquals(this.player.ballsFaced(), 4);
    }
}
