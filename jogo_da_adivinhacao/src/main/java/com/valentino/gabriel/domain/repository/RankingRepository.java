package com.valentino.gabriel.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valentino.gabriel.domain.Ranking;
import com.valentino.gabriel.domain.dto.RankingDTO;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

	@Query("SELECT obj FROM Ranking obj")
	Page<RankingDTO> getRank(Pageable pageRequest);
	
	@Query("SELECT obj FROM Ranking obj WHERE obj.nome = :nome ")
	List<Ranking> getRecordName(@Param("nome") String name);
	
}
