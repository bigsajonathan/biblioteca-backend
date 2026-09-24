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
public class CategoriaDTO {

    private Integer idCategoria;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nombreCategoria;

    @NotBlank
    @Size(min = 3, max = 150)
    private String descripcionCategoria;

    @NotNull
    private Boolean estadoCategoria;
}
