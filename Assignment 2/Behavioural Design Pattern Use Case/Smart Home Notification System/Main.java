import java.util.*;

// Observer interface
interface Observer {
    void update(String event);
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String event);
}

// Concrete Subject (Smart Hub)
class SmartHub implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer o : observers) {
            o.update(event);
        }
    }

    // Methods for event triggers
    public void userArrivedHome() {
        System.out.println("SmartHub: User has arrived home!");
        notifyObservers("UserArrivedHome");
    }

    public void goodMorningRoutine() {
        System.out.println("SmartHub: Good morning routine started!");
        notifyObservers("GoodMorningRoutine");
    }

    public void securityAlert() {
        System.out.println("SmartHub: Security alert triggered!");
        notifyObservers("SecurityAlert");
    }
}

// Example device observers
class LightObserver implements Observer {
    public void update(String event) {
        switch (event) {
            case "UserArrivedHome":
                System.out.println("LightObserver: Turning on lights! (Welcome Home)");
                break;
            case "GoodMorningRoutine":
                System.out.println("LightObserver: Setting gentle brightness for morning.");
                break;
            case "SecurityAlert":
                System.out.println("LightObserver: Flashing red lights for alert!");
                break;
        }
    }
}

class ThermostatObserver implements Observer {
    public void update(String event) {
        switch (event) {
            case "UserArrivedHome":
                System.out.println("ThermostatObserver: Adjusting to preferred temperature.");
                break;
            case "GoodMorningRoutine":
                System.out.println("ThermostatObserver: Warming up house.");
                break;
            case "SecurityAlert":
                System.out.println("ThermostatObserver: Going to energy save mode.");
                break;
        }
    }
}

class CoffeeMachineObserver implements Observer {
    public void update(String event) {
        switch (event) {
            case "UserArrivedHome":
                System.out.println("CoffeeMachineObserver: Brewing coffee to welcome you!");
                break;
            case "GoodMorningRoutine":
                System.out.println("CoffeeMachineObserver: Starting morning brew.");
                break;
            case "SecurityAlert":
                System.out.println("CoffeeMachineObserver: Pausing brewing for safety.");
                break;
        }
    }
}

// Optional: Add more creative observers if desired!

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHub hub = new SmartHub();

        // Register devices
        hub.registerObserver(new LightObserver());
        hub.registerObserver(new ThermostatObserver());
        hub.registerObserver(new CoffeeMachineObserver());

        System.out.println("Welcome to your Smart Home!");
        System.out.println("Choose an action to trigger:");
        System.out.println("1. User Arrives Home");
        System.out.println("2. Start Good Morning Routine");
        System.out.println("3. Security Alert");
        System.out.println("0. Exit");

        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hub.userArrivedHome();
                    break;
                case 2:
                    hub.goodMorningRoutine();
                    break;
                case 3:
                    hub.securityAlert();
                    break;
                case 0:
                    System.out.println("Shutting down smart home. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
