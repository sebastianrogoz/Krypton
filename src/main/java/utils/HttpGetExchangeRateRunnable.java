package utils;

import NET.BinanceApiConnection;

public class HttpGetExchangeRateRunnable implements Runnable{
    private String symbol;
    private double price;

    public HttpGetExchangeRateRunnable(String symbol) {
        this.symbol = symbol;
    }

    public void run() {
        this.price = BinanceApiConnection.getSymbolPrice(this.symbol);
    }

    public double getPrice() {
        return this.price;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
