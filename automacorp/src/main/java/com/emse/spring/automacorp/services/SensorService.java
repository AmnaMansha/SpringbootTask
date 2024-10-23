package com.emse.spring.automacorp.services;
import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.mapper.SensorMapper;
import com.emse.spring.automacorp.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    // Fetch all sensors and convert them to DTOs
    public List<SensorDto> findAll() {
        return sensorRepository.findAll().stream()
                .map(SensorMapper::of)
                .collect(Collectors.toList());
    }

    // Fetch a sensor by ID and convert to DTO
    public Optional<SensorDto> findById(Long id) {
        return sensorRepository.findById(id)
                .map(SensorMapper::of);
    }

    // Create a new sensor from DTO
    public SensorDto create(SensorDto dto) {
        Sensor sensorEntity = SensorMapper.toEntity(dto);
        Sensor savedEntity = sensorRepository.save(sensorEntity);
        return SensorMapper.of(savedEntity);
    }

    // Update an existing sensor
    public SensorDto update(Long id, SensorDto dto) {
        Optional<Sensor> existingSensorOpt = sensorRepository.findById(id);

        if (existingSensorOpt.isPresent()) {
            Sensor sensorEntity = existingSensorOpt.get();
            sensorEntity.setName(dto.name());
            sensorEntity.setValue(dto.value());
            sensorEntity.setSensorType(dto.sensorType());

            Sensor updatedEntity = sensorRepository.save(sensorEntity);
            return SensorMapper.of(updatedEntity);
        } else {
            throw new IllegalArgumentException("Sensor not found");
        }
    }

    public void deleteById(Long id) {
        sensorRepository.deleteById(id);
    }
}