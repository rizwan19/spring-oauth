package com.example.oauthtestamigo.repo;

import com.example.oauthtestamigo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
