import NET.BinanceApiConnection;
import DAL.DatabaseConnectionOld;

public class Main {
    public static void main(String[] args) throws Exception {

        BinanceApiConnection binanceConn = new BinanceApiConnection();
        DatabaseConnectionOld dbConn = DatabaseConnectionOld.getInstance();

        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(binanceConn.getSymbolPrice("BTCUSDT"));
            }).start();
        }
    }
}

