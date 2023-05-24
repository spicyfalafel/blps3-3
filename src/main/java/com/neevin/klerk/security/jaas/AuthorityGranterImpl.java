package com.neevin.klerk.security.jaas;


import com.neevin.klerk.entity.User;
import com.neevin.klerk.exception.UserNotFoundException;
import com.neevin.klerk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
public class AuthorityGranterImpl implements AuthorityGranter {

    private final UserRepository userRepository;
    @Override
    public Set<String> grant(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return Collections.singleton(user.get().getRole().name());
        }
        throw new UserNotFoundException("Wrong credentials");
    }
}
