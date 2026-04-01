package com.devoir1.moetezbenyemna.devoir1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devoir1.moetezbenyemna.devoir1.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
