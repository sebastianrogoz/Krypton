package NET;

import org.json.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.List;

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
}