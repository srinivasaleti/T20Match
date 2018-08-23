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
    private Player nonStriker;

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

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    public Player striker() {
        return striker;
    }

    public Player nonStriker() {
        return nonStriker;
    }

    public void takeAction(Score score, boolean isOverFinished) {
        boolean isOddScore = (score.getValue() % 2 == 1);
        boolean switchStriking = (isOddScore ^ isOverFinished);
        if (switchStriking) {
            switchStriking();
        }
    }

    private void switchStriking() {
        Player dummyStriker = striker;
        striker = nonStriker;
        nonStriker = dummyStriker;
    }

}
