package com.neevin.klerk.security;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @JsonView
    @NotBlank(message = "Никнейм не должен быть пустым")
    @Size(min = 3, max = 32, message = "Слишком короткий или длинный никнейм")
    private String name;

    @JsonView
    @NotBlank(message = "Никнейм не должен быть пустым")
    @Size(min = 6, max = 32, message = "Слишком короткий или длинный пароль")
    private String password;
}
