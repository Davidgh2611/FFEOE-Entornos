package com.DAM.DAM1.Controlador;

import com.DAM.DAM1.Dominio.Director;
import com.DAM.DAM1.Servicio.DirectorServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directores")
@AllArgsConstructor

public class DirectorController {

    private final DirectorServicio servicio;

    @GetMapping
    public List<Director> listar() {
        return servicio.obtenerTodas();
    }

    @PostMapping
    public Director guardar(@RequestBody Director director) {
        return servicio.guardar(director);
    }
}