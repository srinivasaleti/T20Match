package service;

import model.Player;
import model.Score;
import model.ScoreBoard;

import java.util.Arrays;
import java.util.List;

import static model.Score.*;

public class PlayerService {

    private static final List<Score> scores = Arrays.asList(OUT, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX);

    private final RandomScoreGeneratorService randomScoreGeneratorService;
    private Player striker;
    private Player nonStriker;

    public PlayerService(RandomScoreGeneratorService randomScoreGeneratorService) {
        this.randomScoreGeneratorService = randomScoreGeneratorService;
    }

    public Score score() {
        List<Integer> scoreWeights = this.striker.getScoreWeights();
        return this.randomScoreGeneratorService.generateBasedOnFrequencies(scores, scoreWeights);
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

    public void takeActionBasedOn(ScoreBoard scoreBoard) {
        Score currentBallStatus = scoreBoard.getCurrentBallStatus();
        boolean oddScore = (currentBallStatus.getValue() % 2 == 1);
        boolean endOfTheOver = scoreBoard.getNoOfBallsFaced() % 6 == 0;
        boolean switchStriking = (oddScore ^ endOfTheOver);
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
