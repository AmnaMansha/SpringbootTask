package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.model.RoomModel;

import java.util.stream.Collectors;

public class RoomMapper {
    public static Room of(RoomModel entity) {
        return new Room(
                entity.getId(),
                entity.getName(),
                entity.getWindows().stream().map(WindowMapper::of).collect(Collectors.toList())
        );
    }
}