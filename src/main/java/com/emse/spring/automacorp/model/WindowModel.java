package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "windows")
public class WindowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Window windowStatus;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomModel room;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Window getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(Window windowStatus) {
        this.windowStatus = windowStatus;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }
}