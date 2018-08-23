package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CommentaryServiceTest {

    private static String BALL_FORMAT = "%d.%d %s scores %d run\n";
    private static String OUT_FORMAT = "%d.%d %s is out\n";
    private static String END_OF_OVER_FORMAT = "%d overs left. %d runs to win\n";
    private static String NON_OUT_BATSMEN_RUNS_FORMAT = "%s - %d* (%d balls)";
    private static String OUT_BATSMEN_RUNS_FORMAT = "%s - %d (%d balls)";

    private CommentaryService commentaryService;
    private ScoreBoard scoreBoard;
    private Player player;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        this.printStream = mock(PrintStream.class);
        this.player = new Player("Srinu");
        this.scoreBoard = new ScoreBoard();
        this.commentaryService = new CommentaryService(printStream);
    }

    @Test
    void shouldAnnounceSingleRunInProperFormat() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setNoOfBallsFaced(5);
        scoreBoard.setCurrentBallStatus(Score.ONE);

        this.commentaryService.announce(scoreBoard);

        verify(printStream).printf(BALL_FORMAT, 0, 5, "Srinu", 1);
    }

    @Test
    void shouldAnnounceTwoRunInProperFormat() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setNoOfBallsFaced(5);
        scoreBoard.setCurrentBallStatus(Score.TWO);

        this.commentaryService.announce(scoreBoard);

        verify(printStream).printf(BALL_FORMAT, 0, 5, "Srinu", 2);
    }

    @Test
    void shouldAnnounceOutInProperFormat() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setNoOfBallsFaced(5);
        scoreBoard.setCurrentBallStatus(Score.OUT);

        this.commentaryService.announce(scoreBoard);

        verify(printStream).printf(OUT_FORMAT, 0, 5, "Srinu");
    }

    @Test
    void shouldNotAnnounceRunIfCurrentScoreStatusIsOut() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setNoOfBallsFaced(5);
        scoreBoard.setCurrentBallStatus(Score.OUT);

        this.commentaryService.announce(scoreBoard);

        verify(printStream, never()).printf(BALL_FORMAT, 0, 5, "Srinu", 2);
    }


    @Test
    void shouldAnnounceRemainingRunsAndOverAtEndOfTheOver() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setRequiredScore(10);
        scoreBoard.setNoOfBallsFaced(6);
        scoreBoard.setCurrentBallStatus(Score.OUT);
        scoreBoard.setTotalOvers(2);
        scoreBoard.setCurrentScore(6);

        this.commentaryService.announce(scoreBoard);

        verify(printStream).printf(OUT_FORMAT, 1, 0, "Srinu");
        verify(printStream).println();
        verify(printStream).printf(END_OF_OVER_FORMAT, 1, 4);
    }

    @Test
    void shouldNotAnnounceRemainingRunsAndOversAtMiddleOfOver() {
        scoreBoard.setCurrentPlayer(player);
        scoreBoard.setRequiredScore(10);
        scoreBoard.setNoOfBallsFaced(5);
        scoreBoard.setCurrentBallStatus(Score.TWO);
        scoreBoard.setTotalOvers(2);
        scoreBoard.setCurrentScore(6);

        this.commentaryService.announce(scoreBoard);

        verify(printStream).printf(BALL_FORMAT, 0, 5, "Srinu", 2);
        verify(printStream, never()).println();
        verify(printStream, never()).printf(END_OF_OVER_FORMAT, 1, 4);
    }

    @Test
    void shouldAnnounceWinByWicketsWhenTeamWin() {
        scoreBoard.setCurrentScore(41);
        scoreBoard.setTotalWickets(3);
        scoreBoard.setNoOfOuts(2);
        scoreBoard.setRequiredScore(40);

        this.commentaryService.announceResults(scoreBoard);

        verify(printStream).println("Won By 2 Wickets");
    }

    @Test
    void shouldAnnounceLooseByRunsWhenTeamLoose() {
        scoreBoard.setCurrentScore(21);
        scoreBoard.setTotalWickets(3);
        scoreBoard.setNoOfOuts(2);
        scoreBoard.setRequiredScore(40);

        this.commentaryService.announceResults(scoreBoard);

        verify(printStream).println("Loose By 19 Runs");
    }

    @Test
    void shouldNotAnnounceLoosByRunsWhenTeamWin() {
        scoreBoard.setCurrentScore(41);
        scoreBoard.setTotalWickets(3);
        scoreBoard.setNoOfOuts(2);
        scoreBoard.setRequiredScore(40);

        this.commentaryService.announceResults(scoreBoard);

        verify(printStream).println("Won By 2 Wickets");
        verify(printStream, never()).println("Loose By -1 Runs");
    }

    @Test
    void shouldAnnounceMatchAsDraw() {
        scoreBoard.setCurrentScore(39);
        scoreBoard.setTotalWickets(3);
        scoreBoard.setNoOfOuts(2);
        scoreBoard.setRequiredScore(40);

        this.commentaryService.announceResults(scoreBoard);

        verify(printStream).println("Match Draw");
    }

    @Test
    void shouldAnnounceEachPlayerScore() {
        Player kiratBoli = new Player("Kirat Boli");
        Player nsShodhi = new Player("N.S Shodhi");
        Player rRumhrah = new Player("R Rumhrah");
        List<Player> playerList = Arrays.asList(kiratBoli, nsShodhi, rRumhrah);
        Team team = new Team(playerList);

        kiratBoli.setBallsFaced(10);
        kiratBoli.updateScore(10);
        kiratBoli.goesToPavilions();

        nsShodhi.setBallsFaced(5);
        nsShodhi.updateScore(5);

        this.commentaryService.announceEachPlayerScoreInTeam(team);

        verify(printStream).printf(OUT_BATSMEN_RUNS_FORMAT, kiratBoli.getName(), kiratBoli.getScore(), kiratBoli.ballsFaced());
        verify(printStream, never()).printf(NON_OUT_BATSMEN_RUNS_FORMAT, kiratBoli.getName(), kiratBoli.getScore(), kiratBoli.ballsFaced());
        verify(printStream).printf(NON_OUT_BATSMEN_RUNS_FORMAT, nsShodhi.getName(), nsShodhi.getScore(), nsShodhi.ballsFaced());
        verify(printStream, never()).printf(OUT_BATSMEN_RUNS_FORMAT, nsShodhi.getName(), nsShodhi.getScore(), nsShodhi.ballsFaced());
        verify(printStream).printf(NON_OUT_BATSMEN_RUNS_FORMAT, rRumhrah.getName(), rRumhrah.getScore(), rRumhrah.ballsFaced());
        verify(printStream, never()).printf(OUT_BATSMEN_RUNS_FORMAT, rRumhrah.getName(), rRumhrah.getScore(), rRumhrah.ballsFaced());
    }

}
