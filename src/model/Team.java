package model;

import java.util.Iterator;
import java.util.List;

public class Team implements Iterator<Player> {

    private final List<Player> players;
    private int currentPlayerIndex = 0;

    public Team(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return currentPlayerIndex < players.size();
    }

    @Override
    public Player next() {
        Player currentPlayer = this.players.get(currentPlayerIndex);
        currentPlayerIndex++;
        return currentPlayer;
    }

    public boolean hasAtLeastTwoBatsMen() {
        return this.players.size() >= 2;
    }

    public List<Player> players() {
        return players;
    }

}
