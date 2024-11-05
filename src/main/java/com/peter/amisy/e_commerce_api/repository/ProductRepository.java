package com.peter.amisy.e_commerce_api.repository;

import com.peter.amisy.e_commerce_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}