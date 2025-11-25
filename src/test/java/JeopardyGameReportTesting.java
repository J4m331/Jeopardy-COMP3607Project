package Jeopardy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class JeopardyGameReportTest {
    private JeopardyGameReport report;
    private List<Player> players;
    private static final String REPORT_FILE = "game_report.txt";

    @BeforeEach
    void setUp() {
        report = new JeopardyGameReport();
        players = new ArrayList<>();
        players.add(new Player(1, "Alice"));
        players.add(new Player(2, "Bob"));
        players.add(new Player(3, "Charlie"));
        
        //Giving players scores
        players.get(0).addToScore(500);
        players.get(1).addToScore(300);
        players.get(2).addToScore(700);
    }

    @AfterEach
    void cleanup() {
        File file = new File(REPORT_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testGenerateReport() {
        assertDoesNotThrow(() -> report.generateReport(players));
    }

    @Test
    void testReportFileCreated() {
        report.generateReport(players);
        File reportFile = new File(REPORT_FILE);
        assertTrue(reportFile.exists());
    }

    @Test
    void testReportContainsAllPlayers() throws Exception {
        report.generateReport(players);
        
        try (BufferedReader br = new BufferedReader(new FileReader(REPORT_FILE))) {
            String content = br.lines().reduce("", (a, b) -> a + b);
            assertTrue(content.contains("Alice"));
            assertTrue(content.contains("Bob"));
            assertTrue(content.contains("Charlie"));
        }
    }

    @Test
    void testReportContainsScores() throws Exception {
        report.generateReport(players);
        
        try (BufferedReader br = new BufferedReader(new FileReader(REPORT_FILE))) {
            String content = br.lines().reduce("", (a, b) -> a + b);
            assertTrue(content.contains("500"));
            assertTrue(content.contains("300"));
            assertTrue(content.contains("700"));
        }
    }

    @Test
    void testReportIdentifiesWinner() throws Exception {
        report.generateReport(players);
        
        try (BufferedReader br = new BufferedReader(new FileReader(REPORT_FILE))) {
            String content = br.lines().reduce("", (a, b) -> a + b);
            
            assertTrue(content.toLowerCase().contains("winner") || 
                      content.toLowerCase().contains("first"));
        }//Charlie has the highest score (700)
    }

    @Test
    void testReportWithTiedScores() {
        players.get(0).addToScore(200); // Alice now has 700
        report.generateReport(players);
        
        File reportFile = new File(REPORT_FILE);
        assertTrue(reportFile.exists());
    }

    @Test
    void testReportWithNegativeScores() {
        players.add(new Player(4, "Dave"));
        players.get(3).addToScore(-100);
        
        report.generateReport(players);
        
        File reportFile = new File(REPORT_FILE);
        assertTrue(reportFile.exists());
    }

    @Test
    void testReportWithSinglePlayer() {
        List<Player> singlePlayer = new ArrayList<>();
        singlePlayer.add(new Player(1, "Solo"));
        singlePlayer.get(0).addToScore(1000);
        
        assertDoesNotThrow(() -> report.generateReport(singlePlayer));
    }

    @Test
    void testReportFileNotEmpty() {
        report.generateReport(players);
        
        File reportFile = new File(REPORT_FILE);
        assertTrue(reportFile.length() > 0);
    }
}
