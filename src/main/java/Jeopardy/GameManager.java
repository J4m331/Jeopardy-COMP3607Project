package Jeopardy;

import java.util.ArrayList;
import java.util.List;

//GameManager class to manage players in the game using Singleton pattern
public class GameManager {
    private static GameManager gmInstance;
    private List<Player> players = new ArrayList<>();;

    private GameManager(){}

    public static GameManager getGmInstance(){
        if(gmInstance == null)
            gmInstance = new GameManager();
        return gmInstance;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void clearPlayers(){
        this.players.clear();
    }
}
