package com.example.ecommerce.Prenda;

import com.example.ecommerce.Excepciones.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "/prendas")
public class PrendaController {
    private final PrendaService prendaService;

    @Autowired
    public PrendaController(PrendaService prendaService){
        this.prendaService = prendaService;
    }

    @GetMapping()
    public ModelAndView mostrarPrendas() {
        ModelAndView modelAndView = new ModelAndView("prendas");
        List<Prenda> prendas = prendaService.getPrendas();
        modelAndView.addObject("prendas", prendas);
        return modelAndView;
    }

    @GetMapping("/agregar")
    public ModelAndView mostrarFormularioAgregar() {
        ModelAndView modelAndView = new ModelAndView("agregar_prenda");
        modelAndView.addObject("nuevaPrenda", new Prenda());
        return modelAndView;
    }

    @PostMapping("/agregar")
    public String agregarPrenda(@ModelAttribute Prenda nuevaPrenda) {
        // Aquí deberías procesar la lógica para guardar la nueva prenda en la base de datos
        prendaService.save(nuevaPrenda);
        return "/prendas"; // Redirige a la lista de prendas después de agregar
    }

    @PutMapping("/prendas/{id}")
    public String actualizarPrenda(@PathVariable Long id, @ModelAttribute Prenda prendaActualizada) {
        // Implementa la lógica para actualizar la prenda en la base de datos
        prendaService.actualizarPrenda(id, prendaActualizada);
        return "redirect:/prendas";
    }

    @GetMapping("/eliminar")
    public ModelAndView mostrarFormularioEliminar() {
        ModelAndView modelAndView = new ModelAndView("eliminar_prenda");
        List<Prenda> prendas = prendaService.getPrendas();
        modelAndView.addObject("prendas", prendas);
        return modelAndView;
    }

    @PostMapping("/eliminar")
    public String eliminarPrenda(@RequestParam Long id) {
        prendaService.eliminarPrenda(id);
        return "/prendas";
    }

    @GetMapping("/prendas/{id}")
    public ModelAndView detallesPrenda(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("detalles_prenda");
        Prenda prenda = prendaService.obtenerPrendaPorId(id);
        modelAndView.addObject("prenda", prenda);
        return modelAndView;
    }


    @GetMapping("/prendas/buscar")
    public String buscarPrendas(@RequestParam String name, Model model) {
        List<Prenda> prendasEncontradas = prendaService.buscarPrendas(name);
        model.addAttribute("prendas", prendasEncontradas);
        return "lista_prendas"; // Página que muestra la lista de prendas
    }

    @PostMapping("/comprar")
    public String comprarPrenda(@RequestParam Long id, @RequestParam int cantidad) {
        // Implementa la lógica para procesar la compra y actualizar el inventario
        try {
            prendaService.comprarPrenda(id, cantidad);
            return "redirect:/prendas";
        } catch (StockInsuficienteException e) {
            // Manejo de existencias insuficientes
            return "redirect:/error?mensaje=Existencias insuficientes";
        }
    }
}
