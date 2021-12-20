package br.com.biblioteca.repositorio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.biblioteca.model.Escritor;
import br.com.biblioteca.model.Livro;

public class LivroRepositorio {

	private EntityManagerFactory emf;

	public LivroRepositorio(EntityManagerFactory emf) {
		this.emf = emf;
	}

	// Método inserir
	public void inserir(Livro livro) {

		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(livro);
			em.getTransaction().commit();

			em.close();

			System.out.println("\nLivro inserido com sucesso!\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\nErro ao inserir os dados ou escritor já está cadastrado!\n");
		}

	}

	// Método listar
	public void listar(String nome) {

		try {
			EntityManager em = emf.createEntityManager();
			Livro livro = new Livro();

			livro = em.find(Livro.class, nome);

			System.out.println("\n---------------------------------------" + 
							   "\nTítulo do livro:  " + livro.getTitulo()  + 
							   "\nGênero do livro:  " + livro.getGenero()  + 
							   "\nEditora:          " + livro.getEditora() + 
							   "\nNome do escritor: " + livro.getEscritor().getNome() + 
							   "\nNacionalidade:    " + livro.getEscritor().getNacionalidade() + 
							   "\n---------------------------------------\n");

			em.close();

		} catch (Exception e) {

			System.out.println("\nErro ao listar livro\n");
		}

	}

	// Método atualizar
	public void atualizar(String titulo) {

		try {
			Scanner teclado = new Scanner(System.in);
			EntityManager em = emf.createEntityManager();

			Livro livro = em.find(Livro.class, titulo);

			if (livro != null) {

				Escritor escritor = livro.getEscritor();

				System.out.print("Digite a nova editora: ");
				livro.setEditora(teclado.nextLine());

				System.out.print("Digite a nova nacionalidade do Escritor: ");
				escritor.setNacionalidade(teclado.nextLine());

				System.out.print("Digite o novo nome do escritor: ");
				escritor.setNome(teclado.nextLine());

				livro.setEscritor(escritor);

				em.getTransaction().begin();
				em.merge(livro);
				em.getTransaction().commit();

				em.close();

				System.out.println("\nDados alterados com sucesso!\n");

			}

		} catch (Exception e) {

			System.out.println("\nErro ao alterar os dados ou livro inexistente na base de dados!\n");
			// TODO: handle exception
		}

	}

	public void excluir(Livro l) {

		try {
			EntityManager em = emf.createEntityManager();
			Livro livro = em.find(Livro.class, l.getTitulo());

			if (livro != null) {

				Escritor escritor = l.getEscritor();

				em.getTransaction().begin();
				em.remove(livro);
				em.remove(escritor);
				em.getTransaction().commit();

				em.close();

				System.out.println("\nLivro excluido com sucesso!\n");

			} else {
				System.out.println("\nLivro inexistente na base de dados!\n");
			}

		} catch (Exception e) {

			System.out.println("Erro ao excluir livro!\n");
			// TODO: handle exception
		}
	}

	public List<Livro> mostrarAcervo() {

		EntityManager em = emf.createEntityManager();

		TypedQuery<Livro> query = em.createQuery("select l from Livro l", Livro.class);
		return query.getResultList();
	}
	
	public List<Livro> listarPorGenero(Livro l) {
		
		List<Livro> existe = null;
		EntityManager em = emf.createEntityManager();
		TypedQuery<Livro> query = em.createNamedQuery("Livro.consultarPorGenero", Livro.class);
		query.setParameter("genero", l.getGenero());
		List<Livro> livro = query.getResultList();
		if (livro != null) {
			existe = livro;
		}
		return existe;
	}

}
