package service;

import model.Player;
import model.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static model.Score.*;

public class PlayerService {

    private static final List<Score> scores = Arrays.asList(OUT, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX);

    private final Random random;
    private Player striker;

    public PlayerService(Random random) {
        this.random = random;
    }

    public Score score() {
        int randomScore = this.random.nextInt(scores.size());
        return scores.get(randomScore);
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player striker() {
        return striker;
    }

}
