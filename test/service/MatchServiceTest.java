package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MatchServiceTest {

    private MatchService matchService;
    private ScoreBoardService scoreBoardService;
    private PlayerService playerService;
    private TeamService teamService;
    private CommentaryService commentaryService;
    private Player striker;

    @BeforeEach
    void setUp() {
        scoreBoardService = mock(ScoreBoardService.class);
        playerService = mock(PlayerService.class);
        striker = mock(Player.class);
        teamService = mock(TeamService.class);
        commentaryService = mock(CommentaryService.class);
        matchService = new MatchService(scoreBoardService, playerService, teamService, commentaryService);

        when(playerService.striker()).thenReturn(striker);
    }

    @Test
    public void shouldUpdateScore() {
        Score zeroRuns = Score.ZERO;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(zeroRuns);

        matchService.start();

        verify(scoreBoardService).update(striker, zeroRuns);
    }

    @Test
    public void shouldPlayTheGameUntilFinish() {
        Score zeroRuns = Score.OUT;

        when(scoreBoardService.isMatchFinish())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);
        when(playerService.score())
                .thenReturn(zeroRuns)
                .thenReturn(Score.FIVE)
                .thenReturn(Score.FIVE);

        matchService.start();

        verify(scoreBoardService, times(4)).update(any(), any());
        verify(scoreBoardService, times(4)).isMatchFinish();
    }

    @Test
    public void shouldAskPlayerServiceToTakeProperActionBasedOnScore() {
        ScoreBoard scoreBoard = mock(ScoreBoard.class);
        Score score = Score.ZERO;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(score);
        when(scoreBoardService.scoreBoard()).thenReturn(scoreBoard);

        matchService.start();

        verify(playerService).takeActionBasedOn(scoreBoard);
    }

    @Test
    public void shouldAskTeamServiceToGetStrikerAndNonStriker() {
        Score score = Score.ONE;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(score);

        matchService.start();

        verify(teamService, times(1)).assignStrikerAndNonStriker();
    }

    @Test
    public void shouldAskTeamServiceToTakeProperActionBasedOnScore() {
        ScoreBoard scoreBoard = mock(ScoreBoard.class);
        Score score = Score.ONE;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(score);
        when(scoreBoardService.scoreBoard()).thenReturn(scoreBoard);
        matchService.start();

        verify(teamService).takeActionBasedOn(scoreBoard);
    }

    @Test
    public void shouldPlayAnnounceTheCommentary() {
        ScoreBoard scoreBoard = mock(ScoreBoard.class);
        when(scoreBoardService.scoreBoard()).thenReturn(scoreBoard);
        when(scoreBoardService.isMatchFinish())
                .thenReturn(true);
        when(playerService.score())
                .thenReturn(Score.FIVE);

        matchService.start();

        verify(commentaryService).announce(scoreBoard);
        verify(scoreBoardService).isMatchFinish();
    }

    @Test
    public void shouldPlayAnnounceTheCommentaryForEachBall() {
        Score zeroRuns = Score.OUT;
        ScoreBoard scoreBoard = mock(ScoreBoard.class);
        when(scoreBoardService.scoreBoard()).thenReturn(scoreBoard);
        when(scoreBoardService.isMatchFinish())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);
        when(playerService.score())
                .thenReturn(zeroRuns)
                .thenReturn(Score.FIVE)
                .thenReturn(Score.FIVE);

        matchService.start();

        verify(commentaryService, times(4)).announce(scoreBoard);
        verify(scoreBoardService, times(4)).isMatchFinish();
    }

}
