package com.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroDTO {

    private Integer idLibro;

    @NotBlank
    @Size(min = 1, max = 150)
    private String tituloLibro;

    @NotBlank
    @Size(min = 1, max = 100)
    private String autorLibro;

    @NotBlank
    @Size(min = 5, max = 20)
    private String isbnLibro;

    @NotNull
    private Boolean disponibleLibro;

    @NotNull
    private CategoriaDTO categoria;
}
