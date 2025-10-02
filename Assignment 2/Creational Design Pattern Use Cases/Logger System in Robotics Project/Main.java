 class Logger {
    // Private static instance variable
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {}

    // Public method to get the single instance (thread-safe)
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Log method to print messages
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
 class MotorControl {
    public void moveForward() {
        Logger.getInstance().log("Motor moving forward.");
    }

    public void stop() {
        Logger.getInstance().log("Motor stopped.");
    }
}

 class SensorModule {
    public void readSensor() {
        Logger.getInstance().log("Sensor value read.");
    }
}

public class Main {
    public static void main(String[] args) {
        MotorControl motor = new MotorControl();
        SensorModule sensor = new SensorModule();

        motor.moveForward();
        sensor.readSensor();
        motor.stop();
        sensor.readSensor();
    }
}