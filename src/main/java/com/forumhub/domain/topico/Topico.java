package com.forumhub.domain.topico;

import com.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String messagem;
    private LocalDate dataCriacao;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Usuario.Curso curso;
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;

    public Topico(DadosTopico topicoCriado) {
        this.titulo = topicoCriado.titulo();
        this.messagem = topicoCriado.messagem();
        this.autor = topicoCriado.autor();
        this.dataCriacao = topicoCriado.dataCriacao();
        this.curso = topicoCriado.curso();
        this.estado = topicoCriado.estado();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Usuario.Curso getCurso() {
        return curso;
    }

    public void setCurso(Usuario.Curso curso) {
        this.curso = curso;
    }

    public EstadoTopico getEstado() {
        return estado;
    }

    public void setEstado(EstadoTopico estado) {
        this.estado = estado;
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dadosAtualizacao) {
        if(dadosAtualizacao.titulo() != null){
            this.titulo = dadosAtualizacao.titulo();
        }
        if(dadosAtualizacao.messagem() != null){
            this.messagem = dadosAtualizacao.messagem();
        }
        if(dadosAtualizacao.titulo() != null){
            this.curso = dadosAtualizacao.curso();
        }
        if(dadosAtualizacao.titulo() != null){
            this.estado = dadosAtualizacao.estado();
        }
    }

    public enum EstadoTopico {
        ABERTO,
        FECHADO,
        RESOLVIDO,
        DELETADO
    }
}
