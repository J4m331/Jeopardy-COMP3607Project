package Jeopardy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class GameManagerTest {
    private GameManager gameManager;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
        players = new ArrayList<>();
        players.add(new Player(1, "Alice"));
        players.add(new Player(2, "Bob"));
        players.add(new Player(3, "Charlie"));
    }

    @Test
    void testAddPlayers() {
        gameManager.addPlayers(players);
        assertEquals(3, gameManager.getPlayers().size());
        assertEquals("Alice", gameManager.getCurrentPlayer().getName());
    }

    @Test
    void testUpdateScore() {
        gameManager.addPlayers(players);
        Player firstPlayer = gameManager.getCurrentPlayer();
        
        gameManager.UpdateScore(100);
        
        assertEquals(100, firstPlayer.getScore());
        assertNotEquals(firstPlayer, gameManager.getCurrentPlayer());
    }

    @Test
    void testShiftPlayer() {
        gameManager.addPlayers(players);
        
        assertEquals("Alice", gameManager.getCurrentPlayer().getName());
        gameManager.shiftPlayer();
        assertEquals("Bob", gameManager.getCurrentPlayer().getName());
        gameManager.shiftPlayer();
        assertEquals("Charlie", gameManager.getCurrentPlayer().getName());
        gameManager.shiftPlayer();
        assertEquals("Alice", gameManager.getCurrentPlayer().getName());
    }

    @Test
    void testPlayerRotation() {
        gameManager.addPlayers(players);
        
        for (int i = 0; i < 10; i++) {
            gameManager.shiftPlayer();
        }
        
        assertEquals("Bob", gameManager.getCurrentPlayer().getName());
    }
}
