import org.json.JSONObject;
import utils.Tuple;

import java.net.*;
import java.io.*;
import java.util.*;

import static net.httpConnection.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://api.binance.com/api/v1/exchangeInfo";
        Tuple<Integer, String> response = getResponseContent(url);
        JSONObject json = new JSONObject(response.item2);

        System.out.println(json.getJSONArray("symbols").getJSONObject(11).getString("symbol"));
        System.out.println(response.item1);
    }
}

