package com.devoir1.moetezbenyemna.devoir1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * InMemory users (Atelier 07).
     * Roles:
     *   ADMIN  → all operations
     *   AGENT  → read + add songs only
     *   USER   → read only
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("123"))
                .authorities("ADMIN")
                .build();

        UserDetails agent = User.withUsername("agent")
                .password(encoder.encode("123"))
                .authorities("AGENT", "USER")
                .build();

        UserDetails user1 = User.withUsername("user1")
                .password(encoder.encode("123"))
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, agent, user1);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                // Public pages
                .requestMatchers("/login", "/webjars/**").permitAll()
                // ADMIN and AGENT can create/edit/delete
                .requestMatchers("/showCreate", "/saveSong", "/supprimerSong", "/modifierSong")
                    .hasAnyAuthority("ADMIN", "AGENT")
                // REST API: ADMIN only
                .requestMatchers("/api/**").hasAuthority("ADMIN")
                // List: everyone authenticated
                .requestMatchers("/ListeSongs", "/").hasAnyAuthority("ADMIN", "AGENT", "USER")
                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/ListeSongs"))
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/accessDenied"));
        return http.build();
    }
}
