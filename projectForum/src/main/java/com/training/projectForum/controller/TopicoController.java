package com.training.projectForum.controller;

import com.training.projectForum.Model.Curso;
import com.training.projectForum.Model.Topico;
import com.training.projectForum.controller.dto.TopicoDTO;
import com.training.projectForum.controller.dto.TopicoDetalharDTO;
import com.training.projectForum.controller.form.TopicoForm;
import com.training.projectForum.controller.form.TopicoFormAtualizar;
import com.training.projectForum.repository.CursoRepository;
import com.training.projectForum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> list(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converte(topicos);
        }
        List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
        return TopicoDTO.converte(topicos);

    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalharDTO> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new TopicoDetalharDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,@RequestBody @Valid TopicoFormAtualizar form ){
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));

    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id){
        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
