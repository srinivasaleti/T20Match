package service;

import model.Player;
import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MatchServiceTest {

    private MatchService matchService;
    private ScoreBoardService scoreBoardService;
    private PlayerService playerService;
    private Player striker;

    @BeforeEach
    void setUp() {
        scoreBoardService = mock(ScoreBoardService.class);
        playerService = mock(PlayerService.class);
        striker = mock(Player.class);
        matchService = new MatchService(scoreBoardService, playerService);

        when(playerService.striker()).thenReturn(striker);
    }

    @Test
    public void shouldUpdateScore() {
        Score zeroRuns = Score.ZERO;
        when(scoreBoardService.isMatchFinish()).thenReturn(true);
        when(playerService.score()).thenReturn(zeroRuns);

        matchService.start();

        verify(scoreBoardService).updateScore(striker, zeroRuns);
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

        verify(scoreBoardService, times(4)).updateScore(any(), any());
        verify(scoreBoardService, times(4)).isMatchFinish();
    }
}
