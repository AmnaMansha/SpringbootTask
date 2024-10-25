package com.emse.spring.automacorp.controller;
import com.emse.spring.automacorp.controller.RoomController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.model.RoomModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.emse.spring.automacorp.services.RoomService;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllRooms() throws Exception {
        RoomModel room1 = new RoomModel();
        room1.setId(1L);
        room1.setName("Room 1");

        RoomModel room2 = new RoomModel();
        room2.setId(2L);
        room2.setName("Room 2");

        Mockito.when(roomService.findAll()).thenReturn(List.of(room1, room2));

        mockMvc.perform(get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Room 1"))
                .andExpect(jsonPath("[1].name").value("Room 2"));
    }


    @Test
    void shouldFindRoomById() throws Exception {
        RoomModel RoomModel = new RoomModel();
        RoomModel.setId(1L);
        RoomModel.setName("Room 1");

        Mockito.when(roomService.findById(1L)).thenReturn(Optional.of(RoomModel));

        mockMvc.perform(get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Room 1"));
    }

    @Test
    void shouldReturnNotFoundForInvalidRoomId() throws Exception {
        Mockito.when(roomService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateRoom() throws Exception {
        RoomModel RoomModel = new RoomModel();
        RoomModel.setId(1L);
        RoomModel.setName("Room 1");

        Mockito.when(roomService.create(Mockito.any())).thenReturn(RoomModel);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Room(null, "Room 1", List.of()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Room 1"));
    }

    @Test
    void shouldUpdateRoom() throws Exception {
        RoomModel RoomModel = new RoomModel();
        RoomModel.setId(1L);
        RoomModel.setName("Updated Room");

        Mockito.when(roomService.update(Mockito.anyLong(), Mockito.any())).thenReturn(RoomModel);

        mockMvc.perform(put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Room(1L, "Updated Room", List.of()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Room"));
    }

    @Test
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/1"))
                .andExpect(status().isOk());

        Mockito.verify(roomService, Mockito.times(1)).delete(1L);
    }
}