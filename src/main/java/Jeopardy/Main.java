package Jeopardy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        // Load categories from CSV
        List<Category> categories = Jeopardy.CSVInput.createCategories("src/main/java/Jeopardy/sample_game_CSV.csv");
        if (categories.isEmpty()){
            System.err.println("No categories loaded. Check CSV path and format.");
            System.exit(1);
        }

        // simple player setup dialog (1-4 players)
        List<Player> players = promptForPlayers();
        if (players.isEmpty()) System.exit(0);

        // start UI
        SwingUtilities.invokeLater(() -> new MainGameFrame(categories, players));
    }

    private static List<Player> promptForPlayers(){
        List<Player> players = new ArrayList<>();
        JPanel input = new JPanel();
        
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        
        String[] options = {"1","2","3","4"};
        String count = (String) JOptionPane.showInputDialog(null, "How many players? (1-4)", "Players", JOptionPane.PLAIN_MESSAGE, null, options, "2");
        
        if (count == null) 
            return players;
        
        int n = Integer.parseInt(count);
        
        for (int i = 1; i <= n; i++){
            String name = JOptionPane.showInputDialog(
                null, 
                "Enter name for player " + i, 
                "Player Name", 
                JOptionPane.QUESTION_MESSAGE
            );

            if (name == null || name.trim().isEmpty()){
                name = "Player" + i;
            }
            
            Player p = new Player(i, name.trim());
            players.add(p);
            
            // Add player to GameManager
            GameManager.getGmInstance().addPlayer(p);
            
            // Log player creation
            EventLogger.getLgInstance().logEvent(
                    new LogEvent.Builder()
                            .playerId(p.getId())
                            .activity("Enter Player Name")
                            .category("")
                            .questionValue(0)
                            .answerGiven("")
                            .result("OK")
                            .scoreAfterPlay(p.getName())
                            .build()
            );
        }
        
        // Log total player count
        EventLogger.getLgInstance().logEvent(
                new LogEvent.Builder()
                        .playerId(-1) // system event
                        .activity("Select Player Count")
                        .category("")
                        .questionValue(n)
                        .answerGiven("")
                        .result("OK")
                        .scoreAfterPlay("")
                        .build()
        );

        return players;
    }
}
