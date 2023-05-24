package com.neevin.klerk.service.auth;

import com.neevin.klerk.dto.MessageDto;
import com.neevin.klerk.entity.Role;
import com.neevin.klerk.entity.User;
import com.neevin.klerk.exception.UserAlreadyExistException;
import com.neevin.klerk.repository.UserRepository;
import com.neevin.klerk.security.AuthenticationAdminRequest;
import com.neevin.klerk.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
     private final PasswordEncoder passwordEncoder;

    public MessageDto register(RegisterRequest request) {
        if (repository.findByUsername(request.getName()).isPresent()){
            throw new UserAlreadyExistException();
        }

        var user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.AUTHORIZED_USER)
                .createdAt(new Date())
                .enabled(true)
                .build();

        repository.save(user);
        return new MessageDto("Registered");
    }

    public MessageDto registerAdmin(AuthenticationAdminRequest adminRequest) {
        if (repository.findByUsername(adminRequest.getName()).isPresent()){
            throw new UserAlreadyExistException();
        }
        var user = User.builder()
                .username(adminRequest.getName())
                .password(passwordEncoder.encode(adminRequest.getPassword()))
                .createdAt(new Date())
                .role(Role.ADMIN)
                .enabled(true)
                .build();
        repository.save(user);
        return new MessageDto("Registered");
    }
}
