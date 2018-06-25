/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author tha
 */
@Entity
public class Exchange implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String keyphrase; //api key to authenticate account on the exchange
    private String baseUrl;
    @OneToMany
    private Map<String,CoinPair> coinpairs;

    public Exchange(String name, String baseUrl, String[] pairs) {
        this.name = name;
        this.baseUrl = baseUrl;
        initiate(pairs);
    }

    public Exchange() {
        
    }
    
    private void initiate(String[] pairs){
        if(coinpairs == null){
            coinpairs = new HashMap();
            for (String pair : pairs) {
//                System.out.println(url);
            addCoinPair(new CoinPair(pair));
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return keyphrase;
    }

    public void setKey(String key) {
        this.keyphrase = key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, CoinPair> getCoinpairs() {
        return coinpairs;
    }

    public void addCoinPair(CoinPair coinpair) {
        this.coinpairs.put(coinpair.getName(), coinpair);
//        coinpair.setExchange(this);  
    }
    
}
