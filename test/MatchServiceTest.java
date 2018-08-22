import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchServiceTest {

    private MatchService matchService;

    @BeforeEach
    void setUp() {
        matchService = new MatchService();
    }

    @Test
    public void shouldNotIncrementScoreIfPlayerDoesNotScoreAnyRuns() {
        matchService.addScore(0);

        assertEquals(matchService.currentScore(), 0);
    }

    @Test
    public void shouldIncrementCurrentScoreIfPlayerScoredMoreThanOneRun() {
        matchService.addScore(2);
        assertEquals(matchService.currentScore(), 2);
    }

}
