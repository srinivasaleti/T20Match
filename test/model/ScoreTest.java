package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @Test
    void shouldReturnValueOfScoreZERO() {
        assertEquals(Score.ZERO.getValue(), 0);
    }

    @Test
    void shouldReturnValueOfScoreONE() {
        assertEquals(Score.ONE.getValue(), 1);
    }

    @Test
    void shouldReturnValueOfScoreTWO() {
        assertEquals(Score.TWO.getValue(), 2);
    }

    @Test
    void shouldReturnValueOfScoreTHREE() {
        assertEquals(Score.THREE.getValue(), 3);
    }

    @Test
    void shouldReturnValueOfScoreFOUR() {
        assertEquals(Score.FOUR.getValue(), 4);
    }

    @Test
    void shouldReturnValueOfScoreFIVE() {
        assertEquals(Score.FIVE.getValue(), 5);
    }

    @Test
    void shouldReturnValueOfScoreSIX() {
        assertEquals(Score.SIX.getValue(), 6);
    }

    @Test
    void shouldReturnValueOfScoreOUT() {
        assertEquals(Score.OUT.getValue(), -1);
    }

}
