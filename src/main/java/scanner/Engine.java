/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entities.CoinPair;
import entities.Exchange;
import entities.Price;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class Engine {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private DateFormat df = new SimpleDateFormat("yyyy-MMM-dd:hh:mm:ss");
    String[] strs;
    Exchange bini;
    private CoinPair cp;
    
    public Engine(){
        
        initiateAllPairs();
//        updatePrices();
    }
    public void updatePrices() {
        //Only Binance for now
        Map<String, CoinPair> map = bini.getCoinpairs();
        map.forEach((k,v)->{
            System.out.println("UPDATE PIRCES"+v.getName());
        });
            new Thread(new priceMonitor(map.get("BTCUSDT"))).start(); //Put inside the foreach loop !!!
    }

    private void initiateAllPairs() {
        
        try {
            strs = ExchangeInitiater.getCoinPairsFromExchange("https://api.binance.com/api/v1/exchangeInfo");
            bini = new Exchange("Binance", "https://api.binance.com/api/v1/trades?symbol=", strs);
            cp = new CoinPair("BTCUSDT");
            bini.addCoinPair(cp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Engine().updatePrices();
    }

     
    
    private class priceMonitor implements Runnable {

        private CoinPair pair;

        public priceMonitor(CoinPair pair) {
            this.pair = pair;
        }

        @Override
        public void run() {
            try {
//                String urlstring = "https://api.binance.com/api/v1/trades?symbol=BTCUSDT";
//                String urlstring = pair.getExchange().getBaseUrl()+pair.getName();
                String urlstring = bini.getBaseUrl()+pair.getName();
                System.out.println("NEW URL: "+urlstring);
                URL url = new URL(urlstring);
                int counter = 0;
                while (counter < 1000) { //Will be changed to while(true)
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept", "application/json");

                    if (con.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + con.getResponseCode());
                    }
                    InputStream in = (InputStream) con.getContent();
                    InputStreamReader reader = new InputStreamReader(in);
                    JsonParser jp = new JsonParser();
//                    JsonElement root = jp.parse(reader);
//                    JsonArray arr = root.getAsJsonArray();
//                    arr.get(0).getAsJsonObject();
//                     char c;
//                    while((c = (char)reader.read())!=-1){
//                        System.out.print(c);
//                    }
                    JsonElement jsonarr = jp.parse(reader);
//                        System.out.println(jsonarr.isJsonArray());
                    Price[] prices = gson.fromJson(jsonarr, Price[].class);
                    Price lastPrice = prices[0];
                    //System.out.print("PRICE: ");
                    //System.out.println(prices[0].getPrice());
                    //System.out.printf("DATE CONVERTED %s",df.format(new Date(1517521885470)));
                    System.out.printf("BTC to USD price:%s at time:%s\n", prices[0].getPrice(), df.format(new Date(prices[0].getTime())));
                    cp.addPrice(prices[0]);

                    waitASecondBeforeRepeat();
                    counter++;
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void waitASecondBeforeRepeat() {
            try {
                
                Thread.sleep(1000);
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
