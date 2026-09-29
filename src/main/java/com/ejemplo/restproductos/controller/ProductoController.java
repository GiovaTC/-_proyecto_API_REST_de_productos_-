package com.ejemplo.restproductos.controller;

import com.ejemplo.restproductos.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {

        productos.add(
                new Producto(
                        1L,
                        "Laptop",
                        2500000
                )
        );

        productos.add(
                new Producto(
                        2L,
                        "Mouse",
                        80000
                )
        );

        productos.add(
                new Producto(
                        3L,
                        "Teclado",
                        120000
                )
        );
    }

    // ==========================================
    // GET - Listar productos
    // ==========================================
    @GetMapping
    public List<Producto> listar() {

        return productos;
    }


    // ==========================================
    // GET - Buscar producto por ID
    // ==========================================
    @GetMapping("/{id}")
    public Producto buscar(
            @PathVariable Long id) {

        return productos.stream()
                .filter(
                        p -> p.getId().equals(id)
                )
                .findFirst()
                .orElse(null);
    }
}
