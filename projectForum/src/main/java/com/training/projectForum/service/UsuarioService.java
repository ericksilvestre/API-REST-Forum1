package com.training.projectForum.service;

import com.training.projectForum.Model.Usuario;
import com.training.projectForum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }
}
