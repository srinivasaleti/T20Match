package controller;

import model.Player;
import model.ScoreBoard;
import model.Team;
import service.MatchService;

import java.util.List;

public class GameController {

    private final ScoreBoard scoreBoard;
    private final int totalOvers;
    private final int requiredScore;
    private final MatchService matchService;
    private final Team team;

    public GameController(int totalOvers, int requiredScore, Team team, ScoreBoard scoreBoard, MatchService matchService) {
        this.scoreBoard = scoreBoard;
        this.totalOvers = totalOvers;
        this.requiredScore = requiredScore;
        this.matchService = matchService;
        this.team = team;
    }

    public void start() {
        scoreBoard.setTotalOvers(this.totalOvers);
        scoreBoard.setRequiredScore(this.requiredScore);
        List<Player> players = this.team.players();
        scoreBoard.setTotalWickets(players.size() - 1);

        this.matchService.start();
    }
}
