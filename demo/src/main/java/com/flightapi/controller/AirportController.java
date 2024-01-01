package com.flightapi.controller;

import com.flightapi.model.Airport;
import com.flightapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Optional<Airport> getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @PostMapping
    public void addAirport(@RequestBody Airport airport) {
        airportService.saveAirport(airport);
    }

    @PutMapping("/{id}")
    public void updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        airportService.getAirportById(id).ifPresent(existingAirport -> {
            existingAirport.setCity(airport.getCity());
            airportService.saveAirport(existingAirport);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }
}
