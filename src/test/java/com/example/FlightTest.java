package com.example;
import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.Date;

public class FlightTest {
    private Flight testFlight;

    @BeforeEach
    void setUp(){
        this .testFlight = new Flight("Amsterdam", "Rotterdam", 5);
    }

    @AfterEach
    void tearDown(){
        this.testFlight = null;
    }

    @Test
    void testSetGetDep() {
        testFlight.setDep("Reykjavík");
        assertEquals("Reykjavík", testFlight.getDep());
    }

    @Test
    void testSetGetArr() {
        testFlight.setArr("Akureyri");
        assertEquals("Akureyri", testFlight.getArr());
    }

    @Test
    void testSetGetDepT() {
        Date depTime = new Date();  // You can use a specific time if you like
        testFlight.setDepT(depTime);
        assertEquals(depTime, testFlight.getDepT());
    }

    @Test
    void testSetGetArrT() {
        Date arrTime = new Date();  // You can use a specific time if you like
        testFlight.setArrT(arrTime);
        assertEquals(arrTime, testFlight.getArrT());
    }

    @Test
    void testSetGetSeats() {
        testFlight.setSeats(42);
        assertEquals(42, testFlight.getSeats());
    }
}
