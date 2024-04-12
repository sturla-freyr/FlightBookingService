package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.repository.*;
import org.junit.jupiter.api.*;
import java.util.Date;

public class FlightRepositoryTest {
    private FlightRepo fc;

    @BeforeEach
    void setUp(){
        this.fc = new FlightRepo();
    }

    @AfterEach
    void tearDown(){
        this.fc = null;
    }

    @Test
    public void testSearch()
    {
        Flight[] result = fc.search();
        assertNotNull(result, "Search result should not be null");
        assertTrue(result.length > 0, "Search result should not be empty");

        for (Flight flight : result) {
            assertTrue(flight instanceof Flight, "Search result items should not be null");    
        }
    }

    @Test
    public void testSearch2()
    {
        Flight[] result = fc.search("Reykjavík", "Akureyri");

        assertNotNull(result, "Search result should not be null");
        assertTrue(result.length > 0, "Search result should not be empty");

        for (Flight flight : result) {
            assertEquals("Reykjavík", flight.getDep(), "Search results should match");
            assertEquals("Akureyri", flight.getArr(), "Search results should match");    
        }
    }

    @Test
    public void testSearch3()
    {
        Date searchDate = new Date(); 
        Flight[] result = fc.search("Reykjavík", searchDate);

        assertNotNull(result, "Search result should not be null");
        assertTrue(result.length > 0, "Search result should not be empty");

        for (Flight flight : result) {
            assertEquals("Reykjavík", flight.getDep(), "Search results should match");
        }
    }

    @Test
    public void testSearch4()
    {
        Date searchDate = new Date(); 
        Flight[] result = fc.search("Reykjavík", "Akureyri", searchDate);

        assertNotNull(result, "Search result should not be null");
        assertTrue(result.length > 0, "Search result should not be empty");

        for (Flight flight : result) {
            assertEquals("Reykjavík", flight.getDep(), "Search results should match");
            assertEquals("Akureyri", flight.getArr(), "Search results should match");    
        }
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
/* 
    @Test
    public void canAddNewFlights(){
        String dep = "Amsterdam";
        String arr = "Rotterdam";
        fc.addFlight(new Flight(dep, arr));
        
        Flight[] results = fc.search(dep, arr);
        
        boolean found = false;
        for (Flight flight : results) {
            if (dep.equals(flight.getDep()) && arr.equals(flight.getArr())) {
                found = true;
                break; // Exit the loop early since we've found what we were looking for
            }
        }
        assertTrue(found, "The added flight should be in the search results");
    }

    @Test
    public void canNotAddBadFlights(){
        String dep = "Amsterdam";
        String arr = "Rotterdam";
        fc.addFlight(new Flight(dep, arr, -1.0));
        
        Flight[] results = fc.search(dep, arr);
        
        boolean found = false;
        for (Flight flight : results) {
            if (dep.equals(flight.getDep()) && arr.equals(flight.getArr())) {
                found = true;
                break; // Exit the loop early since we've found what we were looking for
            }
        }
        assertFalse(found, "The added flight should be in the search results");
    }
*/
    @Test
    public void canDeleteAFlight(){
        assertTrue(false);
    }

    @Test
    public void canUpdateFlight(){
        assertTrue(false);
    }
}
