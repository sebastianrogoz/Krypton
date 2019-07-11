package net;

import java.net.HttpURLConnection;
import org.json.*;

public class binanceApiConnection {
    private final String apiKey;
    private final String secretKey;

    public binanceApiConnection(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    private JSONObject getResponse(String url) {
        return new JSONObject();
    }
}
