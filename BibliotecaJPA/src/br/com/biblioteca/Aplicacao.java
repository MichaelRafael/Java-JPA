package br.com.biblioteca;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.biblioteca.model.Escritor;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repositorio.LivroRepositorio;

public class Aplicacao {

	public static int leiaInt(String num) {

		int n = 0;
		while (true) {
			try {
				n = Integer.valueOf(num);
			} catch (Exception e) {
				System.out.println("Erro. Digite apenas números inteiros!");
			}
			return n;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste-JPA");
		Scanner entrada = new Scanner(System.in);
		int opc;

		System.out.println("### ÁREA DO ESCRITOR ###\n");

		while (true) {

			System.out.println("\nEscolha a opção: ");

			System.out.println("|------------------------|");
			System.out.println("| 1 - Inserir Livro      |");
			System.out.println("| 2 - Listar Livro       |");
			System.out.println("| 3 - Atualizar Livro    |");
			System.out.println("| 4 - Excluir Livro      |");
			System.out.println("| 5 - Mostrar Acervo     |");
			System.out.println("| 6 - Listar por Gênero  |");
			System.out.println("| 7 - Sair...            |");
			System.out.println("|------------------------|");
			System.out.print("Opção: ");
			opc = leiaInt(entrada.next());

			// ### INSERIR ###
			if (opc == 1) {
				Scanner teclado = new Scanner(System.in);
				Escritor escritor = new Escritor();
				Livro livro = new Livro();

				System.out.println("\n-----------");
				System.out.println("| INSERIR |");
				System.out.println("-----------");

				System.out.print("\nDigite o título do livro: ");
				livro.setTitulo(teclado.nextLine());

				System.out.print("Digite o gênero do livro: ");
				livro.setGenero(teclado.nextLine());

				System.out.print("Digite a editora do livro: ");
				livro.setEditora(teclado.nextLine());

				System.out.print("Digite o nome do Escritor: ");
				escritor.setNome(teclado.nextLine());

				System.out.print("Digite a nacionalidade: ");
				escritor.setNacionalidade(teclado.nextLine());

				System.out.print("Digite a data de nascimento: ");
				escritor.setDataNascimento(teclado.nextLine());

				System.out.print("Digite o email: ");
				escritor.setEmail(teclado.next());

				livro.setEscritor(escritor);

				LivroRepositorio lr = new LivroRepositorio(emf);

				lr.inserir(livro);

				// ### LISTAR ###
			} else if (opc == 2) {

				Scanner teclado = new Scanner(System.in);

				System.out.println("\n------------");
				System.out.println("|  LISTAR  |");
				System.out.println("------------");

				System.out.print("Digite o título do livro: ");
				String nome = teclado.nextLine();

				LivroRepositorio lr = new LivroRepositorio(emf);

				lr.listar(nome);

				// ### ATUALIZAR ###
			} else if (opc == 3) {

				Scanner teclado = new Scanner(System.in);

				System.out.println("\n-------------");
				System.out.println("| ATUALIZAR |");
				System.out.println("-------------");

				System.out.print("Digite o título do livro: ");
				String titulo = teclado.nextLine();

				LivroRepositorio lr = new LivroRepositorio(emf);

				lr.atualizar(titulo);

				// ### EXCLUIR ###
			} else if (opc == 4) {

				Livro livro = new Livro();
				Scanner teclado = new Scanner(System.in);

				System.out.println("\n-------------");
				System.out.println("|  EXCLUIR  |");
				System.out.println("-------------");
				
				System.out.print("Digite o título do livro: ");
				livro.setTitulo(teclado.nextLine());

				LivroRepositorio lr = new LivroRepositorio(emf);

				lr.excluir(livro);
			
				// ### Listar todos os livros ###
			} else if (opc == 5) {
				
				LivroRepositorio lr = new LivroRepositorio(emf);
				
				List<Livro> livro = lr.mostrarAcervo();
				
				System.out.println("\n--------------");
				System.out.println("|   ACERVO   |");
				System.out.println("--------------");
				
				for (Livro acervo : livro) {
					System.out.println(acervo);
					
				}
			
			// ### Listar por gênero
			} else if (opc == 6) {
				
				Scanner teclado = new Scanner(System.in);
				LivroRepositorio lr = new LivroRepositorio(emf);
				List<Livro> livro;
				Livro l = new Livro();
				
				System.out.print("Digite o gênero do livro: ");
				l.setGenero(teclado.nextLine());
				
				livro = lr.listarPorGenero(l);
				
				System.out.println("\n--------------------");
				System.out.println("| LISTAR POR GÊNERO |");
				System.out.println("---------------------");
				
				for (Livro genero : livro) {
					
					System.out.println(genero);
					
				}
				
			// ### Sair ###
			} else if (opc == 7) {
				
				break;
			
			
			} else {
				
				System.out.println("\nDigite um número válido!\n");
			}
		}
		
		System.out.println("\n #### FIM DA EXECUÇÃO ####");
	}
}
