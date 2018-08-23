package service;

import model.Player;
import model.Score;

public class MatchService {

    private final ScoreBoardService scoreBoardService;
    private final PlayerService playerService;
    private final TeamService teamService;

    public MatchService(ScoreBoardService scoreBoardService, PlayerService playerService, TeamService teamService) {
        this.scoreBoardService = scoreBoardService;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public void start() {
        teamService.assignStrikerAndNonStriker();
        do {
            Score score = this.playerService.score();
            Player striker = playerService.striker();
            this.scoreBoardService.updateScore(striker, score);
            boolean endOfTheOver = this.scoreBoardService.isEndOfTheOver();
            playerService.takeAction(score, endOfTheOver);
            this.teamService.takeAction(score);
        } while (!this.scoreBoardService.isMatchFinish());
    }

}
