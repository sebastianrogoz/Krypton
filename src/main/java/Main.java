import NET.BinanceApiConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(DAL.Repositories.ExchangeRateRepository.getCurrencyBySymbol("XZCBNB").getValue());
        System.out.println(BinanceApiConnection.getSymbolPrice("XZCBNB"));
    }
}

