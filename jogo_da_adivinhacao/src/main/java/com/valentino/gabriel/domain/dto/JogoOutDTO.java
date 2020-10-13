package com.valentino.gabriel.domain.dto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.valentino.gabriel.domain.Jogo;

public class JogoOutDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private String nome;
	
	private int partidas;
	
	private int tentativas;
	
	private String duracao;	
	
	public JogoOutDTO() {
		
	}

	public JogoOutDTO(String nome, int partidas, int tentativas, String duracao) {
		super();
		this.nome = nome;
		this.partidas = partidas;
		this.tentativas = tentativas;
		this.duracao = duracao;
	}

	public JogoOutDTO(Jogo jogo) {
		super();
		this.nome = jogo.getNome();
		this.partidas = jogo.getPartidas();
		this.tentativas = jogo.getTentativas();
		this.duracao = formatTime(jogo.getDuracao());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	private String formatTime(long duracao) {
	    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duracao),
	            TimeUnit.MILLISECONDS.toMinutes(duracao) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duracao)),
	            TimeUnit.MILLISECONDS.toSeconds(duracao) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duracao)));
	    return hms;
	}

	@Override
	public String toString() {
		return "JogoOutDTO [nome=" + nome + ", partidas=" + partidas + ", tentativas=" + tentativas + ", duracao="
				+ duracao + "]";
	}
		
}
