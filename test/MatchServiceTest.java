import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MatchServiceTest {

    private MatchService matchService;
    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        scoreBoardService = mock(ScoreBoardService.class);
        matchService = new MatchService(scoreBoardService);
    }

    @Test
    public void shouldNotIncrementScoreIfPlayerDoesNotScoreAnyRuns() {
        matchService.addScore(0);

        verify(scoreBoardService).addScore(0);
    }

    @Test
    public void shouldIncrementCurrentScoreIfPlayerScoredMoreThanOneRun() {
        matchService.addScore(2);

        verify(scoreBoardService).addScore(2);
    }

}
