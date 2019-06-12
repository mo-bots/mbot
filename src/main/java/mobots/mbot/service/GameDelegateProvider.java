package mobots.mbot.service;

import mobots.mbot.logic.GameDelegate;
import mobots.mbot.model.GameIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jacola
 */
@Service
public class GameDelegateProvider {

    @Autowired
    AutowireCapableBeanFactory autowireCapableBeanFactory;

    private Logger logger = LoggerFactory.getLogger(GameDelegateProvider.class);

    private ConcurrentHashMap<GameIndex, GameDelegate> gameDelegates = new ConcurrentHashMap<>();

    public GameDelegate findOrCrateGameDelegate(@NotNull GameIndex gameIndex) {
        Objects.requireNonNull(gameIndex, "game index parameter can't be null");
        return gameDelegates.computeIfAbsent(gameIndex, this::createDelegate);
    }

    private GameDelegate createDelegate(GameIndex gameIndex) {
        logger.debug("going to cretate new game delegate for game index {}", gameIndex);
        final GameDelegate newDelegate = new GameDelegate(gameIndex);
        autowireCapableBeanFactory.autowireBean(newDelegate);
        return newDelegate;
    }

}
