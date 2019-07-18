import DAL.Repositories.CurrencyRepository;

public class Main {
    public static void main(String[] args) {

        System.out.println(CurrencyRepository.getCurrencyBySymbol("BTCUSDT"));
    }
}

