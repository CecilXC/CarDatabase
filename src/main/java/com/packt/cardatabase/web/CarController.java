package com.packt.cardatabase.web;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final CarRepository repository;

    public CarController(CarRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return repository.findAll();
    }

}
