package com.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Integer idCliente;

    @NotBlank
    @Size(min = 2, max = 60)
    private String nombresCliente;

    @NotBlank
    @Size(min = 2, max = 60)
    private String apellidosCliente;

    @NotBlank
    @Size(min = 5, max = 15)
    private String cedulaCliente;

    @NotBlank
    @Email
    @Size(max = 100)
    private String emailCliente;
}
