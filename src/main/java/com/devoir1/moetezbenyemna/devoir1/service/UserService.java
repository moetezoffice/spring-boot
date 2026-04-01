package com.devoir1.moetezbenyemna.devoir1.service;

import com.devoir1.moetezbenyemna.devoir1.entities.Role;
import com.devoir1.moetezbenyemna.devoir1.entities.User;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    void deleteAllUsers();
    void deleteAllRoles();
}
