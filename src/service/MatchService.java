package service;

import model.Score;

import static model.Score.OUT;

public class MatchService {

    private final ScoreBoardService scoreBoardService;
    private final PlayerService playerService;

    public MatchService(ScoreBoardService scoreBoardService, PlayerService playerService) {
        this.scoreBoardService = scoreBoardService;
        this.playerService = playerService;
    }

    public void start() {
        Score score = this.playerService.score();
        if (score == OUT) {
            scoreBoardService.incrementNoOfOuts();
        } else {
            int runs = score.getValue();
            this.scoreBoardService.addScore(runs);
        }
    }
}
