package com.emse.spring.automacorp.services;
import com.emse.spring.automacorp.model.RoomModel;
import com.emse.spring.automacorp.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomModel> findAll() {
        return roomRepository.findAll();
    }

    public Optional<RoomModel> findById(Long id) {
        return roomRepository.findById(id);
    }

    public RoomModel create(RoomModel roomEntity) {
        return roomRepository.save(roomEntity);
    }

    public RoomModel update(Long id, RoomModel updatedRoomEntity) {
        Optional<RoomModel> existingRoom = roomRepository.findById(id);
        if (existingRoom.isPresent()) {
            RoomModel roomEntity = existingRoom.get();
            roomEntity.setName(updatedRoomEntity.getName());
            return roomRepository.save(roomEntity);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}