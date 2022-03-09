package com.training.projectForum.controller.form;

import com.sun.istack.NotNull;
import com.training.projectForum.Model.Topico;
import com.training.projectForum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class TopicoFormAtualizar {

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

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

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
