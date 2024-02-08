package com.api.boardcamp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cpf is required")
    @Size(min = 11, max = 11, message = "cpf must have 11 numbers")
    private String cpf;
}
