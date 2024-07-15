package br.com.exemplo.literalura;

import br.com.alura.literalura.model.CatalogService;
import br.com.exemplo.literalura.model.Author;
import br.com.exemplo.literalura.model.Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CatalogService catalogService = new CatalogService();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 1:
                    consultarLivroPorTitulo();
                    break;
                case 2:
                    listarTodosAutores();
                    break;
                case 3:
                    listarLivrosPorIdioma();
                    break;
                case 4:
                    listarTodosLivros();
                    break;
                case 5:
                    listarAutoresVivosPorAnoNascimento();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1 - Consultar livro por título");
        System.out.println("2 - Listar todos autores");
        System.out.println("3 - Listar livros por idioma");
        System.out.println("4 - Listar todos livros");
        System.out.println("5 - Listar autores vivos por ano de nascimento");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void consultarLivroPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        Book livro = catalogService.consultarLivroPorTitulo(titulo);
        if (livro != null) {
            System.out.println("Livro encontrado:\n" + livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void listarTodosAutores() {
        List<Author> autores = catalogService.listarTodosAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            System.out.println("Lista de autores:");
            for (Author autor : autores) {
                System.out.println(autor);
            }
        }
    }

    private static void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma dos livros: ");
        String idioma = scanner.nextLine();
        List<Book> livros = catalogService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma especificado.");
        } else {
            System.out.println("Lista de livros no idioma '" + idioma + "':");
            for (Book livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void listarTodosLivros() {
        List<Book> livros = catalogService.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("Lista de todos os livros:");
            for (Book livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void listarAutoresVivosPorAnoNascimento() {
        System.out.print("Digite o ano de nascimento dos autores vivos: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        List<Author> autores = catalogService.listarAutoresVivosPorAnoNascimento(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado nascido no ano " + ano + ".");
        } else {
            System.out.println("Lista de autores vivos nascidos no ano " + ano + ":");
            for (Author autor : autores) {
                System.out.println(autor);
            }
        }
    }
}
