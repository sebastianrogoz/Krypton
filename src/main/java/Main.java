import NET.BinanceApiConnection;
import DAL.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        BinanceApiConnection binanceConn1 = new BinanceApiConnection();
        BinanceApiConnection binanceConn2 = new BinanceApiConnection();
        DatabaseConnection dbConn = DatabaseConnection.getInstance();


        //https://api.binance.com/api/v3/avgPrice?symbol=USDCUSDT


        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(binanceConn1.getSymbolPrice("BTCUSDT"));
            }).start();
        }




    }
}

