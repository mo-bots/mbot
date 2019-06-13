package mobots.mbot.logic.context;

import mobots.mbot.logic.GameDelegate;

public class Input {

    private final GameDelegate gameDelegate;

    public Input(GameDelegate gameDelegate) {

        this.gameDelegate = gameDelegate;
    }

    public GameDelegate getGameDelegate() {
        return gameDelegate;
    }
}
