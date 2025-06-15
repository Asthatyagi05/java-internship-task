package stock.trading;

import java.util.*;

public class StockTradingPlatform {
    static Map<String, Stock> market = new HashMap<>();
    static Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        loadMarket();
        portfolio.loadFromFile();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                for (Stock stock : market.values()) {
                    stock.updatePrice();
                }
            }
        }, 0, 10000); // Every 10 seconds

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- STOCK TRADING MENU ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n---  MARKET DATA ---");
                    for (Stock stock : market.values()) {
                        System.out.printf("%s - %s : ₹%.2f%n", stock.symbol, stock.name, stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.nextLine().toUpperCase();
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    portfolio.buy(buySymbol, buyQty, market.get(buySymbol).price);
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.nextLine().toUpperCase();
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    portfolio.sell(sellSymbol, sellQty, market.get(sellSymbol).price);
                    break;
                case 4:
                    portfolio.showPortfolio(market);
                    break;
                case 5:
                    portfolio.saveToFile();
                    System.out.println("Portfolio saved. Goodbye!");
                    timer.cancel();
                    return;
                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }

    private static void loadMarket() {
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 180));
        market.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 130));
        market.put("AMZN", new Stock("AMZN", "Amazon.com", 110));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 190));
        market.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 220));
    }
}
