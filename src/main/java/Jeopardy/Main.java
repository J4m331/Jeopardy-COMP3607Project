package Jeopardy;

import java.util.List;

public class Main {
    public static void main(String[] args){
        EventLogger eventLogger = EventLogger.getEventLoggerInstance();

        FileChooserPromptWindow fileChooser = new FileChooserPromptWindow(eventLogger);
        List<Category> categories = fileChooser.getCategories();

        AddPlayersPromptWindow playersWindow = new AddPlayersPromptWindow(eventLogger);
        List<Player> players = playersWindow.getPlayers();

        GameManager gM = new GameManager();
        gM.addPlayers(players);

        new MainGameFrame(categories, gM, gM, eventLogger);

    }
}