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
public class AuthenticationRequest {
    @JsonView
    @Size(min = 3, max = 32, message = "Слишком короткий или длинный никнейм")
    @NotBlank(message = "Никнейм не должен быть пустым")
    private String name;

    @JsonView
    @Size(min = 5, max = 32, message = "Слишком короткий или длинный пароль")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
