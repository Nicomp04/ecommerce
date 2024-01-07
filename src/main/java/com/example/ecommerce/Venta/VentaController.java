package com.example.ecommerce.Venta;

import com.example.ecommerce.Prenda.Prenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/nueva")
    public ResponseEntity<?> crearNuevaVenta(@RequestBody Venta nuevaVenta) {

        Venta ventaCreada = ventaService.crearVenta(nuevaVenta);
        return ResponseEntity.ok(ventaCreada);
    }

    // Otros métodos para obtener historial de ventas, detalles de venta, etc.
}
