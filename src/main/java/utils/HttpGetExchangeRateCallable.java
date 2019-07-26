package utils;

import DAL.net.BinanceApiConnection;

import java.util.concurrent.Callable;

public class HttpGetExchangeRateCallable implements Callable<Double> {
    private String symbol;

    public HttpGetExchangeRateCallable(String symbol) {
        this.symbol = symbol;
    }

    public Double call() {
        return BinanceApiConnection.getSymbolPrice(this.symbol);
    }

}
