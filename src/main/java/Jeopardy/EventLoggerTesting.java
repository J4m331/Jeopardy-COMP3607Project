package Jeopardy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

class EventLoggerTest {
    private EventLogger logger;
    private static final String LOG_FILE = "game_event_log.csv";

    @BeforeEach
    void setUp() {
        logger = EventLogger.getEventLoggerInstance();
    }

    @Test
    void testSingletonPattern() {
        EventLogger logger1 = EventLogger.getEventLoggerInstance();
        EventLogger logger2 = EventLogger.getEventLoggerInstance();
        assertSame(logger1, logger2);
    }

    @Test
    void testLogFileCreation() {
        File logFile = new File(LOG_FILE);
        assertTrue(logFile.exists());
    }

    @Test
    void testLogEvent() throws Exception {
        LogEvent event = new LogEvent.Builder()
                .playerName("TestPlayer")
                .activity("Test Activity")
                .build();

        logger.logEvent(event);

        File logFile = new File(LOG_FILE);
        assertTrue(logFile.exists());
        assertTrue(logFile.length() > 0);
    }

    @Test
    void testLogFileHeaders() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String header = br.readLine();
            assertTrue(header.contains("Case_ID"));
            assertTrue(header.contains("Player_ID"));
            assertTrue(header.contains("Activity"));
            assertTrue(header.contains("Timestamp"));
        }
    }

    @Test
    void testMultipleLogEvents() throws Exception {
        long initialSize = new File(LOG_FILE).length();

        for (int i = 0; i < 5; i++) {
            LogEvent event = new LogEvent.Builder()
                    .playerName("Player" + i)
                    .activity("Activity" + i)
                    .build();
            logger.logEvent(event);
        }

        long finalSize = new File(LOG_FILE).length();
        assertTrue(finalSize > initialSize);
    }
}
