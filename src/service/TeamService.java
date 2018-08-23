package service;

import model.Player;
import model.ScoreBoard;
import model.Team;

import static model.Score.OUT;

public class TeamService {

    private PlayerService playerService;
    private Team team;

    public TeamService(Team team, PlayerService playerService) {
        this.playerService = playerService;
        this.team = team;
    }

    public void assignStrikerAndNonStriker() {
        if (team.hasAtLeastTwoBatsMen()) {
            this.playerService.setStriker(team.next());
            this.playerService.setNonStriker(team.next());
        }
    }

    public void takeActionBasedOn(ScoreBoard scoreBoard) {
        boolean out = scoreBoard.getCurrentBallStatus() == OUT;
        if (out && this.team.hasNext()) {
            Player next = this.team.next();
            this.playerService.setStriker(next);
        }
    }

}
