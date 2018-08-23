package controller;

import model.ScoreBoard;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CommentaryService;
import service.MatchService;

import java.util.List;

import static org.mockito.Mockito.*;

class GameControllerTest {

    private ScoreBoard scoreBoard;
    private int totalOvers = 10;
    private int requiredScore = 100;
    private MatchService matchService;
    private Team team;
    private GameController gameController;
    private CommentaryService commentaryService;

    @BeforeEach
    void setUp() {
        this.scoreBoard = mock(ScoreBoard.class);
        this.matchService = mock(MatchService.class);
        this.commentaryService = mock(CommentaryService.class);
        this.team = mock(Team.class);
        this.gameController = new GameController(totalOvers, requiredScore, team, scoreBoard, matchService, commentaryService);
    }

    @Test
    void shouldSetTotalScoreOnScoreBoard() {
        this.gameController.start();

        verify(this.scoreBoard).setRequiredScore(this.requiredScore);
    }

    @Test
    void shouldSetTotalSOversOnScoreBoard() {
        this.gameController.start();

        verify(this.scoreBoard).setTotalOvers(this.totalOvers);
    }

    @Test
    void shouldAskTeamListOfPlayers() {
        this.gameController.start();

        verify(this.team).players();
    }

    @Test
    void shouldSetTotalWicketsToOneLessThanTeamSize() {
        List players = mock(List.class);
        when(this.team.players()).thenReturn(players);
        when(players.size()).thenReturn(10);

        this.gameController.start();

        verify(this.scoreBoard).setTotalWickets(9);
    }

    @Test
    void shouldStartTheMatch() {
        this.gameController.start();

        this.matchService.start();
    }

    @Test
    void shouldAnnounceResults() {
        this.gameController.start();

        verify(this.commentaryService).announceResults(scoreBoard);
    }

}