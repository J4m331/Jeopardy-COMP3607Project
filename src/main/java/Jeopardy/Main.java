package Jeopardy;

import java.util.List;

public class Main {
    public static void main(String[] args){
        EventLogger eL = EventLogger.getElInstance();
        List<Category> categories = new FileChooserPromptWindow().getCategories();
        List<Player> players = new AddPlayersPromptWindow().getPlayers();
        GameManager gM = new GameManager();
        gM.addPlayers(players);

        new MainGameFrame(categories, gM);

    }
}