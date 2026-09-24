package com.biblioteca.controller;

import com.biblioteca.dto.LibroDTO;
import com.biblioteca.model.Libro;
import com.biblioteca.service.ILibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// CRUD de Libro
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/libros")
public class LibroController {

    private final ILibroService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> findAll() throws Exception {
        List<LibroDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> findById(@PathVariable Integer id) throws Exception {
        LibroDTO obj = convertToDto(service.findById(id));

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody LibroDTO dto) throws Exception {
        Libro obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdLibro()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> update(@PathVariable Integer id, @Valid @RequestBody LibroDTO dto) throws Exception {
        Libro obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private Libro convertToEntity(LibroDTO dto) {
        return defaultMapper.map(dto, Libro.class);
    }

    private LibroDTO convertToDto(Libro obj) {
        return defaultMapper.map(obj, LibroDTO.class);
    }
}
