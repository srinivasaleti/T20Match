package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeamTest {

    private Team team;
    private final Player srinu = mock(Player.class);
    private final Player kohli = mock(Player.class);
    private final Player sachin = mock(Player.class);

    private List<Player> players = Arrays.asList(srinu, kohli, sachin);

    @BeforeEach
    void setUp() {
        this.team = new Team(players);
    }

    @Test
    public void shouldGetThePlayersOneAfterOther() {
        assertEquals(srinu, team.next());
        assertEquals(kohli, team.next());
        assertEquals(sachin, team.next());
    }

    @Test
    public void shouldReturnTrueWhenAllPlayersInTheTeamNotYetPlayed() {
        team.next();
        team.next();

        assertTrue(team.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenAllPlayersInTheTeamPlayed() {
        team.next();
        team.next();
        team.next();

        assertFalse(team.hasNext());
    }

    @Test
    public void shouldReturnTrueIfTeamHasAtLeastTwoBatsMen() {
        List<Player> players = Arrays.asList(srinu, kohli, sachin);
        Team team = new Team(players);

        assertTrue(team.hasAtLeastTwoBatsMen());
    }

    @Test
    public void shouldReturnFalseIfTeamDoNotHaveAtLeastTwoBatsMen() {
        List<Player> players = Collections.singletonList(srinu);
        Team team = new Team(players);

        assertFalse(team.hasAtLeastTwoBatsMen());
    }

    @Test
    public void shouldReturnListOfPlayers() {
        assertEquals(players, team.players());
    }

}
