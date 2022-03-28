package com.training.projectForum.controller;


import com.training.projectForum.Model.Topico;
import com.training.projectForum.controller.dto.TopicoDTO;
import com.training.projectForum.controller.dto.TopicoDetalharDTO;
import com.training.projectForum.controller.form.TopicoForm;
import com.training.projectForum.controller.form.TopicoFormAtualizar;
import com.training.projectForum.repository.CursoRepository;
import com.training.projectForum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")//UTILIZAR EM TABELAS QUE DIFICILMENTE É ALTERADA
    public Page<TopicoDTO> list(@RequestParam(required = false) String nomeCurso,
                                 Pageable paginacao) {
        if (nomeCurso == null) {
         Page<Topico> topicos = topicoRepository.findAll(paginacao);
         return TopicoDTO.converte(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso,paginacao);
            return TopicoDTO.converte(topicos);
        }
    }

    @PostMapping
    @CacheEvict(value = "listaDeTopicos",allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalharDTO> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new TopicoDetalharDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos",allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoFormAtualizar form) {
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));

    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos",allEntries = true)
    ResponseEntity delete(@PathVariable Long id) {
        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
