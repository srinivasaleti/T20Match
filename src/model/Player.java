package model;

import java.util.List;

public class Player {
    private final String name;
    private int score;
    private boolean out;
    private int ballsFaced;
    private final List<Integer> scoreWeights;

    public Player(String name, List<Integer> scoreWeights) {
        this.name = name;
        this.scoreWeights = scoreWeights;
        this.score = 0;
        this.out = false;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void goesToPavilions() {
        this.out = true;
    }

    public boolean out() {
        return out;
    }

    public String getName() {
        return name;
    }

    public int ballsFaced() {
        return this.ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public List<Integer> getScoreWeights() {
        return scoreWeights;
    }

}
