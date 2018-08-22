public class ScoreBoardService {

    private ScoreBoard scoreBoard;

    public ScoreBoardService(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void addScore(int runs) {
        int currentScore = this.scoreBoard.getCurrentScore();
        this.scoreBoard.setCurrentScore(currentScore + runs);
    }

    public int getCurrentScore() {
        return this.scoreBoard.getCurrentScore();
    }
}
