package Jeopardy;

import java.util.List;


public class GameManager implements ScoreObserver {
    private static GameManager gmInstance;
    private List<Player> players;
    private Player currentPlayer;
    private int playerIndex;
    private int playerCount;

    public GameManager(){
        this.players = null;
    }
/*
    public static GameManager getGmInstance(){
        if(gmInstance == null)
            gmInstance = new GameManager();
        return gmInstance;
    }*/

    public void addPlayers(List<Player> players){
        this.players = players;
        currentPlayer = this.players.getFirst();
        playerIndex = 0;
        playerCount = players.size();
    }

    @Override
    public void UpdateScore(int score){
        currentPlayer.addToScore(score);
        shiftPlayer();
    }

    public void shiftPlayer(){
        System.out.println(currentPlayer.getScore());
        playerIndex = playerIndex + 1;
        if(playerIndex >= playerCount)
            playerIndex = 0;
        currentPlayer = players.get(playerIndex);
        System.out.println(playerIndex);
        System.out.println(currentPlayer.getName());
    }

    @Override
    public void Update(){

    }
}
