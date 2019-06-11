package mobots.mbot.service;

import mobots.mbot.logic.GameDelegate;
import mobots.mbot.model.GameIndex;
import mobots.mbot.model.GameOrder;
import mobots.mbot.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;

	private List<GameDelegate> gameDelegates = new LinkedList<>();
	private Logger logger = LoggerFactory.getLogger(GameService.class);

	public GameOrder getOrder(GameState gameState) {
		Objects.requireNonNull(gameState);
		logger.info("Get order for tournament {} gameId {}", gameState.getTournamentId(), gameState.getGameId());
		final GameDelegate gameDelegate = findOrCrateGameDelegate(new GameIndex(gameState.getTournamentId(), gameState.getGameId()));
		return gameDelegate.getOrder(gameState);
	}

	private GameDelegate findOrCrateGameDelegate(GameIndex gameIndex) {
		final Optional<GameDelegate> existingDelegate = findDelegate(gameIndex);
		return existingDelegate.orElseGet(() -> createDelegate(gameIndex));
	}

	private Optional<GameDelegate> findDelegate(GameIndex gameIndex) {
		return gameDelegates.stream().filter(d -> d.getGameIndex().equals(gameIndex)).findFirst();
	}

	private GameDelegate createDelegate(GameIndex gameIndex) {
		final GameDelegate newDelegate = new GameDelegate(gameIndex);
		gameDelegates.add(newDelegate);
		autowireCapableBeanFactory.autowireBean(newDelegate);
		return newDelegate;
	}
}
