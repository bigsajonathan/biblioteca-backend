package com.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Integer idReserva;

    @NotNull
    private ClienteDTO cliente;

    @NotNull
    private LocalDate fechaReserva;

    // La reserva debe incluir uno o más libros
    @NotEmpty
    @JsonManagedReference
    private List<@Valid DetalleReservaDTO> detalleReserva;
}
