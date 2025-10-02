import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Strategy Interface
interface PaymentStrategy {
    String pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
    public String pay(int amount) {
        return "Credit Card [" + cardNumber + "] paid: $" + amount;
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    public PayPalPayment(String email) { this.email = email; }
    public String pay(int amount) {
        return "PayPal [" + email + "] paid: $" + amount;
    }
}

class CryptoPayment implements PaymentStrategy {
    private String walletAddress;
    public CryptoPayment(String walletAddress) { this.walletAddress = walletAddress; }
    public String pay(int amount) {
        return "Crypto Wallet [" + walletAddress + "] paid: $" + amount;
    }
}

// Additional Creative Strategy
class RewardPointsPayment implements PaymentStrategy {
    private int pointsAvailable;
    public RewardPointsPayment(int pointsAvailable) { this.pointsAvailable = pointsAvailable; }
    public String pay(int amount) {
        if(pointsAvailable >= amount) {
            pointsAvailable -= amount;
            return "Reward Points paid: $" + amount + " | Remaining points: " + pointsAvailable;
        } else {
            return "Insufficient reward points!";
        }
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;
    public void setPaymentStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void pay(int amount) {
        String transactionID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("--- Transaction Summary ---");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Time: " + time);
        System.out.println(strategy.pay(amount));
        System.out.println("---------------------------");
    }
}

// Demo with Console I/O resembling e-commerce checkout
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentContext context = new PaymentContext();

        System.out.println("Welcome to RoboShop Checkout!");
        System.out.print("Enter total payment amount: $");
        int amount = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Choose payment method:\n1.Credit Card\n2.PayPal\n3.\nCrypto\n4.Reward Points");
        int option = sc.nextInt();
        sc.nextLine();

        switch(option) {
            case 1:
                System.out.print("Enter Credit Card Number: ");
                String card = sc.nextLine();
                context.setPaymentStrategy(new CreditCardPayment(card));
                break;
            case 2:
                System.out.print("Enter PayPal Email: ");
                String email = sc.nextLine();
                context.setPaymentStrategy(new PayPalPayment(email));
                break;
            case 3:
                System.out.print("Enter Crypto Wallet Address: ");
                String wallet = sc.nextLine();
                context.setPaymentStrategy(new CryptoPayment(wallet));
                break;
            case 4:
                System.out.print("Enter available reward points: ");
                int points = sc.nextInt();
                context.setPaymentStrategy(new RewardPointsPayment(points));
                break;
            default:
                System.out.println("Invalid option! Exiting...");
                return;
        }

        context.pay(amount);
        System.out.println("Thank you for shopping with RoboShop!");
        sc.close();
    }
}