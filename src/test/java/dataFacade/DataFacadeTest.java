/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataFacade;

import entities.Base;
import entities.CoinPair;
import entities.Price;
import entities.PriceMove;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static tools.Util.getHoursFromMilliSecs;
import static tools.Util.getMilliSecFromHours;

public class DataFacadeTest {
    DataFacade df;
    PriceMove pm; 
    public DataFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        df = new DataFacade();
        long lowValue = new Date().getTime(); //NOW
        long highValue = getMilliSecFromHours(getHoursFromMilliSecs(lowValue)+10); //Add 10 hours
        Price low  = new Price(lowValue,4.00000000); //low is first
        Price high = new Price(highValue,4.50000000);
        pm = new PriceMove(low, high);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createPriceMove method, of class DataFacade.
     */
    @Test
    public void testCreatePriceMove() {
        System.out.println("createPriceMove");
        int firstSize = df.getAllPriceMoves().size();
        df.createPriceMove(pm);
        int secondSize = df.getAllPriceMoves().size();
        assertEquals(firstSize+1,secondSize);
        
    }

    /**
     * Test of createBase method, of class DataFacade.
     */
//    @Test
//    public void testCreateBase() {
//        System.out.println("createBase");
//        Base base = null;
//        DataFacade instance = new DataFacade();
//        instance.createBase(base);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updatePriceMove method, of class DataFacade.
//     */
//    @Test
//    public void testUpdatePriceMove() {
//        System.out.println("updatePriceMove");
//        PriceMove pm = null;
//        DataFacade instance = new DataFacade();
//        instance.updatePriceMove(pm);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateBase method, of class DataFacade.
//     */
//    @Test
//    public void testUpdateBase() {
//        System.out.println("updateBase");
//        Base base = null;
//        DataFacade instance = new DataFacade();
//        instance.updateBase(base);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBasesFromCoinPair method, of class DataFacade.
//     */
//    @Test
//    public void testGetBasesFromCoinPair() {
//        System.out.println("getBasesFromCoinPair");
//        CoinPair cp = null;
//        DataFacade instance = new DataFacade();
//        List<Base> expResult = null;
//        List<Base> result = instance.getBasesFromCoinPair(cp);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
