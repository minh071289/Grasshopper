package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    private String text;
    public Translate(String text) {
        this.text = text;
    }
    public String translate () {
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbxCc7iNhd4CirHudoIbxnNLJn3G3yORKVpdy-coMUVM5N4scqGYStJ-t_ygVK3hvrYb/exec" +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + "vi" +
                    "&source=" + "en";
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Translation Error";
        }
    }
}
