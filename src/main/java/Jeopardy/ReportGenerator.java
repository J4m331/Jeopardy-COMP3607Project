package Jeopardy;

import java.io.FileWriter;
import java.util.List;

// Generates a simple TXT summary report containing final scores and turn-by-turn log summary.
public class ReportGenerator {

    public static void generateTextReport(String path, List<Player> players, String sessionSummary) {
        try (FileWriter fw = new FileWriter(path, false)) {
            fw.append("Jeopardy Game Summary\n");
            fw.append("=====================\n\n");

            fw.append("Final Scores:\n");
            for (Player p : players) {
                fw.append(String.format("%s : %d\n", p.getName(), p.getScore()));
            }

            fw.append("\nSession Rundown:\n");
            if (sessionSummary == null || sessionSummary.isEmpty()) {
                fw.append("No rundown available.\n");
            } else {
                fw.append(sessionSummary);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}
