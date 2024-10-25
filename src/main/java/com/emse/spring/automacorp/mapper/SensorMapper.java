package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.model.Sensor;

public class SensorMapper {

    public static SensorDto of(Sensor entity) {
        return new SensorDto(
                entity.getId(),
                entity.getName(),
                entity.getValue(),
                entity.getSensorType()
        );
    }

    public static Sensor toEntity(SensorDto dto) {
        Sensor entity = new Sensor();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setValue(dto.value());
        entity.setSensorType(dto.sensorType());
        return entity;
    }
}