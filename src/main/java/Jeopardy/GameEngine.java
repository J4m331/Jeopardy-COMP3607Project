package Jeopardy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final List<Player> players;
    private final List<Category> categories;
    private final EventLogger logger = EventLogger.getLgInstance();

    private int currentPlayerIndex = 0;
    private final List<String> sessionRundown = new ArrayList<>();

    public GameEngine(List<Player> players, List<Category> categories) {
        this.players = players;
        this.categories = categories;

        log("Start Game");
        log("Load File");
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    public void advanceTurn() {
        currentPlayerIndex = currentPlayerIndex + 1;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }

    public void executePlay(Player player, Category category, Question question, char givenAnswer) {

        question.markAnswered();

        boolean correct;
        if (Character.toUpperCase(givenAnswer) == Character.toUpperCase(question.getCorrectAnswer())) {
            correct = true;
        } else {
            correct = false;
        }

        int points = question.getScore();
        int delta;
        if (correct) {
            delta = points;
        } else {
            delta = -points;
        }

        player.addToScore(delta);

        String result;
        if (correct) {
            result = "CORRECT";
        } else {
            result = "INCORRECT";
        }

        // Logging
        log("Player " + player.getName() + " selected category '" + category.getName() + "'");
        log("Player " + player.getName() + " selected question value " + points + " in '" + category.getName() + "'");
        log("Player " + player.getName() + " answered '" + givenAnswer + "' → " + result + " (Score now " + player.getScore() + ")");

        // Rundown entry
        sessionRundown.add(
            "Player=" + player.getName() +
            " | Category=" + category.getName() +
            " | Value=" + points +
            " | Q=\"" + question.getQuestion() +
            "\" | Given=" + givenAnswer +
            " | Result=" + result +
            " | ScoreAfter=" + player.getScore()
        );

        // Feedback popup
        JOptionPane.showMessageDialog(
            null,
            player.getName() + " answered " + result +
                    "\nPoints: " + (delta >= 0 ? "+" + delta : delta) +
                    "\nTotal: " + player.getScore(),
            "Answer Result",
            JOptionPane.INFORMATION_MESSAGE
        );

        advanceTurn();
    }

    public boolean allQuestionsAnswered() {
        for (Category c : categories) {
            if (!c.allAnswered()) {
                return false;
            }
        }
        return true;
    }

    public void generateReportAndFinish() {

        ReportGenerator.generateTextReport(
                "jeopardy_summary.txt",
                players,
                String.join("\n", sessionRundown)
        );

        log("Generate Report");
        log("Generate Event Log");
        log("Exit Game");

        JOptionPane.showMessageDialog(
                null,
                "Report (jeopardy_summary.txt) and log (game_event_log.csv) generated.",
                "Game Finished",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // === Simple String Logger Shortcut ===
    private void log(String activity) {
        logger.logEvent(
            new LogEvent.Builder()
                .activity(activity)
                .result("OK")
                .build()
        );
    }
}
