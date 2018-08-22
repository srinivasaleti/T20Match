package service;

import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class MatchServiceTest {

    private MatchService matchService;
    private ScoreBoardService scoreBoardService;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        scoreBoardService = mock(ScoreBoardService.class);
        playerService = mock(PlayerService.class);
        matchService = new MatchService(scoreBoardService, playerService);
    }

    @Test
    public void shouldNotIncrementScoreIfPlayerDoesNotScoreAnyRuns() {
        Score zeroRuns = Score.ZERO;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(zeroRuns);

        matchService.start();

        verify(scoreBoardService).addScore(zeroRuns.getValue());
    }

    @Test
    public void shouldIncrementCurrentScoreIfPlayerScoredMoreThanOneRun() {
        Score twoRuns = Score.TWO;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(twoRuns);

        matchService.start();

        verify(scoreBoardService).addScore(twoRuns.getValue());
    }

    @Test
    public void shouldAddIncrementOutsIfPlayerIsOut() {
        Score zeroRuns = Score.OUT;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(zeroRuns);

        matchService.start();

        verify(scoreBoardService).incrementNoOfOuts();
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

        verify(scoreBoardService, times(4)).isMatchFinish();
    }
}
