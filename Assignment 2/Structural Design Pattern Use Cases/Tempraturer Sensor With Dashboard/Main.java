import java.util.Scanner;

// Legacy Fahrenheit Sensor API (cannot be changed)
class LegacyFahrenheitSensor {
    public double getTemperatureFahrenheit() {
        // In a realistic scenario, this would return a live sensor value.
        // For demo, this will be set interactively.
        return 0.0;
    }
}

// Target interface expected by modern dashboard (expects Celsius)
interface CelsiusTemperature {
    double getTemperatureCelsius(double fahrenheitInput);
}

// Adapter: Converts Fahrenheit to Celsius and adapts legacy API to modern usage
class TemperatureAdapter implements CelsiusTemperature {
    @Override
    public double getTemperatureCelsius(double fahrenheitInput) {
        double celsius = (fahrenheitInput - 32) * 5.0 / 9.0;
        System.out.printf("Converting %.2f °F to Celsius...", fahrenheitInput);
        System.out.printf("Formula: (Fahrenheit - 32) * 5 / 9");
        System.out.printf("Step 1: %.2f - 32 = %.2f", fahrenheitInput, fahrenheitInput - 32);
        System.out.printf("Step 2: %.2f * 5 / 9 = %.2f", fahrenheitInput - 32, celsius);
        return celsius;
    }
}

// Modern Dashboard (uses Celsius interface)
class ModernDashboard {
    private CelsiusTemperature temperatureSensor;

    public ModernDashboard(CelsiusTemperature temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public void displayTemperature(double fahrenheitValue) {
        double celsiusValue = temperatureSensor.getTemperatureCelsius(fahrenheitValue);
        System.out.printf("📊 Current Temperature: %.2f °C", celsiusValue);
    }
}

// Main class to run interactive demo
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LegacyFahrenheitSensor legacySensor = new LegacyFahrenheitSensor();
        CelsiusTemperature adapter = new TemperatureAdapter();
        ModernDashboard dashboard = new ModernDashboard(adapter);

        System.out.println("=== IoT Temperature Adapter Demo ===");
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Enter Fahrenheit value for conversion");
            System.out.println("2. Show sample conversion (e.g. 100°F)");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter current sensor value (°F): ");
                    double userFahrenheit = scanner.nextDouble();
                    dashboard.displayTemperature(userFahrenheit);
                    break;
                case 2:
                    double sampleF = 100.0;
                    dashboard.displayTemperature(sampleF);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting demo. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println("---");
        }
        scanner.close();
    }
}