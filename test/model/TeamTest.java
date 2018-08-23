package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team team;
    private final Player srinu = new Player("Srinu");
    private final Player kohli = new Player("Kohli");
    private final Player sachin = new Player("sachin");

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

}
