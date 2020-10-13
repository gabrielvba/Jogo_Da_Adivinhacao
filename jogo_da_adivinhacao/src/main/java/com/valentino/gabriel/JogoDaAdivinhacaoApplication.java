package com.valentino.gabriel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valentino.gabriel.domain.Jogo;
import com.valentino.gabriel.domain.Ranking;
import com.valentino.gabriel.domain.repository.JogoRepository;
import com.valentino.gabriel.domain.repository.RankingRepository;

@SpringBootApplication
public class JogoDaAdivinhacaoApplication implements CommandLineRunner {
	

	public static void main(String[] args) {
		SpringApplication.run(JogoDaAdivinhacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
}
