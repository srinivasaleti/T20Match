public class MatchService {

    private int currentScore;

    public MatchService() {
        currentScore = 0;
    }

    public void addScore(int runs) {
        this.currentScore += runs;
    }

    public int currentScore() {
        return this.currentScore;
    }
}
