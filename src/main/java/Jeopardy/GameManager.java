package Jeopardy;

import java.util.List;


public class GameManager implements ScoreObserver , ScoreUISubject{
    private static GameManager gmInstance;
    private List<Player> players;
    private Player currentPlayer;
    private int playerIndex;
    private int playerCount;
    private int questionCount;
    private ScoreUIObserver playerPanelUI;

    public GameManager(){
        this.players = null;
        this.questionCount = 0;
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

    public List<Player> getPlayers(){
        return this.players;
    }

    @Override
    public void UpdateScore(int score){
        currentPlayer.addToScore(score);
        shiftPlayer();
    }

    public void shiftPlayer(){
        playerIndex = playerIndex + 1;
        if(playerIndex >= playerCount)
            playerIndex = 0;
        currentPlayer = players.get(playerIndex);
        UpdateUI(currentPlayer);
        questionCount++;
        System.out.println(questionCount);
        if(questionCount > 24){
            ReportGenerator.generateTextReport("jeopardyGameReport.txt", players, "game_event_log.csv");
            System.exit(0);
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    @Override
    public void Update(){
        this.UpdateObservers();
    }

    @Override
    public void LinkObserver(Observer o) {
        this.playerPanelUI = (ScoreUIObserver) o;
    }

    @Override
    public void UnlinkObserver(Observer o) {

    }

    @Override
    public void UpdateObservers() {

    }

    @Override
    public void UpdateUI(Player currentPlayer) {
        this.playerPanelUI.UpdateUI(currentPlayer);
    }
}
