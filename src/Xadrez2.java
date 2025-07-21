package com.mycompany.xadrez2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principal que inicia o jogo de xadrez
 */
public class Xadrez2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo de Xadrez!");

        while (true) {
            System.out.println("1 - Novo Jogo");
            System.out.println("2 - Carregar Jogo");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine().trim().toLowerCase();

            if (entrada.equals("3")) {
                System.out.println("Saindo...");
                break;
            } else if (entrada.equals("1")) {
                iniciarNovoJogo(scanner);
            } else if (entrada.equals("2")) {
                carregarJogo(scanner);
            } else {
                System.out.println("Opção inválida! Digite 1, 2 ou 3.");
            }
        }
        scanner.close();
    }

    private static void iniciarNovoJogo(Scanner scanner) {
        System.out.print("Nome do jogador das peças brancas: ");
        String nomeBrancas = scanner.nextLine();
        
        System.out.print("Nome do jogador das peças pretas: ");
        String nomePretas = scanner.nextLine();
        
        Jogo jogo = new Jogo(nomeBrancas, nomePretas);
        jogo.iniciar();
        
        System.out.print("Deseja salvar o jogo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            salvarJogo(jogo);
        }
    }

    private static void carregarJogo(Scanner scanner) {
        System.out.print("Nome do arquivo para carregar: ");
        String nomeArquivo = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String nomeBrancas = reader.readLine();
            String nomePretas = reader.readLine();
            Jogo jogo = new Jogo(nomeBrancas, nomePretas);

            String linha;
            boolean turnoBrancas = true; // alterna o turno conforme o histórico
            while ((linha = reader.readLine()) != null) {
                if (linha.length() == 4) {
                    int linhaO = Character.getNumericValue(linha.charAt(0));
                    char colunaO = linha.charAt(1);
                    int linhaD = Character.getNumericValue(linha.charAt(2));
                    char colunaD = linha.charAt(3);

                    // Ajusta o jogador atual antes de cada jogada
                    jogo.setJogadorAtual(turnoBrancas ? jogo.getJogadorBrancas() : jogo.getJogadorPretas());
                    if (jogo.jogadaValida(linhaO, colunaO, linhaD, colunaD)) {
                        jogo.realizarJogada(linhaO, colunaO, linhaD, colunaD);
                        jogo.getHistoricoJogadas().add(linha);
                    }
                    turnoBrancas = !turnoBrancas;
                }
            }
            System.out.println("Jogo carregado!");
            jogo.iniciar();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
        }
    }

    private static void salvarJogo(Jogo jogo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do arquivo para salvar: ");
        String nomeArquivo = scanner.nextLine();
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(jogo.registroJogo());
            System.out.println("Jogo salvo com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public static void testes() {
        // Teste de criação de peças
        Peca bispo = new Bispo(Cor.BRANCA);
        System.out.println("Bispo desenho: " + bispo.desenho());
        System.out.println("Bispo movimento válido (2c para 5f): " + bispo.movimentoValido(2, 'c', 5, 'f'));
        System.out.println("Bispo caminho (2c para 5f): " + bispo.caminho(2, 'c', 5, 'f'));

        // Teste de tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println(tabuleiro.desenho());

        // Teste de jogada válida
        Jogador jogador = new Jogador("Teste", Cor.BRANCA);
        Jogada jogada = new Jogada(jogador, 2, 'c', 4, 'c', "2c3c4c");
        System.out.println("Jogada válida? " + jogada.ehValida(tabuleiro));
    }
}