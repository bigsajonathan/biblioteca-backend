package com.biblioteca.controller;

import com.biblioteca.dto.ReservaDTO;
import com.biblioteca.model.Reserva;
import com.biblioteca.service.IReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reservas")
public class ReservaController {

    private final IReservaService service;
    private final ModelMapper defaultMapper;

    // Lista las reservas mostrando fecha, cliente y libros reservados
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll() throws Exception {
        List<ReservaDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Integer id) throws Exception {
        ReservaDTO obj = convertToDto(service.findById(id));

        return ResponseEntity.ok(obj);
    }

    // Consulta las reservas de un cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservaDTO>> findByCliente(@PathVariable Integer idCliente) {
        List<ReservaDTO> list = service.findByCliente(idCliente).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    // Registra una reserva con uno o más libros mediante DetalleReserva
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReservaDTO dto) {
        Reserva obj = service.saveTransactional(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdReserva()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private Reserva convertToEntity(ReservaDTO dto) {
        return defaultMapper.map(dto, Reserva.class);
    }

    private ReservaDTO convertToDto(Reserva obj) {
        return defaultMapper.map(obj, ReservaDTO.class);
    }
}
