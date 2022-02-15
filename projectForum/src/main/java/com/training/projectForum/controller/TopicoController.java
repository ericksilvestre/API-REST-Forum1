package com.training.projectForum.controller;

import com.training.projectForum.Model.Curso;
import com.training.projectForum.Model.Topico;
import com.training.projectForum.controller.dto.TopicoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {

    @RequestMapping("/topicos")

    public List<TopicoDTO> list() {
        Topico topico = new Topico("Duvida", "Spring Boot",new Curso("Back and","Spring boot"));

        return TopicoDTO.converte(Arrays.asList(topico));
    }
}
