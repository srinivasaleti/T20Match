package model;

public class Player {
    private final String name;
    private int score;
    private boolean out;

    public Player(String name) {
        this.name = name;
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

}
