package stock.trading;

public class Stock {
    public String symbol;
    public String name;
    public double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public void updatePrice() {
        double changePercent = (Math.random() * 10 - 5);
        price += price * (changePercent / 100);
        price = Math.round(price * 100.0) / 100.0;
    }
}
