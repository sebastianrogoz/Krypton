package NET;

import utils.HttpGetExchangeRateCallable;
import org.json.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BinanceApiConnection {
    //private final String apiKey;
    //private final String secretKey;

    public BinanceApiConnection(/*String apiKey, String secretKey*/) {
        //this.apiKey = apiKey;
        //this.secretKey = secretKey;
    }

    public static List<String> getSymbolsList(){
        String url = "https://api.binance.com/api/v1/exchangeInfo";
        Tuple<Integer, String> response = HttpConnection.getResponseContent(url);
        JSONArray jsonArr = new JSONObject(response.item2).getJSONArray("symbols");
        List<String> symbols = new ArrayList<String>();

        for(int i = 0; i < jsonArr.length(); i++) {
            symbols.add(jsonArr.getJSONObject(i).getString("symbol"));
        }
        return symbols;
    }

    public static Double getSymbolPrice(String symbol) {
        String url = "https://api.binance.com/api/v3/avgPrice?symbol=" + symbol;
        Tuple<Integer, String> response = HttpConnection.getResponseContent(url);

        try {
            JSONObject json = new JSONObject(response.item2);
            if(response.item1 == 200) {
                return Double.parseDouble(json.getString("price"));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Double> getExchangeRates() throws Exception{
        List<String> symbols = BinanceApiConnection.getSymbolsList();
        Map<String, Double> exchangeRates = new HashMap<>();
        int currentIndex;
        ExecutorService exec;
        List<HttpGetExchangeRateCallable> tasks = new ArrayList<>();
        List<Future<Double>> futures = new ArrayList<>();

        for(int i = 0; i < (symbols.size() / 20); i++) {
            exec = Executors.newFixedThreadPool(20);

            for(int j = 0; j < 20; j++) {
                currentIndex = i * 20 + j;
                tasks.add(new HttpGetExchangeRateCallable(symbols.get(currentIndex)));
            }
                futures = exec.invokeAll(tasks);
                for(Future<Double> entry : futures) {
                }
            symbols.

            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        exec = Executors.newFixedThreadPool(symbols.size() % 20);
        if(symbols.size() % 20 != 0) {
            for(int i = exchangeRates.size(); i < symbols.size(); i++) {
                currentIndex = i;
                HttpGetExchangeRateCallable runn = new HttpGetExchangeRateCallable(symbols.get(currentIndex));
                exec.submit(runn);
            }
        }

        return exchangeRates;
    }
}