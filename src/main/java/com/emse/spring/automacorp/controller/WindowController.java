package com.emse.spring.automacorp.controller;
import com.emse.spring.automacorp.dto.WindowDto;
import com.emse.spring.automacorp.services.WindowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RestController
@RequestMapping("/api/windows")
public class WindowController {

    private final WindowService windowService;

    public WindowController(WindowService windowService) {
        this.windowService = windowService;
    }

    @GetMapping
    public List<WindowDto> findAll() {
        return windowService.findAll();
    }

    @GetMapping("/{id}")
    public WindowDto findById(@PathVariable Long id) {
        return windowService.findById(id).orElse(null);
    }

    @PostMapping
    public WindowDto create(@RequestBody WindowDto windowDto) {
        return windowService.create(windowDto);
    }

    @PutMapping("/{id}")
    public WindowDto update(@PathVariable Long id, @RequestBody WindowDto windowDto) {
        return windowService.update(id, windowDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        windowService.deleteWindow(id);
    }
}
