package com.alexander.springlibrary.controller;

import com.alexander.springlibrary.dto.LibraryDTO;
import com.alexander.springlibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/{id}")
    public LibraryDTO getLibraryById(@PathVariable Long id) {
        return libraryService.getLibraryById(id);
    }

    @PostMapping
    public LibraryDTO createLibrary(@RequestBody LibraryDTO libraryDTO) {
        return libraryService.createLibrary(libraryDTO);
    }

    @PutMapping("/{id}")
    public LibraryDTO updateLibrary(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO) {
        return libraryService.updateLibrary(id, libraryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
    }
}
