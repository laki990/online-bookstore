package com.nikolic.user_service.config.security;

import com.nikolic.user_service.model.User;
import com.nikolic.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username) .orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = new HashSet<>();

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }
}
