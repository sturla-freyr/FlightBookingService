package com.example;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.Test;
import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    private FlightController fc;

    @BeforeEach
    void setUp(){
        this.fc = new FlightController();
    }

    @AfterEach
    void tearDown(){
        this.fc = null;
    }

    @Test
    public void thereIsAFlight()
    {
        MockFlight f = new MockFlight();
        assertTrue(f.arr == "Akureyri");
    }

    @Test
    public void thereIsADB(){
        assertTrue(this.fc.db.MFS != null);
        
    }
    @Test
    public void thereIsADate(){
        assertTrue(this.fc.db.MFS[0].depT != null);
    }

    @Test
    public void myNewTest(){
        MockFlight[] a = this.fc.search("Reykjavík", "Akureyri");
        assertTrue(a.length == 1);
    }
}