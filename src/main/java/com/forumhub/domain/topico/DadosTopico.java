package com.forumhub.domain.topico;

import com.forumhub.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosTopico(
        @NotBlank String titulo,
        @NotBlank String messagem,
        @NotBlank
        @Column(name = "data_criacao")
        LocalDate dataCriacao,
        @NotBlank String autor,
        @NotBlank Usuario.Curso curso,
        @NotBlank Topico.EstadoTopico estado) {

        public DadosTopico(Topico topico){
                this(topico.getTitulo(), topico.getMessagem(), topico.getDataCriacao(), topico.getAutor(), topico.getCurso(), topico.getEstado());
        }
}
