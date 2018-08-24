package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    private static final int NUMBER_OF_SCORE_ENUMS = 8;

    private PlayerService playerService;
    private ScoreBoard scoreBoard;
    private RandomScoreGeneratorService randomScoreGeneratorService;
    private Player striker;
    private Player nonStriker;
    private List scoreWeights = mock(List.class);

    @BeforeEach
    void setUp() {
        this.striker = mock(Player.class);
        this.nonStriker = mock(Player.class);
        randomScoreGeneratorService = mock(RandomScoreGeneratorService.class);
        playerService = new PlayerService(randomScoreGeneratorService);
        scoreBoard = mock(ScoreBoard.class);

        playerService.setStriker(this.striker);
        playerService.setNonStriker(this.nonStriker);
    }

    @Test
    void shouldAskRandomScoreGeneratorServiceToGetAScore() {
        when(striker.getScoreWeights()).thenReturn(scoreWeights);
        when(randomScoreGeneratorService.generateBasedOnFrequencies(any(), any())).thenReturn(Score.OUT);

        playerService.score();

        verify(this.randomScoreGeneratorService).generateBasedOnFrequencies(any(), any());
    }

    @Test
    void shouldReturnScoreReturnByRandomScoreGeneratorService() {
        when(striker.getScoreWeights()).thenReturn(scoreWeights);
        when(randomScoreGeneratorService.generateBasedOnFrequencies(any(), any())).thenReturn(Score.OUT);

        assertEquals(Score.OUT, playerService.score());
    }

    @Test
    void shouldReturnZEROIfRandomGeneratorServiceReturnsZero() {
        playerService.setStriker(this.striker);
        when(striker.getScoreWeights()).thenReturn(scoreWeights);
        when(randomScoreGeneratorService.generateBasedOnFrequencies(any(), any())).thenReturn(Score.ZERO);

        assertEquals(Score.ZERO, playerService.score());
    }


    @Test
    public void shouldSetStrikerAsSrinu() {
        Player striker = new Player("Srinu", scoreWeights);

        playerService.setStriker(striker);

        assertEquals(playerService.striker(), striker);
    }

    @Test
    public void shouldSetStrikerAsKohli() {
        Player striker = new Player("Kohli", scoreWeights);

        playerService.setStriker(striker);

        assertEquals(playerService.striker(), striker);
    }

    public void shouldSetNonStrikerAsSrinu() {
        Player nonStriker = new Player("Srinu", scoreWeights);

        playerService.setNonStriker(nonStriker);

        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldSetNonStrikerAsKohli() {
        Player nonStriker = new Player("Kohli", scoreWeights);

        playerService.setNonStriker(nonStriker);

        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldRotateTheStrikeWhenStrikerScoresOddRuns() {
        Player striker = new Player("Srinu", scoreWeights);
        Player nonStriker = new Player("Kohli", scoreWeights);
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

        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.TWO);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(5);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), striker);
        assertEquals(playerService.nonStriker(), nonStriker);
    }

    @Test
    public void shouldNotRotateTheStrikeWhenStrikerScoresOddRunsAtEndOfOver() {
        Player striker = new Player("Srinu", scoreWeights);
        Player nonStriker = new Player("Kohli", scoreWeights);
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
        Player striker = new Player("Srinu", scoreWeights);
        Player nonStriker = new Player("Kohli", scoreWeights);
        playerService.setStriker(striker);
        playerService.setNonStriker(nonStriker);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.TWO);
        when(scoreBoard.getNoOfBallsFaced()).thenReturn(6);

        playerService.takeActionBasedOn(scoreBoard);

        assertEquals(playerService.striker(), nonStriker);
        assertEquals(playerService.nonStriker(), striker);
    }

}
