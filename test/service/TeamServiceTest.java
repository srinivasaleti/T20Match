package service;

import model.Player;
import model.Score;
import model.ScoreBoard;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class TeamServiceTest {

    private TeamService teamService;
    private PlayerService playerService;
    private ScoreBoard scoreBoard;
    private Team team;

    private final List<Integer> srinuScoreWieghts = mock(List.class);
    private final List<Integer> ramuScoreWieghts = mock(List.class);
    private final List<Integer> koliScoreWieghts = mock(List.class);
    private final Player srinu = new Player("Srinu", srinuScoreWieghts);
    private final Player ramu = new Player("ramu", ramuScoreWieghts);
    private final Player kohli = new Player("Kohli", koliScoreWieghts);

    @BeforeEach
    void setUp() {
        this.scoreBoard = mock(ScoreBoard.class);
        this.team = mock(Team.class);
        this.playerService = mock(PlayerService.class);
        this.teamService = new TeamService(team, playerService);
    }

    @Test
    void shouldAssignStrikerAndNonStrikerIfTeamHasAtLeastTwoBatsMen() {
        when(this.team.hasAtLeastTwoBatsMen()).thenReturn(true);
        when(this.team.next()).thenReturn(srinu).thenReturn(ramu);

        this.teamService.assignStrikerAndNonStriker();

        verify(this.playerService).setStriker(srinu);
        verify(this.playerService).setNonStriker(ramu);
    }

    @Test
    void shouldNotAssignStrikerAndNonStrikerIfTeamDoNotHaveAtLeastTwoBatsMen() {
        when(this.team.hasAtLeastTwoBatsMen()).thenReturn(false);

        this.teamService.assignStrikerAndNonStriker();

        verify(this.playerService, never()).setStriker(any());
        verify(this.playerService, never()).setNonStriker(any());
    }

    @Test
    public void shouldAskTeamToKnowWhetherNextBatsMenIsAvailableOrNotWhenStrikerIsOut() {
        when(team.hasNext()).thenReturn(false);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.OUT);

        this.teamService.takeActionBasedOn(scoreBoard);

        verify(team).hasNext();
    }

    @Test
    public void shouldGetTheNextStrikerWhenStrikerIsOut() {
        when(team.hasNext()).thenReturn(true);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.OUT);

        this.teamService.takeActionBasedOn(scoreBoard);

        verify(team, times(1)).next();
    }

    @Test
    public void shouldChangeTheStrikerWhenStrikerIsOut() {
        when(team.hasNext()).thenReturn(true);
        when(team.hasAtLeastTwoBatsMen()).thenReturn(true);
        when(team.next()).thenReturn(srinu).thenReturn(kohli).thenReturn(ramu);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.OUT);

        this.teamService.assignStrikerAndNonStriker();
        this.teamService.takeActionBasedOn(scoreBoard);

        verify(playerService).setStriker(ramu);
    }

    @Test
    public void shouldNotChangeTheStrikerWhenStrikerIsNotOut() {
        when(team.hasNext()).thenReturn(true);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.ONE);

        this.teamService.takeActionBasedOn(scoreBoard);

        verify(playerService, never()).setStriker(any());
    }

    @Test
    public void shouldNotChangeTheStrikerWhenStrikerIsOutAndThereIsNoNextBatsMen() {
        when(team.hasNext()).thenReturn(false);
        when(scoreBoard.getCurrentBallStatus()).thenReturn(Score.OUT);

        this.teamService.takeActionBasedOn(scoreBoard);

        verify(playerService, never()).setStriker(any());
    }
}