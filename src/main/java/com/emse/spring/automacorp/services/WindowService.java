package com.emse.spring.automacorp.services;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.model.RoomModel;
import com.emse.spring.automacorp.model.WindowModel;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.repository.WindowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WindowService {

    private final WindowRepository windowRepository;
    private final RoomService roomService;

    public WindowService(WindowRepository windowRepository, RoomService roomService) {
        this.windowRepository = windowRepository;
        this.roomService = roomService;
    }

    // Fetch all windows and convert them to DTOs
    public List<WindowDto> findAll() {
        return windowRepository.findAll().stream()
                .map(WindowMapper::of)
                .collect(Collectors.toList());
    }

    // Fetch a window by ID and convert to DTO
    public Optional<WindowDto> findById(Long id) {
        return windowRepository.findById(id)
                .map(WindowMapper::of);
    }

    // Create a new window from DTO
    public WindowDto create(WindowDto dto) {
        RoomModel RoomModel = roomService.findById(dto.roomId()).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        WindowModel WindowModel = WindowMapper.toEntity(dto, RoomModel);
        WindowModel savedEntity = windowRepository.save(WindowModel);
        return WindowMapper.of(savedEntity);
    }

    // Update an existing window
    public WindowDto update(Long id, WindowDto dto) {
        RoomModel RoomModel = roomService.findById(dto.roomId()).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        Optional<WindowModel> existingWindowOpt = windowRepository.findById(id);

        if (existingWindowOpt.isPresent()) {
            WindowModel WindowModel = existingWindowOpt.get();
            WindowModel.setName(dto.name());
            WindowModel.setWindowStatus(dto.windowStatus());
            WindowModel.setRoom(RoomModel);

            WindowModel updatedEntity = windowRepository.save(WindowModel);
            return WindowMapper.of(updatedEntity);
        } else {
            throw new IllegalArgumentException("Window not found");
        }
    }

    public void deleteWindow(Long id) {
        windowRepository.deleteById(id);
    }
}