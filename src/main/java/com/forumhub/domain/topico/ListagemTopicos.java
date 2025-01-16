package com.forumhub.domain.topico;

import com.forumhub.domain.usuario.Usuario;
import com.forumhub.infra.exceptions.ResourceNotFoundException;
import com.forumhub.infra.repository.TopicoRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public record ListagemTopicos(
        Long id,
        String titulo,
        String messagem,
        @Column(name = "data_criacao")
        LocalDate dataCriacao,
        String autor,
        Usuario.Curso curso,
        Topico.EstadoTopico status) {

    public ListagemTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMessagem(), topico.getDataCriacao(), topico.getAutor(), topico.getCurso(), topico.getEstado());
    }

    @Service
    public static class TopicoService {

        @Autowired
        private TopicoRepository repository;


        public void publicar(DadosTopico topicoCriado){
            repository.save(new Topico(topicoCriado));
        }


        public Page<ListagemTopicos> listar(Pageable paginacao){
            return repository.findAllByStatusIsNotDelected(paginacao).map(ListagemTopicos::new);
        }

        @Transactional
        public ListagemTopicos detalhar(Long id){
            try {
                Topico topico = repository.getReferenceById(id);
                return new ListagemTopicos(topico);
            } catch (EntityNotFoundException e){
                throw new ResourceNotFoundException("ID inválido!");
            }
        }

        public void atualizar(Long id, DadosAtualizacaoTopico dadosAtualizacao){
            Topico topico = repository.getReferenceById(id);
            topico.atualizarInformacoes(dadosAtualizacao);
        }

        public void excluir(Long id) {
            Topico.EstadoTopico estadoDeletado = Topico.EstadoTopico.valueOf("DELETADO");
            Topico topico = repository.getReferenceById(id);
            topico.setEstado(estadoDeletado);
        }
    }
}
