import java.util.Scanner;

interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double getCost() { return 30.0; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee c) { this.decoratedCoffee = c; }
    public String getDescription() { return decoratedCoffee.getDescription(); }
    public double getCost() { return decoratedCoffee.getCost(); }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee c) { super(c); }
    public String getDescription() { return super.getDescription() + ", Milk"; }
    public double getCost() { return super.getCost() + 10.0; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee c) { super(c); }
    public String getDescription() { return super.getDescription() + ", Sugar"; }
    public double getCost() { return super.getCost() + 5.0; }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee c) { super(c); }
    public String getDescription() { return super.getDescription() + ", Whipped Cream"; }
    public double getCost() { return super.getCost() + 15.0; }
}

class ChocolateDecorator extends CoffeeDecorator {
    public ChocolateDecorator(Coffee c) { super(c); }
    public String getDescription() { return super.getDescription() + ", Chocolate"; }
    public double getCost() { return super.getCost() + 12.0; }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Coffee coffee = new SimpleCoffee();

        System.out.println(" Welcome to Creative Java Coffee Shop! ");
        System.out.println("Start with: " + coffee.getDescription() + " (₹" + coffee.getCost() + ")");

        while (true) {
            System.out.println("Choose an extra to add (or finish your order):");
            System.out.println("1Milk (+10)");
            System.out.println("2 Sugar (+5)");
            System.out.println("3 Whipped Cream (+15)");
            System.out.println("4 Chocolate (+12)");
            System.out.println("5 Finish and Show Order");
            System.out.print("Pick option (1-5): ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    coffee = new MilkDecorator(coffee);
                    System.out.println("Milk added! Order: " + coffee.getDescription() + " (₹" + coffee.getCost() + ")");
                    break;
                case 2:
                    coffee = new SugarDecorator(coffee);
                    System.out.println("Sugar added! Order: " + coffee.getDescription() + " (₹" + coffee.getCost() + ")");
                    break;
                case 3:
                    coffee = new WhippedCreamDecorator(coffee);
                    System.out.println("Whipped Cream added! Order: " + coffee.getDescription() + " (₹" + coffee.getCost() + ")");
                    break;
                case 4:
                    coffee = new ChocolateDecorator(coffee);
                    System.out.println("Chocolate added! Order: " + coffee.getDescription() + " (₹" + coffee.getCost() + ")");
                    break;
                case 5:
                    System.out.println("----- Final Order -----");
                    System.out.println("Order: " + coffee.getDescription());
                    System.out.printf("Total Cost: ₹%.2f", coffee.getCost());
                    System.out.println("Your coffee will be served shortly! Thank you for choosing us ");
                    scanner.close();
                    return;
                default:
                    System.out.println(" Invalid choice! Please pick 1-5.");
            }
        }
    }
}