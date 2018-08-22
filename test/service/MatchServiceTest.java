package service;

import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(playerService.score()).thenReturn(zeroRuns);

        matchService.start();

        verify(scoreBoardService).addScore(zeroRuns.getValue());
    }

    @Test
    public void shouldIncrementCurrentScoreIfPlayerScoredMoreThanOneRun() {
        Score twoRuns = Score.TWO;
        when(playerService.score()).thenReturn(twoRuns);

        matchService.start();

        verify(scoreBoardService).addScore(twoRuns.getValue());
    }

}
