package mobots.mbot.service;

import mobots.mbot.logic.GameDelegate;
import mobots.mbot.model.GameIndex;
import mobots.mbot.model.GameOrder;
import mobots.mbot.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameService {


	@Autowired
	GameDelegateProvider gameDelegateProvider;
	private Logger logger = LoggerFactory.getLogger(GameService.class);

	public GameOrder getOrder(GameState gameState) {
		Objects.requireNonNull(gameState);
		final GameIndex gameIndex = new GameIndex(gameState.getTournamentId(), gameState.getGameId());

		logger.info("Get order for game {}", gameIndex);
		final GameDelegate gameDelegate = gameDelegateProvider.findOrCrateGameDelegate(gameIndex);
		return gameDelegate.getOrder(gameState);
	}

}
