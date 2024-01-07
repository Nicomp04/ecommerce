package com.example.ecommerce.Venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public Venta crearVenta(Venta venta) {
        // Lógica para calcular el total, asociar prendas, etc.
        // Puedes guardar la venta en el repositorio

        //tambien de aca dispare el paypal y que espere la confirmacion
        return ventaRepository.save(venta);
    }

    // Otros métodos relacionados con las ventas
}