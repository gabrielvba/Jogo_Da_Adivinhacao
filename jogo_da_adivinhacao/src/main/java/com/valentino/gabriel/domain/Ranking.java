package com.valentino.gabriel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.valentino.gabriel.domain.dto.JogoInDTO;
import com.valentino.gabriel.domain.dto.RankingDTO;

@Entity
public class Ranking implements Serializable, Comparable<Ranking> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	@ManyToOne
	@JoinColumn(name="jogo_id")
	private Jogo jogo;
	
	
	public Ranking() {
	}

	public Ranking(Jogo jogo) {
		super();
		this.jogo = jogo;
		this.nome = jogo.getNome();
	}

	public Ranking(JogoInDTO jogoDTO) {
		super();
		this.jogo = new Jogo(jogoDTO);
		this.nome = jogoDTO.getNome();
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

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	@Override
	public int compareTo(Ranking o) {
	    int res=0;
	    if(this.getJogo().getTentativas() == o.getJogo().getTentativas()) {
	    	res = this.getJogo().getDuracao() > o.getJogo().getDuracao() ? 1 : -1;
	    }else {
	    	 res = this.getJogo().getTentativas() > o.getJogo().getTentativas() ? 1 : -1;
	    }     
	    return res;
	}

	@Override
	public String toString() {
		return "Ranking [id=" + id + ", nome=" + nome + ", jogo=" + jogo.getId() + "]";
	}
	
}
