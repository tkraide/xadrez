package com.mycompany.xadrez2;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogadorBrancas;
    private Jogador jogadorPretas;
    private Jogador jogadorAtual;
    private List<String> historicoJogadas;
    private boolean emXeque;
    private boolean terminado;

    public Jogo(String nomeJogadorBrancas, String nomeJogadorPretas) {
        this.tabuleiro = new Tabuleiro();
        this.jogadorBrancas = new Jogador(nomeJogadorBrancas, Cor.BRANCA);
        this.jogadorPretas = new Jogador(nomeJogadorPretas, Cor.PRETA);
        this.jogadorAtual = jogadorBrancas;
        this.historicoJogadas = new ArrayList<>();
        this.emXeque = false;
        this.terminado = false;
    }

    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public Jogador getJogadorBrancas() { return jogadorBrancas; }
    public Jogador getJogadorPretas() { return jogadorPretas; }
    public Jogador getJogadorAtual() { return jogadorAtual; }
    public List<String> getHistoricoJogadas() { return historicoJogadas; }
    public boolean isEmXeque() { return emXeque; }
    public boolean isTerminado() { return terminado; }

    public void setJogadorAtual(Jogador jogador) {
        this.jogadorAtual = jogador;
    }

    public boolean jogadaValida(int linhaO, char colunaO, int linhaD, char colunaD) {
        Peca p = tabuleiro.getCasa(linhaO, colunaO).getPeca();
        if (p == null) {
            return false;
        }
        String cam = p.caminho(linhaO, colunaO, linhaD, colunaD);
        Jogada j = new Jogada(jogadorAtual, linhaO, colunaO, linhaD, colunaD, cam);
        return j.ehValida(tabuleiro);
    }

    public void realizarJogada(int linhaO, char colunaO, int linhaD, char colunaD) {
        Peca p = tabuleiro.getCasa(linhaO, colunaO).getPeca();
        String cam = p.caminho(linhaO, colunaO, linhaD, colunaD);
        Jogada j = new Jogada(jogadorAtual, linhaO, colunaO, linhaD, colunaD, cam);
        realizarJogada(j);
    }

    public void iniciar() {
        while (!terminado) {
            System.out.println(tabuleiro.desenho());
            System.out.println("Vez de: " + jogadorAtual.getNome());
            System.out.println("Peças capturadas: " + jogadorAtual.pecasCapturadas());

            String entrada = jogadorAtual.informaJogada();
            if (entrada.equalsIgnoreCase("parar")) {
                terminado = true;
                System.out.println("Jogo interrompido.");
                return; // Sai do método sem tentar ler mais nada
            }

            try {
                int lO = Integer.parseInt(entrada.substring(0, 1));
                char cO = entrada.charAt(1);
                int lD = Integer.parseInt(entrada.substring(2, 3));
                char cD = entrada.charAt(3);

                if (!jogadaValida(lO, cO, lD, cD)) {
                    System.out.println("Jogada inválida! Tente novamente.");
                    continue;
                }

                realizarJogada(lO, cO, lD, cD);
                historicoJogadas.add(entrada);

                Peca pecaDestino = tabuleiro.getCasa(lD, cD).getPeca();
                if (pecaDestino != null) {
                    Jogada jogada = new Jogada(jogadorAtual, lO, cO, lD, cD, pecaDestino.caminho(lO, cO, lD, cD));
                    if (jogada.ehXequeMate(tabuleiro)) {
                        System.out.println("Xeque-mate! " + jogadorAtual.getNome() + " venceu!");
                        terminado = true;
                        return; // Sai do método sem tentar ler mais nada
                    } else if (jogada.ehXeque(tabuleiro)) {
                        System.out.println("Xeque!");
                        emXeque = true;
                    } else {
                        emXeque = false;
                    }
                }

                jogadorAtual = (jogadorAtual == jogadorBrancas)
                    ? jogadorPretas : jogadorBrancas;

            } catch (Exception e) {
                if (!terminado) {
                    System.out.println("Erro ao processar jogada: " + e.getMessage());
                }
            }
        }
    }

    public String registroJogo() {
        StringBuilder sb = new StringBuilder();
        sb.append(jogadorBrancas.getNome()).append("\n");
        sb.append(jogadorPretas.getNome()).append("\n");
        for (String jog : historicoJogadas) {
            sb.append(jog).append("\n");
        }
        return sb.toString();
    }

    private void realizarJogada(Jogada jogada) {
        Casa o = tabuleiro.getCasa(jogada.getLinhaO(), jogada.getColunaO());
        Casa d = tabuleiro.getCasa(jogada.getLinhaD(), jogada.getColunaD());
        if (d.getPeca() != null) {
            jogadorAtual.adicionarPecaCapturada(d.getPeca());
        }
        d.setPeca(o.getPeca());
        o.setPeca(null);
    }
}
