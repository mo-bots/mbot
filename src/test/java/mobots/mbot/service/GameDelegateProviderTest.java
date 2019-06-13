package mobots.mbot.service;

import mobots.mbot.logic.GameDelegate;
import mobots.mbot.model.GameIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDelegateProviderTest {

    @Autowired
    GameDelegateProvider gameDelegateProvider;

    @Test
    public void whenGetFirstGameOrderThenCreateOne() {
        GameDelegate gameDelegate = gameDelegateProvider.findOrCrateGameDelegate(new GameIndex(1, 1,"1"));
        assertNotNull(gameDelegate);
    }

    @Test
    public void whenGetTwoGameOrderThenReturnExisting() {
        GameDelegate gameDelegate1 = gameDelegateProvider.findOrCrateGameDelegate(new GameIndex(0, 1,"1"));
        assertNotNull(gameDelegate1);
        GameDelegate gameDelegate2 = gameDelegateProvider.findOrCrateGameDelegate(new GameIndex(0, 1,"1"));
        assertNotNull(gameDelegate2);
        assertEquals(gameDelegate1, gameDelegate2);
    }

    @Test(expected = NullPointerException.class)
    public void whenGameStateIsNullThrowException() {
        gameDelegateProvider.findOrCrateGameDelegate(null);
    }

}