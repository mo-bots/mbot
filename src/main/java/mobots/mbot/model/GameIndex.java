package mobots.mbot.model;

import java.util.Objects;
import java.util.StringJoiner;

public class GameIndex {
    private final int tournamentId;
    private final int gameId;
    private final String activePlayer;

    public GameIndex(int tournamentId, int gameId, String activePlayer) {
        this.tournamentId = tournamentId;
        this.gameId = gameId;
        this.activePlayer =activePlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameIndex)) return false;
        GameIndex gameIndex = (GameIndex) o;
        return tournamentId == gameIndex.tournamentId &&
                gameId == gameIndex.gameId &&
                Objects.equals(activePlayer, gameIndex.activePlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, gameId, activePlayer);
    }

    @Override
    public String toString() {
        return "GameIndex{" +
                "tournamentId=" + tournamentId +
                ", gameId=" + gameId +
                ", activePlayer='" + activePlayer + '\'' +
                '}';
    }
}
