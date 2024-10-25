package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.model.RoomModel;
import com.emse.spring.automacorp.model.Window;
import com.emse.spring.automacorp.model.WindowModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowMapperTest {

    @Test
    void shouldMapWindowModelToDto() {
        RoomModel roomEntity = new RoomModel();
        roomEntity.setId(1L);

        WindowModel WindowModel = new WindowModel();
        WindowModel.setId(1L);
        WindowModel.setName("Window 1");
        WindowModel.setWindowStatus(Window.CLOSED);
        WindowModel.setRoom(roomEntity);

        WindowDto windowDto = WindowMapper.of(WindowModel);

        assertEquals(1L, windowDto.id());
        assertEquals("Window 1", windowDto.name());
        assertEquals(Window.CLOSED, windowDto.windowStatus());
        assertEquals(1L, windowDto.roomId());
    }
}