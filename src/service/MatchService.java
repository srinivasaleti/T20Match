package service;

public class MatchService {

    private final ScoreBoardService scoreBoardService;

    public MatchService(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    public void addScore(int runs) {
        this.scoreBoardService.addScore(runs);
    }

}
