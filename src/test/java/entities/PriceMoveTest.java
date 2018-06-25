/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static tools.Util.getHoursFromMilliSecs;
import static tools.Util.getMilliSecFromHours;


/**
 *
 * @author tha
 */
public class PriceMoveTest {
    
    private PriceMove instance;
    private Price low;
    private Price high;
    
    public PriceMoveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        long lowValue = new Date().getTime(); //NOW
        long highValue = getMilliSecFromHours(getHoursFromMilliSecs(lowValue)+10); //Add 10 hours
        low  = new Price(lowValue,4.00000000); //low is first
        high = new Price(highValue,4.50000000);
        instance = new PriceMove(low, high);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDiffPrice method, of class PriceMove.
     */
    @Test
    public void testGetDiffPrice() {
        System.out.println("getDiffPrice");
        
        double expResult = 0.5;
        double result = instance.getDiffPrice();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDiffPercentage method, of class PriceMove.
     */
    @Test
    public void testGetDiffPercentage() {
        System.out.println("getDiffPercentage");
        
        double expResult = 12.5;
        double result = instance.getDiffPercentage();
        assertEquals(expResult, result, 1e-15);
    }

    /**
     * Test of getTimeSpan method, of class PriceMove.
     */
    @Test
    public void testGetTimeSpan() {
        System.out.println("getTimeSpan");
        long expResult = instance.getEndPrice().getTime()-instance.getStartPrice().getTime();
        long result = instance.getTimeSpan();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getEndTime method, of class PriceMove.
//     */
//    @Test
//    public void testGetEndTime() {
//        System.out.println("getEndTime");
//        PriceMove instance = null;
//        long expResult = 0L;
//        long result = instance.getEndTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getLowPrice method, of class PriceMove.
     */
    @Test
    public void testGetLowPrice() {
        System.out.println("getLowPrice");
        Price expResult = low;
        Price result = instance.getLowPrice();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setLowPrice method, of class PriceMove.
     * This method provokes changes in several others
     */
//    @Test
//    public void testSetLowPrice() {
//        System.out.println("setLowPrice");
//        Price startPrice = new Price(1499897319559L, 3.0);
//        instance.setLowPrice(startPrice);
//        double expResult = 1.5;
//        double result = instance.getDiffPrice();
//        assertEquals(expResult, result, 0.0);
//    }

    /**
     * Test of setHighPrice method, of class PriceMove.
     */
//    @Test
//    public void testSetHighPrice() {
//        System.out.println("setHighPrice");
//        Price price = new Price(1499897319559L, 5.0);
//        instance.setHighPrice(price);
//        double expResult = 1.0;
//        double result = instance.getDiffPrice();
//        assertEquals(expResult, result, 0.0);
//    }

    /**
     * Test of getHighPrice method, of class PriceMove.
     */
//    @Test
//    public void testGetHighPrice() {
//        System.out.println("getHighPrice");
//        PriceMove instance = null;
//        Price expResult = null;
//        Price result = instance.getHighPrice();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of isIsUp method, of class PriceMove.
     */
    @Test
    public void testIsUp() {
        System.out.println("isUpMove");
        
        assertTrue(instance.isUp());
        
    }

    /**
     * Test of setDirection method, of class PriceMove.
     */
//    @Test
//    public void testSetDirection() {
//        System.out.println("setDirection");
//        Price lowPrice1 = null;
//        Price highPrice1 = null;
////        PriceMove instance = null;
//        instance.setDirection(lowPrice1, highPrice1);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of setSignificant method, of class PriceMove.
     */
//    @Test
//    public void testSetSignificant() {
//        System.out.println("setSignificant");
//        Price lowPrice = null;
//        Price HighPrice = null;
//        
//        instance.setSignificant(lowPrice, HighPrice);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateHighLow method, of class PriceMove.
     */
    @Test
    public void testUpdateHighLow() {
        System.out.println("updateHighLow");
        Price price = new Price(getMilliSecFromHours(getHoursFromMilliSecs(low.getTime())+10), low.getPrice()-1); // 10 hours later and price is down with 1. Endprice should now be 3.
        instance.updateHighLow(price);
        double expResult = 3.0;
        double result = instance.getEndPrice().getPrice();
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of isSignificant method, of class PriceMove. A move is significant if it is more than 10%.
     */
//    @Test
//    public void testIsSignificant() {
//        System.out.println("isSignificant");
//        Price newHigh = new Price(high.getTime(),4.5);
//        instance.updateHighLow(high);
//        System.out.printf("different in price: %s", instance.getDiffPrice());
//        assertTrue(instance.isSignificant());
//        instance = new PriceMove(low, new Price(high.getTime(), 4.3));
//        System.out.printf("different in percent: %s", instance.getDiffPercentage());
//        assertFalse(instance.isSignificant());
//    }

    /**
     * Test of getHighPrice method, of class PriceMove.
     */
//    @Test
//    public void testGetHighPrice() {
//        System.out.println("getHighPrice");
//        
//        Price expResult = null;
//        Price result = instance.getHighPrice();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of shouldTerminate method, of class PriceMove.
     */
//    @Test
//    public void testShouldTerminate() {
//        System.out.println("shouldTerminate");
//        Price currentPrice = null;
//        
//        boolean expResult = false;
//        boolean result = instance.shouldTerminate(currentPrice);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of noChangeToEndTimeIn4Hours method, of class PriceMove.
     */
    @Test
    public void testNoChangeToEndTimeIn4Hours() {
        System.out.println("noChangeToEndTimeValueIn4Hours test");
        low  = new Price(low.getTime(),2.0); //low is first
        high = new Price(high.getTime(),4.0); //
        instance.updateHighLow(low);
        instance.updateHighLow(high);
        System.out.printf("low price: %s and high price: %s",new Date(low.getTime()),new Date(high.getTime()));
        double endTimePlus4 = getHoursFromMilliSecs(instance.getEndPrice().getTime())+4; //endprice should be the high price. add 4 hours.
        Price currentPrice = new Price(getMilliSecFromHours(endTimePlus4),3.90); //low was before high (trend is rising) 4 hours after high is now lower 
        System.out.println("");
        instance.updateHighLow(currentPrice);
        System.out.printf("low price: %s and high price after : %s",new Date(low.getTime()),new Date(high.getTime()));
        
        assertTrue(instance.noChangeToEndPriceIn4Hours(currentPrice));
    }

    /**
     * Test of priceIs50percentContra method, of class PriceMove.
     */
    @Test
    public void testPriceIs50percentContra() {
        System.out.println("priceIs50percentContra");
        low  = new Price(1499820000000L,2.0); //low is first
        high = new Price(1499860000000L,4.0);
        System.out.printf("low price: %s and high price: %s",new Date(low.getTime()),new Date(high.getTime()));
        Price currentPrice = new Price(1499890000000L,2.90); //low was before high (trend is rising) bur current price has now fallen to half 3 hours later.
        instance.updateHighLow(low);
        instance.updateHighLow(high);
        
        assertTrue(instance.priceIs50percentContra(currentPrice));
    }

    /**
     * Test of iAmImmidiate method, of class PriceMove. A move is immediate to another move if it happens less than 4 hours later.
     */
    @Test
    public void testIAmImmediate() {
        System.out.println("IAmImmidiate");
        double newStartTime = tools.Util.getHoursFromMilliSecs(instance.getStartPrice().getTime())-12; //Substract 2 hours from end time of other move
        double newEndTime = tools.Util.getHoursFromMilliSecs(instance.getStartPrice().getTime())-2; //Substract 2 hours from end time of other current Move
        
        PriceMove other = new PriceMove(new Price(getMilliSecFromHours(newStartTime), 3.0), new Price(getMilliSecFromHours(newEndTime), 5.0)); //Ends 2 hours before current starts
        assertTrue(instance.iAmImmidiate(other));
    }
    
    /**
     * Test of iAmImmidiate method, of class PriceMove. A move is immediate to another move if it happens less than 4 hours later. This method test if 5 hours difference makes it false.
     */
    @Test
    public void testIAmNotImmediate() {
        System.out.println("I Am Not Immidiate");
        double newStartTime = tools.Util.getHoursFromMilliSecs(instance.getStartPrice().getTime())-12; //Substract 2 hours from end time of other move
        double newEndTime = tools.Util.getHoursFromMilliSecs(instance.getStartPrice().getTime())-5; //Substract 5 hours from end time of other current Move
        
        PriceMove other = new PriceMove(new Price(getMilliSecFromHours(newStartTime), 3.0), new Price(getMilliSecFromHours(newEndTime), 5.0)); //Ends 2 hours before current starts
        assertFalse(instance.iAmImmidiate(other));
    }

    /**
     * Test of getStartPrice method, of class PriceMove.
     */
    @Test
    public void testGetStartPrice() {
        System.out.println("getStartPrice");
        long beforeStartTime = getMilliSecFromHours(getHoursFromMilliSecs(low.getTime())-2);
        Price earlierHigh  = new Price(beforeStartTime,9.0); //time for high is now earlier
        //System.out.printf("low price: %s and high price: %s",new Date(low.getTime()),new Date(high.getTime()));
        instance.updateHighLow(earlierHigh);
        double expResult = 9.0;
        double result = instance.getStartPrice().getPrice();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getEndPrice method when end price is the highest, of class PriceMove.
     */
    @Test
    public void testGetEndPriceHigh() {
        System.out.println("getEndPrice");
        long newTime = getMilliSecFromHours(getHoursFromMilliSecs(high.getTime())+10); //add 10 hours
        Price newHigh = new Price(newTime, 9.0);
        //high  = new Price(1499890000000L,9.00000000); //high is now later than low
        System.out.printf("high price: %s",new Date(newHigh.getTime()*1000));
        instance.updateHighLow(newHigh);
        double expResult = 9.0;
        
        double result = instance.getEndPrice().getPrice();
        assertEquals(expResult, result, 0);
        
    }
    /**
     * Test of getEndPrice method when end price is the highest, of class PriceMove.
     */
    @Test
    public void testGetEndPriceLow() {
        System.out.println("getEndPrice");
        double newEndTime = getHoursFromMilliSecs(instance.getEndPrice().getTime())+1;
        low  = new Price(getMilliSecFromHours(newEndTime),3.00000000); //low is now later than high
        instance.updateHighLow(low);
        double expResult = 3.0;
        double result = instance.getEndPrice().getPrice();
        assertEquals(expResult, result, 0);
        
    }
}
