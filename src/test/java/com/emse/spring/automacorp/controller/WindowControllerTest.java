package com.emse.spring.automacorp.controller;
import com.emse.spring.automacorp.controller.WindowController;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.model.Window;
import com.emse.spring.automacorp.services.WindowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WindowController.class)
class WindowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WindowService windowService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllWindows() throws Exception {
        Mockito.when(windowService.findAll()).thenReturn(List.of(
                new WindowDto(1L, "Window 1", Window.OPEN, 1L),
                new WindowDto(2L, "Window 2", Window.CLOSED, 1L)
        ));

        mockMvc.perform(get("/api/windows").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Window 1"))
                .andExpect(jsonPath("[1].name").value("Window 2"));
    }

    @Test
    void shouldCreateWindow() throws Exception {
        WindowDto windowDto = new WindowDto(null, "Window 1", Window.OPEN, 1L);
        WindowDto savedWindowDto = new WindowDto(1L, "Window 1", Window.OPEN, 1L);

        Mockito.when(windowService.create(Mockito.any(WindowDto.class))).thenReturn(savedWindowDto);

        mockMvc.perform(post("/api/windows")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(windowDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Window 1"));
    }

    @Test
    void shouldDeleteWindow() throws Exception {
        mockMvc.perform(delete("/api/windows/1"))
                .andExpect(status().isOk());

        Mockito.verify(windowService, Mockito.times(1)).deleteWindow(1L);
    }
}