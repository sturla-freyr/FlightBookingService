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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void thereIsAFlight()
    {
        MockFlight f = new MockFlight();
        assertTrue(f.arr == "Akureyri");
    }

    @Test
    public void thereIsADB(){
        MockDB db = new MockDB();
        assertTrue(db.MFS.length == 3);
        
    }
    @Test
    public void thereIsADate(){
        MockDB db = new MockDB();
        System.out.println(db.MFS[0].depT);
        assertTrue(db.MFS[0].depT != null);
    }

    @Test
    public void myNewTest(){
        FlightController fc = new FlightController();
        MockFlight[] a = fc.search("Reykjavík", "Akureyri");
        assertTrue(a.length == 1);
    }
}