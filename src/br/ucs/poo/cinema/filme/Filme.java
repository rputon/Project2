package br.ucs.poo.cinema.filme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ucs.poo.cinema.cinema.Horario;
import br.ucs.poo.cinema.cinema.Sala;
import br.ucs.poo.cinema.pessoas.Ator;
import br.ucs.poo.cinema.pessoas.Diretor;

public class Filme implements Serializable {
	// private int cod;

	protected String nome;
	protected int ano;
	protected int timeMin;
	protected String descricao;
	protected Rating rating;
	protected Genero genero;

	protected List<Ator> atores;
	protected Diretor dire;

	private List<Horario> horarios;
	private Sala sala;
	private int nIngressos;

	/*---- Constructor ---------------------------------------------------------------------------------- */
	public Filme(String nome, int ano, int timeMin, String descricao, Rating rating, Genero genero) {
		setNome(nome);
		setAno(ano);
		setTimeMin(timeMin);
		setDescricao(descricao);
		setRating(rating);
		setGenero(genero);
		this.atores = new ArrayList<Ator>();
		this.horarios = new ArrayList<Horario>();
	}

	public Filme(String nome, int ano, int timeMin, String descricao, Rating rating, Genero genero, List<Ator> atores,
			Diretor dire) {
		setNome(nome);
		setAno(ano);
		setTimeMin(timeMin);
		setDescricao(descricao);
		setRating(rating);
		setGenero(genero);
		this.atores = new ArrayList<Ator>();
		setAtores(atores);
		setDiretor(dire);
		this.horarios = new ArrayList<Horario>();
	}

	/*---- Getters/Setters ------------------------------------------------------------------------------ */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sala getSala() {
		return sala;
	}

	public void setDiretor(Diretor dire) {
		this.dire = dire;
	}

	/*---- Listas -------------------------------------------------------------------------------------- */
	public String getRating() {
		return rating.getIdade();
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public Horario getHorarios(int index) {
		return horarios.get(index);
	}

	public void setHora(Horario hora) {
		this.horarios.add(hora);
	}

	public void addIngresso() {
		this.nIngressos++;
	}

	public int getIngressos() {
		return nIngressos;
	}

	/*---- Methods ---------------------------------------------------------------------------------- */
	public String toString(int index) {
		if (index == 1) {
			return String.format("%s ‧ %s\n%d ‧ %s ‧ %dmin\n%s", nome, rating, ano, genero, timeMin, descricao);
		} else if (index == 2) {
			return String.format("%d ‧ %s ‧ %dmin", ano, genero, timeMin);
		} else if (index == 3) {
			return String.format("1 - %s \n2 - %d \n3 - %d \n4 - %s \n5 - %s \n6 - %s \nDigite 0 para cancelar", nome,
					ano, timeMin, descricao, rating, genero);
		} else if (index == 4) {
			return String.format("%s ‧ %s ‧ %d\n%dmin ‧ %s ‧ %s", nome, rating, ano, timeMin, sala, horarios.get(0));
		} else if(index == 5){
			StringBuilder ator = new StringBuilder();
			for(int i=0;i<atores.size();i++){
				ator.append(String.format("%s, ", atores.get(i)));
			}
			return String.format("%s, %d \n%s %s %dmin\n%s\n%s\n%s", nome, ano, rating.toString(),genero.toString(),timeMin,descricao,dire.toString(),ator.toString());
		}
		return String.format("%s\n%d ‧ %s ‧ %dmin", nome, ano, genero.getNome(), timeMin);
	}

	public String checkDados() {
		return String.format("%s \n%d \n%d \n%s \n%s \n%s \nDigite 0 para cancelar", nome,
				ano, timeMin, descricao, rating, genero);
	}

	public String toString() {
		return String.format("%s, %d\n%s", nome, ano, descricao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	/*
	 * @Override
	 * public int hashCode(){
	 * final int prime = 31;
	 * int result = 1;
	 * result = prime * result + cod;
	 * return result;
	 * }
	 */

}
