package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {

    private static final int NUMBER_OF_SCORE_ENUMS = 8;

    private PlayerService playerService;
    private ScoreBoard scoreBoard;
    private Random random;

    @BeforeEach
    void setUp() {
        random = mock(Random.class);
        playerService = new PlayerService(random);
        scoreBoard = mock(ScoreBoard.class);
    }

    @Test
    void shouldReturnOUT() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(0);

        assertEquals(Score.OUT, playerService.score());
    }

    @Test
    void shouldReturnZERO() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(1);

        assertEquals(Score.ZERO, playerService.score());
    }

    @Test
    void shouldReturnONE() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(2);

        assertEquals(Score.ONE, playerService.score());
    }

    @Test
    void shouldReturnTWO() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(3);

        assertEquals(Score.TWO, playerService.score());
    }

    @Test
    void shouldReturnTHREE() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(4);

        assertEquals(Score.THREE, playerService.score());
    }

    @Test
    void shouldReturnFOUR() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(5);

        assertEquals(Score.FOUR, playerService.score());
    }

    @Test
    void shouldReturnFIVE() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(6);

        assertEquals(Score.FIVE, playerService.score());
    }

    @Test
    void shouldReturnSIX() {
        when(random.nextInt(NUMBER_OF_SCORE_ENUMS)).thenReturn(7);

        assertEquals(Score.SIX, playerService.score());
    }

    @Test
    public void shouldSetStrikerAsSrinu() {
        Player striker = new Player("Srinu");

        playerService.setStriker(striker);

        assertEquals(playerService.striker(), striker);
    }

    @Test
    public void shouldSetStrikerAsKohli() {
        Player striker = new Player("Kohli");

        playerService.setStriker(striker);

        assertEquals(playerService.striker(), striker);
    }

    public void shouldSetNonStrikerAsSrinu() {
        Player nonStriker = new Player("Srinu");

        playerService.setNonStriker(nonStriker);

        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldSetNonStrikerAsKohli() {
        Player nonStriker = new Player("Kohli");

        playerService.setNonStriker(nonStriker);

        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldRotateTheStrikeWhenStrikerScoresOddRuns() {
        Player striker = new Player("Srinu");
        Player nonStriker = new Player("Kohli");
        playerService.setStriker(striker);
        playerService.setNonStriker(nonStriker);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.ONE);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(5);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), nonStriker);
        assertEquals(playerService.nonStriker(), striker);
    }

    @Test
    public void shouldNotRotateTheStrikeWhenStrikerScoresEvenRuns() {
        Player striker = new Player("Srinu");
        Player nonStriker = new Player("Kohli");
        playerService.setStriker(striker);
        playerService.setNonStriker(nonStriker);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.TWO);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(5);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), striker);
        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldNotRotateTheStrikeWhenStrikerScoresOddRunsAtEndOfOver() {
        Player striker = new Player("Srinu");
        Player nonStriker = new Player("Kohli");
        playerService.setStriker(striker);
        playerService.setNonStriker(nonStriker);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.ONE);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(6);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), striker);
        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldSwitchTheStrikeWhenStrikerScoresEvenRunsAtEndOfOver() {
        Player striker = new Player("Srinu");
        Player nonStriker = new Player("Kohli");
        playerService.setStriker(striker);
        playerService.setNonStriker(nonStriker);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.TWO);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(6);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), nonStriker);
        assertEquals(playerService.nonStriker(), striker);
    }

}
