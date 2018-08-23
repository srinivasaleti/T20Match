package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommentaryServiceTest {

    private static String BALL_FORMAT = "%d.%d %s scores %d run\n";

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

}
