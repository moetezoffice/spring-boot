package com.devoir1.moetezbenyemna.devoir1.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.devoir1.moetezbenyemna.devoir1.entities.User;
import com.devoir1.moetezbenyemna.devoir1.service.UserService;

/**
 * Atelier 08 - JPA-based UserDetailsService.
 * To activate: comment out the InMemoryUserDetailsManager bean in SecurityConfig
 * and uncomment this service. Also initialize users via @PostConstruct in main class.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Utilisateur introuvable : " + username);

        List<GrantedAuthority> auths = new ArrayList<>();
        user.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            auths.add(authority);
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), auths);
    }
}
