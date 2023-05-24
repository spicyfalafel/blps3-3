package com.neevin.klerk.security.jaas;

import com.neevin.klerk.entity.User;
import com.neevin.klerk.exception.UserNotFoundException;
import com.neevin.klerk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;

public class JaasLoginModule implements LoginModule {

    private PasswordEncoder passwordEncoder;
    private String username;
    private boolean loginSucceeded = false;
    private Subject subject;
    private CallbackHandler callbackHandler;
    private UserRepository userRepository;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
        this.userRepository = (UserRepository) options.get("userRepository");
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean login() throws LoginException {
        var nameCallback = new NameCallback("username");
        var passwordCallback = new PasswordCallback("password", false);
        try {
            callbackHandler.handle(new Callback[] {nameCallback, passwordCallback});
        } catch (IOException | UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }
        username = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            loginSucceeded = passwordEncoder.matches(password, user.get().getPassword());
        } else {
            throw new UserNotFoundException("User not found: " + username);
        }
        return loginSucceeded;
    }

    @Override
    public boolean commit() throws LoginException {
        if(!loginSucceeded) return false;
        if(username == null) throw new UserNotFoundException("Username is not specified");
        Principal principal = (UserPrincipal) () -> username;
        subject.getPrincipals().add(principal);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
