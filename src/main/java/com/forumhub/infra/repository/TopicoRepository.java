package com.forumhub.infra.repository;
import com.forumhub.domain.topico.Topico;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topic t WHERE t.estado != DELETADO")
    Page<Topico> findAllByStatusIsNotDelected(Pageable paginacao);
}
