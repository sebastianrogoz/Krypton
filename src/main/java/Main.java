import DAL.Repositories.ExchangeRateRepository;
import NET.BinanceApiConnection;

import javax.management.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {



        List<String> symbols = BinanceApiConnection.getSymbolsList();
        for(String symbol : symbols) {
            double exchangeRateValue = BinanceApiConnection.getSymbolPrice(symbol);
            ExchangeRateRepository.addCurrency(symbol, exchangeRateValue);
        }
    }
}

