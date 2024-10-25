package com.emse.spring.automacorp.repository;
import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findBySensorType(SensorType sensorType);
}