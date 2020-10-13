package com.valentino.gabriel.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valentino.gabriel.domain.Jogo;
import com.valentino.gabriel.domain.dto.JogoOutDTO;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
	
	@Query("SELECT obj FROM Jogo obj WHERE obj.nome = :nome")
	Page<JogoOutDTO> getJogos(@Param("nome") String name, Pageable pageRequest );
}
 