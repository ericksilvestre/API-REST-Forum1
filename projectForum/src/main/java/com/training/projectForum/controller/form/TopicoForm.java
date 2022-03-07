package com.training.projectForum.controller.form;

import com.training.projectForum.Model.Curso;
import com.training.projectForum.Model.Topico;
import com.training.projectForum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicoForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
    }
}
