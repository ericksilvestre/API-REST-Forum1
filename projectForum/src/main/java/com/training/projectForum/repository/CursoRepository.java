package com.training.projectForum.repository;

import com.training.projectForum.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso,Long>{

    Curso findByNome(String nome);
}
