package NET;

import javafx.concurrent.Task;
import utils.HttpGetExchangeRateCallable;
import org.json.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class BinanceApiConnection {

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

    public static Map<String, Double> getAllExchangeRates() throws Exception{
        return null;
    }

    private static Map<String, Double> performMultithreadedPriceRequests(List<String> exchangeRateSymbols, int numberOfThreads) {
        List<Callable<Double>> taskList = new ArrayList<>();
        List<Future<Double>> responseList = new ArrayList<>();

        Executor exec = Executors.newFixedThreadPool(numberOfThreads);



        return null;
    }
}