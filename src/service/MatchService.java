package service;

import model.Player;
import model.Score;

public class MatchService {

    private final ScoreBoardService scoreBoardService;
    private final PlayerService playerService;

    public MatchService(ScoreBoardService scoreBoardService, PlayerService playerService) {
        this.scoreBoardService = scoreBoardService;
        this.playerService = playerService;
    }

    public void start() {
        do {
            Score score = this.playerService.score();
            Player striker = playerService.striker();
            this.scoreBoardService.updateScore(striker, score);
        } while (!this.scoreBoardService.isMatchFinish());
    }

}
