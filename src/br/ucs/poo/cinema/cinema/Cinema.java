package br.ucs.poo.cinema.cinema;

import java.util.List;

import br.ucs.poo.cinema.filme.Filme;
import br.ucs.poo.cinema.filme.SortFilme;

import java.util.ArrayList;
import java.util.Collections;

public class Cinema {
	private String nome;
	private String endereco;
	private List<Sala> salas = new ArrayList<>();
	private List<Filme> filmes = new ArrayList<>();
	private List<Filme> filmesCartaz = new ArrayList<>();
	private List<Ingresso> ingressos = new ArrayList<>();
	//Lista de atores
	//Lista de gêneros


	/*---- Constructor ---------------------------------------------------------------------------------- */
	public Cinema() {

	}

	/*---- Getters/Setters ------------------------------------------------------------------------------ */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/*---- Listas ----------------------------------------- */
	// Create
	public void setSala(Sala index) {
		this.salas.add(index);
	}

	public void setFilme(String nome, int ano, int timeMin, String descricao, int rating,String genero) {
		this.filmes.add(new Filme(nome,ano,timeMin,descricao,rating,genero));
		Collections.sort(filmes, new SortFilme());
		//TODO: List<Cinema> genero = c.getGeneros();
		//Generos
		//Escolha o gênero
		//getGenero -> Filme.setGenero(getGenero)
	}

	public void setFilme(List<Filme> filmes){
		this.filmes = filmes;
	}

	public void setFilmeCartaz(String nome, int ano, int timeMin, String descricao, int rating, String genero) {
		if (getFilmeCartazTam() <= 10) {
			this.filmesCartaz.add(new Filme(nome,ano,timeMin,descricao,rating,genero));
			Collections.sort(filmesCartaz, new SortFilme());
		}
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingressos.add(ingresso);
	}

	// Read----------------------------------------------------
	public Sala getSala(int index) {
		return salas.get(index);
	}

	public List<Filme> getFilmes(){
		return filmes;
	}

	//public  boolean 

	public Filme getFilme(int index) {
		return filmes.get(index);
	}

	public Filme getFilmeCartaz(int index) {
		return filmesCartaz.get(index);
	}

	public Ingresso getIngresso(int index) {
		return ingressos.get(index);
	}
	// Updade----------------------------------------------------

	// Delete-----------------------------------------------------------
	public Sala removeSala(int index) {
		return salas.remove(index);
	}

	public Filme removeFilme(int index) {
		return filmes.remove(index);
	}

	public Filme removeFilmeCartaz(int index) {
		return filmesCartaz.remove(index);
	}

	public Ingresso removeIngresso(int index) {
		return ingressos.remove(index);
	}

	// Get Size
	public int getSalasTam() {
		return salas.size();
	}

	public int getFilmeTam() {
		return filmes.size();
	}

	public int getFilmeCartazTam() {
		return filmesCartaz.size();
	}

	public int getIngressoTam() {
		return ingressos.size();
	}

	/*---- Methods ---------------------------------------------------------------------------------- */

}
