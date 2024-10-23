package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.mapper.SensorMapper;
import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.model.SensorType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorMapperTest {

    @Test
    void shouldMapSensorEntityToDto() {
        Sensor sensorEntity = new Sensor();
        sensorEntity.setId(1L);
        sensorEntity.setName("Temperature Sensor");
        sensorEntity.setSensorType(SensorType.TEMPERATURE);
        sensorEntity.setValue(25.0);

        SensorDto sensorDto = SensorMapper.of(sensorEntity);

        assertEquals(1L, sensorDto.id());
        assertEquals("Temperature Sensor", sensorDto.name());
        assertEquals(SensorType.TEMPERATURE, sensorDto.sensorType());
        assertEquals(25.0, sensorDto.value());
    }
}