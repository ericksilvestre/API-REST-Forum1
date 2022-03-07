package com.training.projectForum.repository;

import com.training.projectForum.Model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    List<Topico> findByTitulo(String nomeCurso);

    List<Topico> findByCursoNome(String nomeCursok);
}
