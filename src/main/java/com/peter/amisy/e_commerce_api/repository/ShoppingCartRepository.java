package com.peter.amisy.e_commerce_api.repository;

import com.peter.amisy.e_commerce_api.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}