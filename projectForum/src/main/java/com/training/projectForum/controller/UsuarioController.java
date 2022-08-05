package com.training.projectForum.controller;


import com.training.projectForum.Model.Usuario;
import com.training.projectForum.controller.dto.UsuarioDTO;
import com.training.projectForum.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var usuarioModel = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioModel);
        usuarioService.cadastrar(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(usuarioModel));
    }
}
