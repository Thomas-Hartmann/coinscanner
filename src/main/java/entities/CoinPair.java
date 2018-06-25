/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dataFacade.DataFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author tha
 */
@Entity
public class CoinPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
//    private final TreeSet<Price> prices = new TreeSet();
//    private final List<Price> prices = new ArrayList(); //Change arraylist to collection with auto delete after 12 hours  = 43200 sekunder/prices.
//    private final List<PriceMove> significantMoves = new ArrayList();
    @OneToMany
    private final List<Base> bases = new ArrayList();
    //Last low is initiated with value so high, it will be overwritten with first actual price. BUT better to load all these values from database (so last low would come from last time the program was run)
//    private final Price lastLow; //in last 12 hours (but keep if currentMove is based from this = coin still moving in same direction overall)
//    private Price lastHigh; //In last 12 hours (but keep if currentMove is based from this = coin still moving in same direction overall)
    private String name;
    private String description;
//    @OneToOne
//    private Exchange exchange;
    @Transient
    private PriceMove lastMove;
    @Transient
    private PriceMove lastSignificantMove;
    @Transient
    private PriceMove currentMove; //a move is a move when start-end is more than +/-5% in last 12 hours. It continoues to be a move as long as it stays above/below the initial 5% and that any 3 hour period has extended the move (small contramoves in an hour or two are allowed without terminating the move).
    @Transient
    DataFacade df = new DataFacade();
    //if a move is terminated and another one started in same direction
    public CoinPair(String name) {
        this.name = name;
        Price low = new Price(0, 100000000); 
        Price high = new Price(0, 0); 
        currentMove = new PriceMove(low, high); 
       
    }

    public CoinPair() {
    }
    
    public void addPrice(Price price) {
        boolean priceChanged = false;
        
//        this.prices.add(price);
        if(currentMove.updateHighLow(price)){
            if(currentMove.isSignificant() && !currentMove.isUp()){ //Larger than 10% change
                //Save it in last significant move
                System.out.println("THIS SHOULD BE MADE AS AN ALERT NOW THAT current move is: "+currentMove.getDiffPercentage()+"%");            
            }
        }
        else if(currentMove.shouldTerminate(price)){
            if(currentMove.isSignificant()){
                if(isBase(lastSignificantMove,currentMove)){
                    addBase(new Base(lastMove, lastMove));
                    System.out.println("NEW BASE CREATED: "+new Date(currentMove.getEndPrice().getTime()));
                }
                this.lastSignificantMove = currentMove;
            } else {
                this.lastMove = currentMove;
            }
            currentMove = (lastMove.isUp())? new PriceMove(price,lastMove.getHighPrice()):new PriceMove(lastMove.getLowPrice(), price);
            df.createPriceMove(currentMove);
        }
        
        //check if a move is more than 5%
        
        
    }
    boolean isBase(PriceMove last, PriceMove current){
        boolean isVshape = (!last.isUp() && current.isUp());
        boolean upIsImmidiate = current.iAmImmidiate(last);
        //boolean bothSignificant = (last.isSignificant() && current.isSignificant());
        
        return isVshape && upIsImmidiate; //bothSignificant;
    }
//    boolean createBase(){
//        boolean result = false;
//        return result;
//    }

//    public Price getLastLow() {
//        return lastLow;
//    }
//
////    public void setLastLow(Price lastLow) {
////        this.lastLow = lastLow;
////    }
//
//    public Price getLastHigh() {
//        return lastHigh;
//    }

//    public void setLastHigh(Price lastHigh) {
//        this.lastHigh = lastHigh;
//    }

    public List<Base> getBases() {
        return bases;
    }

    private void addBase(Base base) {
        this.bases.add(base);
        df.createBase(base);
    }

   
    

//    public Set<Price> getPrices() {
//        return prices;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

//    public Exchange getExchange() {
//        return exchange;
//    }
//
//    public void setExchange(Exchange exchange) {
//        this.exchange = exchange;
//    }

//    public PriceMove getMove() {
//        return move;
//    }
    
    
}
