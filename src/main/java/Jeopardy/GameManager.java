package Jeopardy;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static GameManager gmInstance;
    private List<Player> players;

    public GameManager(){
        this.players = new ArrayList<>();
    }

    public static GameManager getGmInstance(){
        if(gmInstance == null)
            gmInstance = new GameManager();
        return gmInstance;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
