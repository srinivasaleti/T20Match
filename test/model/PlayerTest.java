package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private final List<Integer> scoreWeights = Arrays.asList(1, 2, 3);

    @BeforeEach
    void setUp() {
        player = new Player("Srinu", scoreWeights);
    }

    @Test
    public void shouldReturnZeroAsInitialScore() {
        assertEquals(0, this.player.getScore());
    }

    @Test
    public void shouldReturnFalseAsInitialPavilionStatus() {
        assertFalse(this.player.out());
    }

    @Test
    public void shouldUpdateScore() {
        int score = 10;
        this.player.updateScore(score);

        assertEquals(this.player.getScore(), score);
    }

    @Test
    public void shouldReturnTrueAsPavilionStatusIfPlayerIsOut() {
        this.player.goesToPavilions();

        assertTrue(this.player.out());
    }

    @Test
    public void shouldReturnNameAsSrinu() {
        Player player = new Player("Srinu", scoreWeights);

        assertEquals(player.getName(), "Srinu");
    }

    @Test
    public void shouldReturnNameAsKohli() {
        Player player = new Player("Kohli", scoreWeights);

        assertEquals(player.getName(), "Kohli");
    }

    @Test
    public void shouldReturnOneAsBallsFaced() {
        Player player = new Player("Srinu", scoreWeights);

        player.setBallsFaced(1);
        assertEquals(player.ballsFaced(), 1);
    }

    @Test
    public void shouldReturnTwoAsBallsFaced() {
        Player player = new Player("Kohli", scoreWeights);

        player.setBallsFaced(2);

        assertEquals(player.ballsFaced(), 2);
    }

}
