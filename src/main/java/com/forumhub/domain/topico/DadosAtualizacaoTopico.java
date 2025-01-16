package com.forumhub.domain.topico;

import com.forumhub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,
        String titulo,
        @NotBlank
        String messagem,
        String autor,
        Usuario.Curso curso,
        Topico.EstadoTopico estado) {
}
