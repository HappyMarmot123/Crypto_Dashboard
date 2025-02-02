package com.example.demo.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Component
public class CoinGeckoApi {
    private final OkHttpClient client;

    public CoinGeckoApi() {
        this.client = new OkHttpClient();
    }

    public List<String> fetchGlobalData(List<String> urls) throws IOException {
    	 List<String> responses = new ArrayList<>();
    	 
         for (String url : urls) {
             Request request = new Request.Builder()
                     .url(url)
                     .get()
                     .addHeader("accept", "application/json")
//                     .addHeader("x-cg-pro-api-key", "CG-JbGd9nxRsDpasAXNHcdiLEnn")
                     .build();

             Response response = client.newCall(request).execute();
             try {
                 if (response.isSuccessful()) {
                     String json = response.body().string();
                     if (url.equals("https://api.coingecko.com/api/v3/global")) { 
                 	    JSONObject jsonObject = new JSONObject(json);
                 	    String modifiedJson = jsonObject.toString(2);
                 	    responses.add(modifiedJson);
                     }
                     if (url.equals("https://api.coingecko.com/api/v3/search/trending")) { 
                    	    JSONObject jsonObject = new JSONObject(json);
                    	    jsonObject.remove("nfts");
                    	    jsonObject.remove("categories");
                    	    
                            JSONArray coinsArray = jsonObject.getJSONArray("coins");
                            for (int i = 0; i < coinsArray.length(); i++) {
                                JSONObject item = coinsArray.getJSONObject(i).getJSONObject("item");
                                JSONObject data = item.getJSONObject("data");
                                data.remove("price_change_percentage_24h");
                            }
                            
                            // 현재 날짜를 추가
                            LocalDateTime currentDateTime = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                            String formattedDateTime = currentDateTime.format(formatter);
                            
                            jsonObject.put("updated_at", formattedDateTime); 
                    	    String modifiedJson = jsonObject.toString(2);
                    	    responses.add(modifiedJson);
                	}
                 } else {
                     throw new IOException("Unexpected code " + response);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         
         return responses;
    }
}