package com.valentino.gabriel.domain.dto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.valentino.gabriel.domain.Ranking;

public class RankingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;

	private int tentativas;
	
	private int partidas;
		
	private String duracao;


	public RankingDTO() {	
		
	}
	
	public RankingDTO(String duracao, int partidas, int tentativas, String nome) {
		super();
		this.duracao = duracao;
		this.partidas = partidas;
		this.tentativas = tentativas;
		this.nome = nome;
	}

	public RankingDTO(Ranking ranking) {	
		this.duracao = formatTime(ranking.getJogo().getDuracao());
		this.partidas = ranking.getJogo().getPartidas();
		this.tentativas = ranking.getJogo().getTentativas();
		this.nome  = ranking.getNome();
	}
	

	public String getDuracao() {
		return duracao;
	}


	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}


	public int getPartidas() {
		return partidas;
	}


	public void setPartidas(int partidas) {
		this.partidas = partidas;
	}


	public int getTentativas() {
		return tentativas;
	}


	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private String formatTime(long duracao) {
	    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duracao),
	            TimeUnit.MILLISECONDS.toMinutes(duracao) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duracao)),
	            TimeUnit.MILLISECONDS.toSeconds(duracao) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duracao)));
	    return hms;
	}
}
