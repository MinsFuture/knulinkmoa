package com.knulinkmoa.auth.service;

import com.knulinkmoa.auth.dto.CustomUserDetails;
import com.knulinkmoa.auth.entity.UserEntity;
import com.knulinkmoa.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
