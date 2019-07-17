package NET;

import utils.Tuple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    public static Tuple<Integer, String> getResponseContent(String url){
        try {
            StringBuilder result = new StringBuilder();
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            return new Tuple<Integer, String>(responseCode, result.toString());
        } catch (Exception e) {
            return new Tuple<>(-1, "Error getting http response.");
        }
    }
}
