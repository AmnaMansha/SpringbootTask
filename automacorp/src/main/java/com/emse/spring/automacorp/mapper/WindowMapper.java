package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.model.RoomModel;
import com.emse.spring.automacorp.model.WindowModel;

public class WindowMapper {

    public static WindowDto of(WindowModel entity) {
        return new WindowDto(
                entity.getId(),
                entity.getName(),
                entity.getWindowStatus(),
                entity.getRoom() != null ? entity.getRoom().getId() : null
        );
    }

    public static WindowModel toEntity(WindowDto dto, RoomModel roomEntity) {
        WindowModel entity = new WindowModel();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setWindowStatus(dto.windowStatus());
        entity.setRoom(roomEntity);
        return entity;
    }
}