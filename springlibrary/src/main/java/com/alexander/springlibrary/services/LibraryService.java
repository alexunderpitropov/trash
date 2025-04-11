package com.alexander.springlibrary.service;

import com.alexander.springlibrary.dto.LibraryDTO;
import com.alexander.springlibrary.entity.Library;
import com.alexander.springlibrary.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Получение всех библиотек
    public List<LibraryDTO> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    // Получение библиотеки по ID
    public LibraryDTO getLibraryById(Long id) {
        Optional<Library> library = libraryRepository.findById(id);
        return library.map(lib -> modelMapper.map(lib, LibraryDTO.class))
                .orElse(null);
    }

    // Создание новой библиотеки
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = modelMapper.map(libraryDTO, Library.class);
        library = libraryRepository.save(library);
        return modelMapper.map(library, LibraryDTO.class);
    }

    // Обновление библиотеки
    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Optional<Library> existingLibrary = libraryRepository.findById(id);
        if (existingLibrary.isPresent()) {
            Library library = existingLibrary.get();
            library.setName(libraryDTO.getName());
            library.setAddress(libraryDTO.getAddress());
            // другие поля по необходимости
            library = libraryRepository.save(library);
            return modelMapper.map(library, LibraryDTO.class);
        }
        return null;
    }

    // Удаление библиотеки
    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
