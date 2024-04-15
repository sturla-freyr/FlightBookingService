package com.example;

import com.example.repository.FlightRepo;

import java.time.LocalDateTime;


public class FlightController {

    FlightRepo fr;
    Flight[] fs;

    public FlightController() {
        fr = new FlightRepo();
        fs = fr.search();
    }


    public Flight[] searchFlights(Object... params) {
        if (params.length == 0) {
            // No parameters: fetch all flights
            fs = fr.search();
        } else if (params.length == 2 && params[0] instanceof String && params[1] instanceof String) {
            // Departure and arrival locations
            fs = fr.search((String) params[0], (String) params[1]);
        } else if (params.length == 2 && params[0] instanceof String && params[1] instanceof LocalDateTime) {
            // Departure location and departure time
            fs = fr.search((String) params[0], (LocalDateTime) params[1]);
        } else if (params.length == 3 && params[0] instanceof String && params[1] instanceof String && params[2] instanceof LocalDateTime) {
            // Departure location, destination location, and departure time
            fs = fr.search((String) params[0], (String) params[1], (LocalDateTime) params[2]);
        } else {
            // Parameters did not match any known combination
            throw new IllegalArgumentException("Invalid parameters for newFlights");
        }
        return fs;
    }
}
