package ncdc.bow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ncdc.bow.model.Action;
import ncdc.bow.model.Coordinates;
import ncdc.bow.model.GameOrder;
import ncdc.bow.model.GameSettings;
import ncdc.bow.model.GameState;
import ncdc.bow.model.Player;
import ncdc.bow.model.Unit;
import ncdc.bow.util.ApiUtil;
import ncdc.bow.util.PathFinderUtil;

@Service
public class GameService {

	Player active;
	Player enemy;
	GameState gameState;
	GameSettings gameSettings;
	List<Action> actions = new ArrayList<>();

	@Autowired
	PathFinderUtil pathFinder;

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

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setActivePlayer() {
		if (gameState.getPlayer1().isActive()) {
			active = gameState.getPlayer1();
			enemy = gameState.getPlayer2();
		} else {
			active = gameState.getPlayer2();
			enemy = gameState.getPlayer1();
		}
	}

	public void setMapAndGameSettings() {
		if (gameState.isFirstTurn()) {
			pathFinder.setMap(ApiUtil.getMap());
			gameSettings = ApiUtil.getGameSettings();
		}
	}

	public void clearActions() {
		actions.clear();
	}

	public void setActions(GameOrder gameOrder) {
		gameOrder.setActions(actions);
	}

	public void executeOrders() {
		if (hasUnit("WORKER")) {
			goToRandomMine();
		} else {
			recruitUnit("W");
		}

	}

	public boolean hasUnit(String unitName) {
		return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).count() > 0;
	}

	public Unit getUnit(String unitName) {
		return active.getUnits().stream().filter(unit -> unit.getName().equals(unitName)).findFirst().get();
	}

	public void goToRandomMine() {
		Unit worker = getUnit("WORKER");
		Integer randIndex = new Random().nextInt(gameState.getMines().size() - 1);
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

	public void recruitUnit(String unitType) {
		actions.add(new Action(active.getBase().getId(), "RECRUIT", unitType));
	}

}
