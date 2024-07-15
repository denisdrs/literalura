package br.com.alura.literalura.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CatalogService {
    private static final String BASE_URL = "https://gutendex.com/books";
    private static final ObjectMapper mapper = new ObjectMapper();

    public br.com.exemplo.literalura.model.Book consultarLivroPorTitulo() {
        return consultarLivroPorTitulo(null);
    }

    public br.com.exemplo.literalura.model.Book consultarLivroPorTitulo(String titulo) {
        try {
            URL url = new URL(BASE_URL + "?search=" + titulo);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String resposta = scanner.useDelimiter("\\A").next();
                scanner.close();

                JsonNode root = mapper.readTree(resposta);
                JsonNode results = root.get("results").get(0); // Assumindo o primeiro resultado

                Book livro = mapper.convertValue(results, Book.class);
                return livro;
            } else {
                System.out.println("Erro ao consultar livro por título. Código de resposta: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return null;
    }

    public List<Author> listarTodosAutores() {
        List<Author> autores = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String resposta = scanner.useDelimiter("\\A").next();
                scanner.close();

                JsonNode root = mapper.readTree(resposta);
                JsonNode results = root.get("results");

                for (JsonNode node : results) {
                    Author autor = mapper.convertValue(node.get("authors").get(0), Author.class); // Assumindo o primeiro autor
                    autores.add(autor);
                }
            } else {
                System.out.println("Erro ao listar autores. Código de resposta: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return autores;
    }

    public List<Book> listarLivrosPorIdioma(String idioma) {
        List<Book> livros = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL + "?languages=" + idioma);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String resposta = scanner.useDelimiter("\\A").next();
                scanner.close();

                JsonNode root = mapper.readTree(resposta);
                JsonNode results = root.get("results");

                for (JsonNode node : results) {
                    Book livro = mapper.convertValue(node, Book.class);
                    livros.add(livro);
                }
            } else {
                System.out.println("Erro ao listar livros por idioma. Código de resposta: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return livros;
    }

    public List<Book> listarTodosLivros() {
        List<Book> livros = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String resposta = scanner.useDelimiter("\\A").next();
                scanner.close();

                JsonNode root = mapper.readTree(resposta);
                JsonNode results = root.get("results");

                for (JsonNode node : results) {
                    Book livro = mapper.convertValue(node, Book.class);
                    livros.add(livro);
                }
            } else {
                System.out.println("Erro ao listar livros. Código de resposta: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return livros;
    }

    public List<Author> listarAutoresVivosPorAnoNascimento(int ano) {
        List<Author> autores = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                String resposta = scanner.useDelimiter("\\A").next();
                scanner.close();

                JsonNode root = mapper.readTree(resposta);
                JsonNode results = root.get("results");

                for (JsonNode node : results) {
                    Author autor = mapper.convertValue(node.get("authors").get(0), Author.class); // Assumindo o primeiro autor
                    int birthYear = node.get("authors").get(0).get("birth_year").asInt(); // Extrair ano de nascimento
                    boolean alive = node.get("authors").get(0).get("death_year").isNull(); // Verificar se está vivo

                    if (alive && birthYear == ano) {
                        autores.add(autor);
                    }
                }
            } else {
                System.out.println("Erro ao listar autores vivos por ano de nascimento. Código de resposta: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return autores;
    }
}
