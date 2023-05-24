package com.neevin.klerk.controller;

import com.neevin.klerk.security.AuthenticationAdminRequest;
import com.neevin.klerk.security.RegisterRequest;
import com.neevin.klerk.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;
    @Hidden
    @PostMapping(value = "/admin/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAdmin(
            @Valid @RequestBody AuthenticationAdminRequest request
    ) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
