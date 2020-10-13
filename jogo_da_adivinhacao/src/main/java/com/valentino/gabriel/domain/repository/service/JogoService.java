package com.valentino.gabriel.domain.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.valentino.gabriel.domain.Jogo;
import com.valentino.gabriel.domain.Ranking;
import com.valentino.gabriel.domain.dto.JogoInDTO;
import com.valentino.gabriel.domain.dto.JogoOutDTO;
import com.valentino.gabriel.domain.dto.RankingDTO;
import com.valentino.gabriel.domain.repository.JogoRepository;
import com.valentino.gabriel.domain.repository.RankingRepository;
import com.valentino.gabriel.services.exception.ObjectNotFoundException;

@Service
public class JogoService {

	@Autowired
	private JogoRepository repo;
	
	@Autowired
	private RankingRepository repoRank;

	public Page<RankingDTO> getRank(Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest =  PageRequest.of(page,linePerPage,Direction.valueOf(direction), orderBy.split(","));
		
		Page<RankingDTO> obj = repoRank.getRank(pageRequest);
		return obj;
	}

	public RankingDTO getRecord(String name) {
		RankingDTO obj = new RankingDTO(repoRank.getRecordName(name).get(0));
		return obj;
	}
	

	private Ranking updateRank(Jogo jogo) {
		List<Ranking> record = repoRank.getRecordName(jogo.getNome());
		Ranking rank = new Ranking(jogo);

		if (record != null && !record.isEmpty()) {
			if (rank.compareTo(record.get(0)) > 0) {
				rank.setId(record.get(0).getId());
				return repoRank.save(rank);
			}
		} else {
			return insertRecord(rank);
		}
		return record.get(0);
	}

	private Ranking insertRecord(Ranking rank) {
		return repoRank.save(rank);
	}
	
	public Jogo getJogoById(Integer id) {
		Optional<Jogo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Jogo.class.getName()));
		}


	public Jogo insertJogo(JogoInDTO jogoDTO) {
		Jogo jogo =repo.save(new Jogo(jogoDTO)) ;
		this.updateRank(jogo);
		return jogo;
	}
	
	public Page<JogoOutDTO> getJogosByName(String name, Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest =  PageRequest.of(page,linePerPage,Direction.valueOf(direction), orderBy.split(","));
		
		return repo.getJogos(name, pageRequest);
		
	}
}


