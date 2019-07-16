package net;

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

    public List<String> getSymbolsList() throws Exception{
        String url = "https://api.binance.com/api/v1/exchangeInfo";
        Tuple<Integer, String> response = HttpConnection.getResponseContent(url);
        JSONArray jsonArr = new JSONObject(response.item2).getJSONArray("symbols");
        List<String> symbols = new ArrayList<String>();

        for(int i = 0; i < jsonArr.length(); i++) {
            symbols.add(jsonArr.getJSONObject(i).getString("symbol"));
        }
        return symbols;
    }
}