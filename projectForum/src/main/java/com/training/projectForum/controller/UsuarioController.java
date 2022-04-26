package com.training.projectForum.controller;


import com.training.projectForum.Model.Topico;
import com.training.projectForum.Model.Usuario;
import com.training.projectForum.controller.dto.TopicoDTO;
import com.training.projectForum.controller.dto.UsuarioDTO;
import com.training.projectForum.controller.form.TopicoForm;
import com.training.projectForum.repository.UsuarioRepository;
import com.training.projectForum.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){

        return usuarioService.listAll();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid Usuario usuario) {

        ModelMapper modelMapper = new ModelMapper();

        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        usuarioService.cadastrar(usuario);

       return new ResponseEntity(usuarioDTO, HttpStatus.CREATED);
    }
}
