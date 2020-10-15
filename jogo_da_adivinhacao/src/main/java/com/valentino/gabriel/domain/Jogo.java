package com.valentino.gabriel.domain;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.valentino.gabriel.domain.dto.JogoInDTO;

@Entity
public class Jogo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private int tentativas;
	
	private int partidas;

	private Long duracao;	

	
	public Jogo() {
		
	}

	public Jogo(Long duracao, int partidas, int tentativas, String nome) {
		super();
		this.duracao = duracao;
		this.partidas = partidas;
		this.tentativas = tentativas;
		this.nome = nome;
	}

	public Jogo(JogoInDTO jogoDTO) {
		super();
		this.id = jogoDTO.getId();
		this.duracao = duracao(jogoDTO);
		this.partidas = jogoDTO.getPartidas();
		this.tentativas = jogoDTO.getTentativas();
		this.nome = jogoDTO.getNome();
	}
	
	private Long duracao(JogoInDTO jogoDTO) {
		return jogoDTO.getTempoFim() - jogoDTO.getTempoInicio();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
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

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome=" + nome + ", tentativas=" + tentativas + ", partidas=" + partidas
				+ ", duracao=" + duracao + "]";
	}
	
	
}
