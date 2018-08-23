package model;

public class Player {
    private final String name;
    private int score;
    private boolean out;
    private int ballsFaced;

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

    public String getName() {
        return name;
    }

    public int ballsFaced() {
        return this.ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

}
