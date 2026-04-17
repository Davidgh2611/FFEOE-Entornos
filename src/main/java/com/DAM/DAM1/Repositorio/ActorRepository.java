package com.DAM.DAM1.Repositorio;

import com.DAM.DAM1.Dominio.Actor;
import com.DAM.DAM1.Dominio.Pelicula;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ActorRepository {

    private List<Actor> actores = new ArrayList<>();

    public ActorRepository() {

        actores.add(new Actor(1L, "Actor1", "España"));
        actores.add(new Actor(2L, "Actor2", "Argelia"));
        actores.add(new Actor(3L, "Actor3", "Francia"));
    }

    public List<Actor> listarActores(){
        return actores;
    }

    public Actor addActor(Actor actor){
        actores.add(actor);
        return actor;
    }
}
