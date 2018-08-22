package service;

public class MatchService {

    private final ScoreBoardService scoreBoardService;
    private final PlayerService playerService;

    public MatchService(ScoreBoardService scoreBoardService, PlayerService playerService) {
        this.scoreBoardService = scoreBoardService;
        this.playerService = playerService;
    }

    public void start() {
        int runs = this.playerService.score().getValue();
        this.scoreBoardService.addScore(runs);
    }
}
