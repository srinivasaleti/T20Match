package context;

import model.Player;
import model.ScoreBoard;
import model.Team;
import service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ContextHolder {

    private static ScoreBoard scoreBoard;
    private static RandomScoreGeneratorService randomScoreGeneratorService;
    private static ScoreBoardService scoreBoardService;
    private static CommentaryService commentaryService;
    private static PlayerService playerService;
    private static MatchService matchService;
    private static TeamService teamService;

    public static Team getTeam() {
        List<Integer> kiratBoliScoreWeights = Arrays.asList(5, 5, 30, 25, 10, 15, 1, 9);
        List<Integer> nsShodhiScoreWeights = Arrays.asList(10, 10, 40, 20, 5, 10, 1, 4);
        List<Integer> rRumhraScoreWeights = Arrays.asList(20, 20, 30, 15, 5, 5, 1, 4);
        List<Integer> sashiSharamScoreWeights = Arrays.asList(30, 30, 25, 5, 0, 5, 1, 4);
        Player kiratBoli = new Player("Kirat Boli", kiratBoliScoreWeights);
        Player NsShodhi = new Player("N.S Shodhi", nsShodhiScoreWeights);
        Player rRumhrah = new Player("R Rumhrah", rRumhraScoreWeights);
        Player hashiSharma = new Player("Sashi Sharma", sashiSharamScoreWeights);
        List<Player> playerList = Arrays.asList(kiratBoli, NsShodhi, rRumhrah, hashiSharma);
        return new Team(playerList);
    }

    public static ScoreBoard scoreBoard() {
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public static ScoreBoardService scoreBoardService() {
        if (scoreBoardService == null) {
            scoreBoardService = new ScoreBoardService(scoreBoard());
        }
        return scoreBoardService;
    }

    public static RandomScoreGeneratorService randomScoreGeneratorService() {
        if (randomScoreGeneratorService == null) {
            Random random = new Random();
            randomScoreGeneratorService = new RandomScoreGeneratorService(random);
        }
        return randomScoreGeneratorService;
    }

    public static CommentaryService commentaryService() {
        if (commentaryService == null) {
            commentaryService = new CommentaryService(System.out);
        }
        return commentaryService;
    }

    public static PlayerService playerService() {
        if (playerService == null) {
            playerService = new PlayerService(randomScoreGeneratorService());
        }
        return playerService;
    }

    public static TeamService teamService(Team team) {
        if (teamService == null) {
            teamService = new TeamService(team, playerService());
        }
        return teamService;
    }

    public static MatchService matchService(Team team) {
        if (matchService == null) {
            matchService = new MatchService(scoreBoardService(), playerService(), teamService(team), commentaryService());
        }
        return matchService;
    }
}
