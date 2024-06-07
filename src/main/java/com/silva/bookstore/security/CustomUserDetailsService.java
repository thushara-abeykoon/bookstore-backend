package com.silva.bookstore.security;

import com.silva.bookstore.model.UserEntity;
import com.silva.bookstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities (String role) {
        return new HashSet<>(Set.of(new SimpleGrantedAuthority(role)));
    }
}
