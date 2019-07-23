import NET.BinanceApiConnection;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> exchangeRates = BinanceApiConnection.getExchangeRates();

        for(Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

