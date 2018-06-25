/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import static tools.Util.*;

/**
 *
 * @author tha
 */
@Entity
public class PriceMove implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Price lowPrice;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Price highPrice;
    private boolean up;
    private boolean isSignificant = false;
    private double percentMove;
    //private final List<Price> hourlyPrices = new ArrayList();
    private final int SIGNIFICANTPCT = 10;
    private final int SIGNIFICANTHOURS = 3;
    public PriceMove(Price lowPrice, Price highPrice) {
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        setDirection(lowPrice, highPrice);
    }

    public PriceMove() {
    }

    void setDirection(Price lowPrice1, Price highPrice1) {
        up = (lowPrice1.getTime() < highPrice1.getTime())?true:false;
        percentMove = getDiffPercentage();
    }
    
    void setSignificant(Price lowPrice, Price HighPrice){ //if more than 10% in less than 3 hours
        boolean moreThan10Pct = (getDiffPercentage() >= SIGNIFICANTPCT)?true:false;
        //Maybe add a condition that a drop within the last hour of 5% or more is significant.
        boolean lessThan3Hours = getDiffTimeHours(lowPrice, HighPrice) <= SIGNIFICANTHOURS;
        this.isSignificant = isSignificant || (moreThan10Pct && lessThan3Hours);
    }
    
    public boolean updateHighLow(Price newPrice) {
        boolean result = false;
        if(newPrice.getPrice()<=lowPrice.getPrice()){
            lowPrice = newPrice;
            result = true;
        }
        if(newPrice.getPrice()>=highPrice.getPrice()){
            highPrice = newPrice; 
            result = true;
        }
        if(result){ //if changes were made
            setDirection(lowPrice, highPrice);
            setSignificant(lowPrice, highPrice);
        }
        return result;
    }
    public boolean isSignificant() {
        return isSignificant;
    }
    
    public double getDiffPrice(){
        return highPrice.getPrice() - lowPrice.getPrice();
    }
    
    double getDiffPercentage(){
        return (this.up)?getDiffPrice()/lowPrice.getPrice()*100:getDiffPrice()/highPrice.getPrice()*100;        
    }
    double getDiffTimeHours(Price lowPrice, Price highPrice){
        return getHoursFromMilliSecs(Math.abs(lowPrice.getTime()-highPrice.getTime()));
    }
    
    long getTimeSpan(){
        return Math.abs(lowPrice.getTime() - highPrice.getTime());
    }
//    public long getEndTime(){
//        return highPrice.getTime();
//    }

    public Price getLowPrice() {
        return lowPrice;
    }

//    public void setLowPrice(Price price) {
//        this.lowPrice = price;
//        setDirection(lowPrice, highPrice);
////        this.percentMove = (int)Math.round(getDiffPercentage()); //MAYBY CHANGE to Math.floor (so that only 5.1 becomes 5 (not e.g 4.6)
////        this.isSignificant = this.percentMove > 5;
//        this.setSignificant(lowPrice, highPrice);
//    }
//    
//    public void setHighPrice(Price price) {
//        this.highPrice = price;
//        setDirection(lowPrice, highPrice);
////        this.percentMove = (int)Math.round(getDiffPercentage()); //MAYBY CHANGE to Math.floor (so that only 5.1 becomes 5 (not e.g 4.6)
////        this.isSignificant = this.percentMove > 5;
//        this.setSignificant(lowPrice, highPrice);
//    }

    public Price getHighPrice() {
        return highPrice;
    }
    /**
     *
     */
    public boolean shouldTerminate(Price currentPrice){
        return noChangeToEndPriceIn4Hours(currentPrice) ;
        //|| priceIs50percentContra(currentPrice);
    }
    
//    boolean priceIs4hourContra(Price currentPrice){
//        if(up){
//            if(currentPrice.getPrice() < this.highPrice.getPrice()){
//                if(getHoursFromMilliSecs(currentPrice.getTime()-highPrice.getTime()) == 4){
//                    return true;
//                }
//            }
//        } else {
//            if(currentPrice.getPrice() > this.lowPrice.getPrice()){
//                if(getHoursFromMilliSecs(currentPrice.getTime()-lowPrice.getTime()) == 4){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    boolean noChangeToEndPriceIn4Hours(Price current){
//        System.out.println("difference from now to recorded endtime"+getHoursFromMilliSecs(current.getTime()-getEndPrice().getTime()));
        return getHoursFromMilliSecs(current.getTime()-getEndPrice().getTime())>=4;
        
    }
    boolean isExpired(){ //More than 10 hours old
        boolean isOld = getHoursFromMilliSecs(Math.abs(getStartPrice().getTime()-getEndPrice().getTime()))>=10;
        return isOld; // && noSignificantChangeLastHour.
    }
    boolean noSignificantChangeLastHour(){
        return true;
    }
    //Price moves contra with 50 percent of current move..
    boolean priceIs50percentContra(Price currentPrice){
        double moveDiff = getDiffPrice();
        double lastDiff;
        if(up){
            lastDiff = highPrice.getPrice() - currentPrice.getPrice();
        }else{
            lastDiff = currentPrice.getPrice() - lowPrice.getPrice();
        }
        return lastDiff/moveDiff*100 > 50;
    }
    
//    boolean moveIsOldandTired(Price currentPrice){
//        Price startPrice = getStartPrice();
//        boolean isOld = getHoursFromMilliSecs(currentPrice.getTime() - startPrice.getTime())==12;
//        //boolean isTired = last 2 hours added nothing to the move,
//        return isOld && isTired;
//    }
    boolean iAmImmidiate(PriceMove other){
        return getHoursFromMilliSecs(this.getStartPrice().getTime() - other.getEndPrice().getTime())<4;
    }
    Price getStartPrice(){
        return (up)?lowPrice:highPrice;
    }
    Price getEndPrice(){
        return (!up)?lowPrice:highPrice;
    }
    

    
    public boolean isUp() {
        return up;
    }
    
}
