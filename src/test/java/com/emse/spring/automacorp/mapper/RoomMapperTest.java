package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.mapper.RoomMapper;
import com.emse.spring.automacorp.model.RoomModel;
import com.emse.spring.automacorp.model.WindowModel;
import org.junit.jupiter.api.Test;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class RoomMapperTest {

    @Test
    void shouldMapRoomModelToDto() {
        RoomModel RoomModel = new RoomModel();
        RoomModel.setId(1L);
        RoomModel.setName("Room 1");

        WindowModel WindowModel1 = new WindowModel();
        WindowModel1.setId(1L);
        WindowModel1.setName("Window 1");

        WindowModel WindowModel2 = new WindowModel();
        WindowModel2.setId(2L);
        WindowModel2.setName("Window 2");

        RoomModel.setWindows(Set.of(WindowModel1, WindowModel2));

        Room roomDto = RoomMapper.of(RoomModel);

        assertEquals(1L, roomDto.id());
        assertEquals("Room 1", roomDto.name());
        assertEquals(2, roomDto.windowDtos().size());
    }
}