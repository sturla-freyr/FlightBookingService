package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.*;
import java.util.Date;

public class FlightControllerTest {
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
    public void testSearch()
    {
        assertTrue(fc.search() != null);
    }

    @Test
    public void testSearch2()
    {
        assertTrue(fc.search("Reykjavík", "Akureyri") != null);
    }

    @Test
    public void testSearch3()
    {
        assertTrue(fc.search("Reykjavík", new Date()) != null);
    }

    @Test
    public void testSearch4()
    {
        assertTrue(fc.search("Reykjavík", "Akureyri", new Date()) != null);
    }

    @Test
    public void thereIsADepTime(){
        Flight[] f = fc.search();
        Date d = f[0].depT;
        assertTrue(d != null);
    }

    @Test
    public void thereIsAArrTime(){
        Flight[] f = fc.search();
        Date d = f[0].arrT;
        assertTrue(d != null);
    }

    @Test
    public void thereIsADepLoc(){
        Flight[] f = fc.search();
        String d = f[0].dep;
        assertEquals("Reykjavík", d);
    }

    @Test
    public void thereIsAArrLoc(){
        Flight[] f = fc.search();
        String d = f[0].arr;
        assertEquals("Akureyri", d);
    }

    @Test
    public void thereAreSeats(){
        Flight[] f = fc.search();
        Integer s = f[0].seats;
        assertTrue(s != null);
    }
}
