package NET;

import javafx.concurrent.Task;
import utils.HttpGetExchangeRateCallable;
import org.json.*;
import utils.ListCutter;
import utils.Tuple;

import java.util.*;
import java.util.concurrent.*;

public class BinanceApiConnection {

    public BinanceApiConnection() {
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

    public static Map<String, Double> performMultithreadedPriceRequests(List<String> exchangeRateSymbols, int numberOfThreads) throws Exception{
        Map<String, Double> exchangeRates = new HashMap<>();
        ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads);

        for(List<String> exchangeRateSymbolsSubList : ListCutter.cut(exchangeRateSymbols, numberOfThreads)) {
            List<Callable<Double>> taskList = new ArrayList<>();
            for(String symbol : exchangeRateSymbolsSubList) {
                taskList.add(new HttpGetExchangeRateCallable(symbol));
            }
            List<Future<Double>> futureResponses = exec.invokeAll(taskList);
            List<Double> responses = new ArrayList<>();
            for(Future future : futureResponses) {
                responses.add((Double) future.get());
            }

            Iterator i1 = exchangeRateSymbolsSubList.iterator();
            Iterator i2 = responses.iterator();
            String key;
            Double value;

            while(i1.hasNext() && i2.hasNext()) {
                key = (String)i1.next();
                value = (double)i2.next();
                exchangeRates.put(key,value);
                System.out.println(key + " " + value);
            }
        }
        exec.shutdown();
        return exchangeRates;
    }
}