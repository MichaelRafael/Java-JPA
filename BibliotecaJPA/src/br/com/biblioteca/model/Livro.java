package br.com.biblioteca.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries(@NamedQuery(name = "Livro.consultarPorGenero", query = "select l from Livro l where l.genero = :genero"))
public class Livro {
	
	@Id
	private String titulo;
	private String genero;
	private String editora;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Escritor escritor;
	
	// Métodos especias
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public Escritor getEscritor() {
		return escritor;
	}
	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}
	@Override
	public String toString() {
		return "\n---------------------------------------" + 
			    "\nTítulo do livro:      " + this.titulo +
				"\nGênero do livro:      " + this.genero  +
				"\nEditora:              " + this.editora + 
				"\nNome do escritor:     " + this.escritor.getNome() + 
				"\n---------------------------------------";
	}
	
}
