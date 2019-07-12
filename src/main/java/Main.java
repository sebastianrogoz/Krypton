import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://api.binance.com/api/v1/exchangeInfo";

        JSONObject json = new JSONObject(getHTML(url));

        System.out.println(json.getJSONArray("symbols").getJSONObject(11).getString("symbol"));

    }

    public static String getHTML(String url) throws Exception {
        StringBuilder result = new StringBuilder();
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}

