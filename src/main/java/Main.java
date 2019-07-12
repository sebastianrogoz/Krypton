import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.*;

import static net.httpConnection.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://api.binance.com/api/v1/exchangeInfo";

        JSONObject json = new JSONObject(getResponseContent(url));

        System.out.println(json.getJSONArray("symbols").getJSONObject(11).getString("symbol"));

    }
}

