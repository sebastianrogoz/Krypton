import NET.BinanceApiConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(DAL.Repositories.ExchangeRateRepository.getCurrencyBySymbol("BNBBTC").getValue());
        System.out.println(BinanceApiConnection.getSymbolPrice("BNBBTC"));

    }
}

