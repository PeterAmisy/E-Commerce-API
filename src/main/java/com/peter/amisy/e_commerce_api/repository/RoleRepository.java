package com.peter.amisy.e_commerce_api.repository;


import com.peter.amisy.e_commerce_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);
}