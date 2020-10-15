package com.valentino.gabriel.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valentino.gabriel.domain.Jogo;
import com.valentino.gabriel.domain.dto.JogoInDTO;
import com.valentino.gabriel.domain.dto.JogoOutDTO;
import com.valentino.gabriel.domain.dto.RankingDTO;
import com.valentino.gabriel.domain.repository.service.JogoService;

@RestController
@RequestMapping(value="/jogos")
public class JogoResource {
	
	@Autowired
	private JogoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJogoById(@PathVariable Integer id) {
		
		Jogo obj = service.getJogoById(id);

		return ResponseEntity.ok(obj);
		
	}
	
	@RequestMapping(value="/meusJogos",method=RequestMethod.GET)
	public ResponseEntity<Page<JogoOutDTO>> getJogosByName(
			@RequestParam(value="name", defaultValue="nome") String name,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="partidas") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {	
		Page<JogoOutDTO> obj = service.getJogosByName(name, page, linesPerPage, direction, orderBy);

		return ResponseEntity.ok(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Jogo> insertJogo(@RequestBody JogoInDTO jogoDTO) {
		
		Jogo jogo = service.insertJogo(jogoDTO);
		
		return ResponseEntity.ok(jogo);
		
	}

	@RequestMapping(value="/rank",method=RequestMethod.GET)
	public ResponseEntity<Page<RankingDTO> > getRank(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="jogo.tentativas,jogo.duracao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<RankingDTO> record = service.getRank(page, linesPerPage, direction, orderBy);

		return ResponseEntity.ok(record);
	}
	
	@RequestMapping(value="/meuRecord",method=RequestMethod.GET)
	public ResponseEntity<RankingDTO> getRecord(@RequestParam(value="name", defaultValue="nome") String name) {
		
		RankingDTO record = service.getRecord(name);

		return ResponseEntity.ok(record);
		
	}
	
}
