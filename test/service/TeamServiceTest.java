package service;

import model.Player;
import model.Score;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TeamServiceTest {

    private TeamService teamService;
    private PlayerService playerService;
    private Team team;

    private final Player srinu = new Player("Srinu");
    private final Player ramu = new Player("ramu");
    private final Player kohli = new Player("Kohli");

    @BeforeEach
    void setUp() {
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

        this.teamService.takeAction(Score.OUT);

        verify(team).hasNext();
    }

    @Test
    public void shouldGetTheNextStrikerWhenStrikerIsOut() {
        when(team.hasNext()).thenReturn(true);

        this.teamService.takeAction(Score.OUT);

        verify(team, times(1)).next();
    }

    @Test
    public void shouldChangeTheStrikerWhenStrikerIsOut() {
        when(team.hasNext()).thenReturn(true);
        when(team.hasAtLeastTwoBatsMen()).thenReturn(true);
        when(team.next()).thenReturn(srinu).thenReturn(kohli).thenReturn(ramu);

        this.teamService.assignStrikerAndNonStriker();
        this.teamService.takeAction(Score.OUT);

        verify(playerService).setStriker(ramu);
    }

    @Test
    public void shouldNotChangeTheStrikerWhenStrikerIsNotOut() {
        when(team.hasNext()).thenReturn(true);

        this.teamService.takeAction(Score.ONE);

        verify(playerService, never()).setStriker(any());
    }

    @Test
    public void shouldNotChangeTheStrikerWhenStrikerIsOutAndThereIsNoNextBatsMen() {
        when(team.hasNext()).thenReturn(false);

        this.teamService.takeAction(Score.ONE);

        verify(playerService, never()).setStriker(any());
    }
}