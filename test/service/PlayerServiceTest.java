package service;

import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {

    private static final int NUMBER_OF_SCORE_ENUMS = 8;

    private PlayerService playerService;
    private Random random;

    @BeforeEach
    void setUp() {
        random = mock(Random.class);
        playerService = new PlayerService(random);
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

}
