package com.training.projectForum.controller.dto;

import com.training.projectForum.Model.Topico;
import com.training.projectForum.Model.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;


    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email  = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
