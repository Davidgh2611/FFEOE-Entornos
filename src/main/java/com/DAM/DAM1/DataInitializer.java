package com.DAM.DAM1;

import com.DAM.DAM1.Dominio.Actor;
import com.DAM.DAM1.Dominio.Director;
import com.DAM.DAM1.Dominio.Pelicula;
import com.DAM.DAM1.Repositorio.ActorRepository;
import com.DAM.DAM1.Repositorio.DirectorRepository;
import com.DAM.DAM1.Repositorio.PeliculaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ActorRepository actorRepo,
                                   DirectorRepository directorRepo,
                                   PeliculaRepository peliculaRepo) {
        return args -> {
            actorRepo.deleteAll();
            directorRepo.deleteAll();
            peliculaRepo.deleteAll();

            actorRepo.save(new Actor(null, "Actor1", "España"));
            actorRepo.save(new Actor(null, "Actor2", "Argelia"));
            actorRepo.save(new Actor(null, "Actor3", "Francia"));

            directorRepo.save(new Director(null, "Director1", 44));
            directorRepo.save(new Director(null, "Director2", 52));
            directorRepo.save(new Director(null, "Director3", 38));

            peliculaRepo.save(new Pelicula(null, "Pelicula1", "Acción", 2014));
            peliculaRepo.save(new Pelicula(null, "Pelicula2", "Drama", 2015));
            peliculaRepo.save(new Pelicula(null, "Pelicula3", "Comedia", 2016));

            System.out.println("-----------------------------------------");
            System.out.println("¡BASE DE DATOS INICIALIZADA CON ÉXITO!");
            System.out.println("-----------------------------------------");
        };
    }
}