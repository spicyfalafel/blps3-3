package com.neevin.klerk.security;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AuthenticationAdminRequest {
        @JsonView
        @Length(min = 3, max = 32, message = "Слишком короткий или длинный никнейм администратора")
        @NotNull(message = "Никнейм не должен быть пустым")
        private String name;

        @JsonView
        @Size(min = 6, max = 32, message = "Слишком короткий или длинный пароль")
        @NotNull(message = "Пароль не должен быть пустым")
        private String password;
}
