package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

class CommentaryServiceTest {

    private static String BALL_FORMAT = "%d.%d %s scores %d run\n";
    private static String OUT_FORMAT = "%d.%d %s is out\n";
    private static String END_OF_OVER_FORMAT = "%d overs left. %d runs to win\n";

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
}
