package com.forumhub.controller;

import com.forumhub.domain.topico.DadosAtualizacaoTopico;
import com.forumhub.domain.topico.DadosTopico;
import com.forumhub.domain.topico.ListagemTopicos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ListagemTopicos.TopicoService service;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody DadosTopico topicoCriado){
        service.publicar(topicoCriado);
    }

    @GetMapping
    public Page<ListagemTopicos> listar(@PageableDefault(size = 10) Pageable paginacao){
        return service.listar(paginacao);
    }

    @GetMapping("/{id}")
    public ListagemTopicos detalhar(@PathVariable Long id){
        return service.detalhar(id);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacao){
        service.atualizar(dadosAtualizacao.id(), dadosAtualizacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }






}
