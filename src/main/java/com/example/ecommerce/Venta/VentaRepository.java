package com.example.ecommerce.Venta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
