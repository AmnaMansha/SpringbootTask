package com.emse.spring.automacorp.repository;

import com.emse.spring.automacorp.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
}