package com.flightapi.controller;

import com.flightapi.model.Flight;
import com.flightapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public void addFlight(@RequestBody Flight flight) {
        flightService.saveFlight(flight);
    }

    @PutMapping("/{id}")
    public void updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        flightService.getFlightById(id).ifPresent(existingFlight -> {
            existingFlight.setDepartureAirport(flight.getDepartureAirport());
            existingFlight.setArrivalAirport(flight.getArrivalAirport());
            existingFlight.setDepartureDateTime(flight.getDepartureDateTime());
            existingFlight.setReturnDateTime(flight.getReturnDateTime());
            existingFlight.setPrice(flight.getPrice());
            flightService.saveFlight(existingFlight);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
