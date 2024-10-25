package com.emse.spring.automacorp.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class RoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<WindowModel> windows = new HashSet<>(); // Initialize to an empty HashSet

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

    public Set<WindowModel> getWindows() {
        return windows;
    }

    public void setWindows(Set<WindowModel> windows) {
        this.windows = windows;
    }
}