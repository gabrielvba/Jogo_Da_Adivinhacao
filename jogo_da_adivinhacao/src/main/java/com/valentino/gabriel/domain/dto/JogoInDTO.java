package com.valentino.gabriel.domain.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class JogoInDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private int partidas;
	
	private int tentativas;
	
	private Long tempoInicio;

	private Long tempoFim;
	
	public JogoInDTO() {
		
	}
	
	public JogoInDTO(Integer id, String nome, int partidas, int tentativas, Long tempoInicio, Long tempoFim) {
		super();
		this.id = id;
		this.nome = nome;
		this.partidas = partidas;
		this.tentativas = tentativas;
		this.tempoInicio = tempoInicio;
		this.tempoFim = tempoFim;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getTempoInicio() {
		return tempoInicio;
	}

	public void setTempoInicio(Long tempoInicio) {
		this.tempoInicio = tempoInicio;
	}

	public Long getTempoFim() {
		return tempoFim;
	}

	public void setTempoFim(Long tempoFim) {
		this.tempoFim = tempoFim;
	}

	@Override
	public String toString() {
		return "JogoInDTO [id=" + id + ", nome=" + nome + ", partidas=" + partidas + ", tentativas=" + tentativas
				+ ", tempoInicio=" + tempoInicio + ", tempoFim=" + tempoFim + "]";
	}

	
}
