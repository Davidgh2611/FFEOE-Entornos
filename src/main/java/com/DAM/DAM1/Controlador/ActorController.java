package com.DAM.DAM1.Controlador;

import com.DAM.DAM1.Dominio.Actor;
import com.DAM.DAM1.Servicio.ActorServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actores")
@AllArgsConstructor

public class ActorController {

    private final ActorServicio servicio;

    @GetMapping
    public List<Actor> listar() {
        return servicio.obtenerTodas();
    }

    @PostMapping
    public Actor guardar(@RequestBody Actor actor) {
        return servicio.guardar(actor);
    }
}