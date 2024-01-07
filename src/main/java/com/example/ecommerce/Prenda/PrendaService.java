package com.example.ecommerce.Prenda;

import com.example.ecommerce.Excepciones.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PrendaService {
    private final PrendaRepository prendaRepository;

    @Autowired
    public PrendaService(PrendaRepository prendaRepository) {
        this.prendaRepository = prendaRepository;
    }

    public List<Prenda> getPrendas() {
        return prendaRepository.findAll();
    }

    public Prenda save(Prenda nuevaPrenda) {
        return prendaRepository.save(nuevaPrenda);
    }

    public void actualizarPrenda(Long id, Prenda prendaActualizada) {
        // Implementa la lógica para actualizar la prenda
        Prenda prendaExistente = prendaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));

        // Actualiza los campos de la prenda existente con los valores de la prenda actualizada
        prendaExistente.setName(prendaActualizada.getName());
        prendaExistente.setPrice(prendaActualizada.getPrice());
        prendaExistente.setQuantity(prendaActualizada.getQuantity());

        prendaRepository.save(prendaExistente);
    }

    public void eliminarPrenda(Long id) {
        // Implementa la lógica para eliminar la prenda
        prendaRepository.deleteById(Math.toIntExact(id));
    }

    public Prenda obtenerPrendaPorId(Long id) {
        // Implementa la lógica para obtener la prenda por ID
        return prendaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));
    }

    public List<Prenda> buscarPrendas(String criterio) {
        // Implementa la lógica para buscar prendas según un criterio
        return prendaRepository.findByNameContaining(criterio);
    }
    public void comprarPrenda(Long id, int cantidad) throws StockInsuficienteException {
        // Implementa la lógica para procesar la compra y actualizar el inventario
        Prenda prenda = obtenerPrendaPorId(id);

        // Verifica si hay suficientes existencias para la compra
        if (prenda.getQuantity() >= cantidad) {
            // Realiza la compra
            prenda.setQuantity(prenda.getQuantity() - cantidad);
            prendaRepository.save(prenda);
        } else {
            // Lanza una excepción si no hay suficientes existencias
            throw new StockInsuficienteException("Existencias insuficientes para la compra");
        }
    }
}
