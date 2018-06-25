/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author tha
 */
@Entity
public class Price implements Comparable<Price>, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    private long priceTime;
    private double price;
//    @OneToOne
//    private CoinPair coinpair;

//    public Price(int time, double price, CoinPair coinpair) {
//        this.time = time;
//        this.price = price;
//        this.coinpair = coinpair;
//    }

    public Price(long time, double price) {
        this.priceTime = time;
        this.price = price;
//        this.coinpair = cp;
    }

    public Price() {
    }
    

    public long getTime() {
        return priceTime;
    }

    public void setTime(long time) {
        this.priceTime = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public CoinPair getCoinpair() {
//        return coinpair;
//    }
//
//    public void setCoinpair(CoinPair coinpair) {
//        this.coinpair = coinpair;
//    }

    @Override
    public int compareTo(Price o) {
        return (this.price >= o.price)?1:-1;
    }

    
    
}
