import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static Logger instance;
    private final String logFile = "application.log";

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    public void info(String message) { log("INFO", message); }
    public void error(String message) { log("ERROR", message); }

    private void log(String type, String message) {
        String record = String.format("%s [%s]: %s", LocalDateTime.now(), type, message);
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(record + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write to log file.");
        }
    }
}