package mobots.mbot.model;

import java.util.Objects;
import java.util.StringJoiner;

public class GameIndex {
    private final int tournamentId;
    private final int gameId;

    public GameIndex(int tournamentId, int gameId) {

        this.tournamentId = tournamentId;
        this.gameId = gameId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameIndex)) return false;
        GameIndex gameIndex = (GameIndex) o;
        return tournamentId == gameIndex.tournamentId &&
                gameId == gameIndex.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, gameId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GameIndex.class.getSimpleName() + "[", "]")
                .add("tournamentId=" + tournamentId)
                .add("gameId=" + gameId)
                .toString();
    }
}
