package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.Window;

public record WindowDto(Long id, String name, Window windowStatus, Long roomId) {
}