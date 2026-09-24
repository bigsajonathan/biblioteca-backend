package com.biblioteca.controller;

import com.biblioteca.dto.CategoriaDTO;
import com.biblioteca.model.Categoria;
import com.biblioteca.service.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// CRUD de Categoría
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private final ICategoriaService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() throws Exception {
        List<CategoriaDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) throws Exception {
        CategoriaDTO obj = convertToDto(service.findById(id));

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO dto) throws Exception {
        Categoria obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdCategoria()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO dto) throws Exception {
        Categoria obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        return defaultMapper.map(dto, Categoria.class);
    }

    private CategoriaDTO convertToDto(Categoria obj) {
        return defaultMapper.map(obj, CategoriaDTO.class);
    }
}
