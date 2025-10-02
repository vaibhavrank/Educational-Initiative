import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

// Abstract product
abstract class Pizza {
    String name;
    public String getName() { return name; }
    public abstract void prepare();
    public void bake() { System.out.println(name + " is being baked."); }
    public void cut() { System.out.println(name + " is being cut."); }
    public void box() { System.out.println(name + " is being boxed."); }
}

// Concrete pizzas
class VegPizza extends Pizza {
    VegPizza() { name = "Veg Pizza"; }
    public void prepare() { System.out.println("Preparing fresh vegetables for " + name); }
}
class CheeseBurstPizza extends Pizza {
    CheeseBurstPizza() { name = "Cheese Burst Pizza"; }
    public void prepare() { System.out.println("Preparing cheesy delight for " + name); }
}
class MargheritaPizza extends Pizza {
    MargheritaPizza() { name = "Margherita Pizza"; }
    public void prepare() { System.out.println("Preparing classic Margherita toppings for " + name); }
}
class MexicanPizza extends Pizza {
    MexicanPizza() { name = "Mexican Pizza"; }
    public void prepare() { System.out.println("Adding spicy Mexican ingredients for " + name); }
}
class PaneerTandooriPizza extends Pizza {
    PaneerTandooriPizza() { name = "Paneer Tandoori Pizza"; }
    public void prepare() { System.out.println("Grilling paneer and tandoori sauce for " + name); }
}

// Factory with registration
class PizzaFactory {
    private static final Map<String, Supplier<Pizza>> registry = new HashMap<>();
    static {
        register("veg", VegPizza::new);
        register("cheeseburst", CheeseBurstPizza::new);
        register("margherita", MargheritaPizza::new);
        register("mexican", MexicanPizza::new);
        register("paneertandoori", PaneerTandooriPizza::new);
    }
    public static void register(String type, Supplier<Pizza> supplier) {
        registry.put(type.toLowerCase(), supplier);
    }
    public static Pizza createPizza(String type) {
        Supplier<Pizza> supplier = registry.get(type.toLowerCase());
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
    public static void displayAvailablePizzas() {
        System.out.println("Available pizzas: " + registry.keySet());
    }
}

// User-interactive client
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Creative Pizza Shop!");

        while (true) {
            PizzaFactory.displayAvailablePizzas();
            System.out.print("Enter pizza type to order (or 'exit' to quit): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Thank you for visiting! Goodbye.");
                break;
            }

            Pizza pizza = PizzaFactory.createPizza(input);
            if (pizza != null) {
                System.out.println("Order received for: " + pizza.getName());
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("Enjoy your " + pizza.getName() + "!");
            } else {
                System.out.println("Sorry, pizza type '" + input + "' is not available.");
            }
        }
        scanner.close();
    }
}