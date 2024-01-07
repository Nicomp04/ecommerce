package com.example.ecommerce.Prenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Integer> {

    List<Prenda> findByNameContaining(String criterio);

    Prenda findById(Long id);
}