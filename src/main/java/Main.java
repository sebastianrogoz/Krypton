import net.BinanceApiConnection;
import DAL.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        BinanceApiConnection exConn = new BinanceApiConnection();
        DatabaseConnection dbConn = DatabaseConnection.getInstance();
        List<String> symbols = exConn.getSymbolsList();

        for(String symbol : symbols) {
            System.out.println(symbol);
        }

        //https://api.binance.com/api/v3/avgPrice?symbol=USDCUSDT

    }
}

