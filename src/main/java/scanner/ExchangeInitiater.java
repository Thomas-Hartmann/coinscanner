/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import com.fasterxml.jackson.databind.*;
import entities.Exchange;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tha
 */
public class ExchangeInitiater {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {
            
            String url = "https://api.binance.com/api/v1/exchangeInfo";
            String[] strs = null;
            try {
                strs = getCoinPairsFromExchange(url);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Exchange binance = new Exchange("binance","https://api.binance.com/api/v1/trades?symbol=",strs);
                
                
                
               // symbolStrings.forEach(System.out::println);
//		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//		StringBuilder output = new StringBuilder(); 
//                        String out;
//		System.out.println("Output from Server .... \n");
//		while ((out = br.readLine()) != null) {
//			output.append(out);
//		}
//                System.out.println(output.toString());
//                myMap = om.readValue(conn.getInputStream(), HashMap.class);
//                System.out.println("map size: "+myMap.size());
//                myMap.keySet().forEach((el)->{System.out.println(el);});
//                List<String> results = myMap.get("symbols");
//                
////                results.keySet().forEach((el)->{System.out.println(el);});
//                results.forEach((el)->{
//                    try {
//                        Map<String, String> map = om.readValue(el, HashMap.class);
//                        map.keySet().forEach((element)->{System.out.println(element);});
//                    } catch (IOException ex) {
//                        Logger.getLogger(NetClientGet.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
		
          

	}

    public static String[] getCoinPairsFromExchange(String urlstr) throws Exception {
        ObjectMapper om = new ObjectMapper();
        try {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
                JsonNode root = om.readTree(conn.getInputStream());
                System.out.println("ROOT: ");
                System.out.println(root.asText());
                JsonNode symbols = root.path("symbols");
                System.out.println(symbols.isMissingNode());
//                
                String symbol1 = symbols.get(0).path("symbol").asText();
                Iterator<JsonNode> nodes = symbols.elements();
                List<String> symbolStrings = new ArrayList();
                while(nodes.hasNext()){
                    symbolStrings.add(nodes.next().path("symbol").asText());
                }
                conn.disconnect();
                return symbolStrings.toArray(new String[symbolStrings.size()]);

	  } catch (IOException e) {

		e.printStackTrace();
                throw new Exception("Error in getCoinPairFromExchange(): "+e.getMessage());
	  } 
    }

}