package com.emse.spring.automacorp.controller;
import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.services.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<SensorDto> findAll() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public SensorDto findById(@PathVariable Long id) {
        return sensorService.findById(id).orElse(null);
    }

    @PostMapping
    public SensorDto create(@RequestBody SensorDto sensorDto) {
        return sensorService.create(sensorDto);
    }

    @PutMapping("/{id}")
    public SensorDto update(@PathVariable Long id, @RequestBody SensorDto sensorDto) {
        return sensorService.update(id, sensorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sensorService.deleteById(id);
    }
}