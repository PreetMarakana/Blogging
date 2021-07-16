package com.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.model.role.Role;
import com.blogapi.model.role.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName name);
}
