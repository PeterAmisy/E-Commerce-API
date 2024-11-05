package com.peter.amisy.e_commerce_api.repository;

import com.peter.amisy.e_commerce_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}