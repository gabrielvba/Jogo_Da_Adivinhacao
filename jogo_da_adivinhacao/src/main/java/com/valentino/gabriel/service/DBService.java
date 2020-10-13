package com.valentino.gabriel.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentino.gabriel.domain.Jogo;
import com.valentino.gabriel.domain.Ranking;
import com.valentino.gabriel.domain.repository.JogoRepository;
import com.valentino.gabriel.domain.repository.RankingRepository;

@Service 
public class DBService {

	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	RankingRepository rankingRepository;

	
	public void instantiateTestDatabase() {
		long x = 920500;
		long y = 803730;
		long z = 145080;
		long w = 305420;
		Jogo j1 = new Jogo(z,1,2,"gabriel");
		Jogo j2 = new Jogo(w,2,2,"gabriel");
		Jogo j3 = new Jogo(x,3,8,"gabriel");
		Jogo j4 = new Jogo(z,4,6,"gabriel");
		Jogo j5 = new Jogo(z,1,4,"thais");
		Jogo j6 = new Jogo(y,2,9,"thais");
		Jogo j7 = new Jogo(w,1,7,"tainara");
		
		jogoRepository.saveAll(Arrays.asList(j1,j2,j3,j4,j5,j6,j7));
		
		Ranking r1 =  new Ranking(j1);
		Ranking r2 =  new Ranking(j5);
		Ranking r3 =  new Ranking(j7);

		rankingRepository.saveAll(Arrays.asList(r1,r2,r3));
	}
}
