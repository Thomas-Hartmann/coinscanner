/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author tha
 */
@Entity
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    private PriceMove down;
    private PriceMove up;
    private int baseQuality; //this will be recalculated whenever the up price is updated.

    public Base() {
    }

    
    public Price getBasePrice(){
        return down.getLowPrice();
   }
   public void setBaseQuality(){
       //drop 10% in less than 3 hours and back up in less than 24 hours total
       double dropQuality = down.getDiffPercentage()/down.getTimeSpan();
       double bounceQuality = up.getDiffPercentage()/up.getTimeSpan();
       this.baseQuality = (int) Math.round(dropQuality*bounceQuality*10);
   }
   
    public int getBaseQuality() {
        return baseQuality;
    }

    public Base(PriceMove down, PriceMove up) {
        this.down = down;
        this.up = up;
        setBaseQuality();
    }

    public PriceMove getDown() {
        return down;
    }

    public void setDown(PriceMove down) {
        this.down = down;
    }

    public PriceMove getUp() {
        return up;
    }

    public void setUp(PriceMove up) {
        this.up = up;
        setBaseQuality();
    }
   
}
