package mobots.mbot.logic;

import mobots.mbot.logic.context.ActorContext;
import mobots.mbot.logic.context.ContextConstructorVisitor;
import mobots.mbot.logic.context.Input;
import mobots.mbot.model.*;
import mobots.mbot.util.ApiUtil;
import mobots.mbot.util.PathFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.Map;

public class GameDelegate {


    private GameIndex gameIndex;
    private Player active;
    private Player enemy;
    private GameState gameState;
    private GameSettings gameSettings;
    private List<Action> actions = new ArrayList<>();

    private Map<String, ActorContext> actorStates = new HashMap<>();

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
        buildUnitStates();
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

    private void buildUnitStates() {
        actorStates.computeIfAbsent("B"+active.getBase().getId(), (id) -> this.computeUnitState(active.getBase()));
        List<ActorUnit> currentUnits = new LinkedList<>(active.getUnits());
        while (!currentUnits.isEmpty()) {
            final ActorUnit unit = currentUnits.remove(0);
            actorStates.computeIfAbsent("U"+unit.getId(), (id) -> this.computeUnitState(unit));
        }

    }

    private ActorContext computeUnitState(ActorUnit unit) {
        final ContextConstructorVisitor contextConstructorVisitor = new ContextConstructorVisitor();
        unit.accept(contextConstructorVisitor);
        return contextConstructorVisitor.getActorContext();
    }

    private void setActions(GameOrder gameOrder) {
        gameOrder.setActions(actions);
    }

    private void executeOrders() {

        final ArrayList<ActorContext> actorContexts = new ArrayList<>(actorStates.values());

        actorContexts.sort(Comparator
                .comparingInt((ActorContext c)->c.getActor().getOrder())
                .thenComparing((ActorContext c)->c.getActor().getId()));

        Input input = new Input(this);

        actorContexts.stream().forEachOrdered(c -> c.update(input));
    }

    private boolean hasUnit(UnitName unitName) {
        return active.getUnits().stream().anyMatch(unit -> unit.getName().equals(unitName));
    }

    private Unit getUnit(UnitName unitName) {
        return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).findFirst().get();
    }

    public void goToRandomMine(Unit worker) {
        int randIndex = new Random().nextInt(gameState.getMines().size() - 1);
        Coordinates randMine = gameState.getMines().get(randIndex).getCoordinates();
        List<String> movesList = pathFinder.getMovesList(worker.getCoordinates(), randMine);

        while (worker.getActionPoints() > 0) {
            if (movesList.size() > 0) {
                actions.add(new Action(worker.getId(), ActionType.MOVE, movesList.get(0)));
                movesList.remove(0);
            }
            worker.setActionPoints(worker.getActionPoints() - 1);
        }
    }

    public void recruitUnit(UnitName unitType) {
        actions.add(new Action(active.getBase().getId(), ActionType.RECRUIT, unitType.getShortcut()));
    }

    public Player getActive() {
        return active;
    }
}
