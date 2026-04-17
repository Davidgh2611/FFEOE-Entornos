package com.DAM.DAM1.Repositorio;

import com.DAM.DAM1.Dominio.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}