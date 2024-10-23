package com.emse.spring.automacorp.repository;
import com.emse.spring.automacorp.model.WindowModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindowRepository extends JpaRepository<WindowModel, Long> {
}