package com.example;

import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.Date;

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
        this.fc = new FlightController(new MockDB());
    }

    @AfterEach
    void tearDown(){
        this.fc = null;
    }

    @Test
    public void thereIsAFlight()
    {
        Flight f = new Flight();
        assertTrue(f.arr == "Akureyri");
    }

    @Test
    public void thereIsADB(){
        assertTrue(this.fc.db != null);
        
    }

    @Test
    public void thereIsADate(){
        Flight[] f = fc.db.getFlights();
        Date d = f[0].depT;
        assertTrue(d != null);
    }

    @Test
    public void testSearch(){
        Flight[] fa = this.fc.search("Reykjavík", "Akureyri");
        assertTrue(fa.length == 1);
    }

    @Test
    public void testGetDep(){
        Flight[] fa = this.fc.search("Reykjavík", "Akureyri");
        assertTrue(fa[0].getDep() == "Reykjavík");
    }
    @Test
    public void testGetArr(){
        Flight[] fa = this.fc.search("Reykjavík", "Akureyri");
        assertTrue(fa[0].getArr() == "Akureyri");
    }
}