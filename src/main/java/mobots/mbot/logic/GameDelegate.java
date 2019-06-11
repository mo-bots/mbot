package mobots.mbot.logic;

import mobots.mbot.model.*;
import mobots.mbot.util.ApiUtil;
import mobots.mbot.util.PathFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDelegate {


    private GameIndex gameIndex;
    private Player active;
    private Player enemy;
    private GameState gameState;
    private GameSettings gameSettings;
    private List<Action> actions = new ArrayList<>();

    @Autowired
    PathFinderUtil pathFinder;

    public GameDelegate(GameIndex gameIndex) {
        this.gameIndex = gameIndex;
    }

    public GameIndex getGameIndex() {
        return gameIndex;
    }

    public GameOrder getOrder(GameState gameState) {
        GameOrder gameOrder = new GameOrder();

        setGameState(gameState);
        setActivePlayer();
        setMapAndGameSettings();
        clearActions();
        executeOrders();
        setActions(gameOrder);

        return gameOrder;
    }

    private void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private void setActivePlayer() {
        if (gameState.getPlayer1().isActive()) {
            active = gameState.getPlayer1();
            enemy = gameState.getPlayer2();
        } else {
            active = gameState.getPlayer2();
            enemy = gameState.getPlayer1();
        }
    }

    private void setMapAndGameSettings() {
        if (gameState.isFirstTurn()) {
            pathFinder.setMap(ApiUtil.getMap());
            gameSettings = ApiUtil.getGameSettings();
        }
    }

    private void clearActions() {
        actions.clear();
    }

    private void setActions(GameOrder gameOrder) {
        gameOrder.setActions(actions);
    }

    private void executeOrders() {
        if (hasUnit("WORKER")) {
            goToRandomMine();
        } else {
            recruitUnit("W");
        }

    }

    private boolean hasUnit(String unitName) {
        return active.getUnits().stream().anyMatch(unit -> unit.getName().equals(unitName));
    }

    private Unit getUnit(String unitName) {
        return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).findFirst().get();
    }

    private void goToRandomMine() {
        Unit worker = getUnit("WORKER");
        int randIndex = new Random().nextInt(gameState.getMines().size() - 1);
        Coordinates randMine = gameState.getMines().get(randIndex).getCoordinates();
        List<String> movesList = pathFinder.getMovesList(worker.getCoordinates(), randMine);

        while (worker.getActionPoints() > 0) {
            if (movesList.size() > 0) {
                actions.add(new Action(worker.getId(), "MOVE", movesList.get(0)));
                movesList.remove(0);
            }
            worker.setActionPoints(worker.getActionPoints() - 1);
        }
    }

    private void recruitUnit(String unitType) {
        actions.add(new Action(active.getBase().getId(), "RECRUIT", unitType));
    }
}
