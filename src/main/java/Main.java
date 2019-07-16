import net.BinanceApiConnection;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        BinanceApiConnection exConn = new BinanceApiConnection();
        List<String> symbols = exConn.getSymbolsList();

        for(String symbol : symbols) {
            System.out.println(symbol);
        }

    }
}

