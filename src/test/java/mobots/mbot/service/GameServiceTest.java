package mobots.mbot.service;

import mobots.mbot.model.Base;
import mobots.mbot.model.GameOrder;
import mobots.mbot.model.GameState;
import mobots.mbot.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @Autowired
    GameService gameService;
    private GameState gameState;

    @Before
    public void createDummyGameState() {
        this.gameState = new GameState();
        this.gameState.setPlayer1(new Player());
        this.gameState.getPlayer1().setActive(true);
        this.gameState.getPlayer1().setUnits(new ArrayList<>());
        this.gameState.getPlayer1().setBase(new Base());
    }

    @Test
    public void whenGetFirstGameOrderThenCreateOne() {
        final GameOrder order = gameService.getOrder(gameState);
        assertNotNull(order);
    }

    @Test(expected = NullPointerException.class)
    public void whenGameStateIsNullThrowException() {
        final GameOrder order = gameService.getOrder(null);
        assertNotNull(order);
    }

}