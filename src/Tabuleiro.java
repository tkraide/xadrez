package com.mycompany.xadrez2;

/**
 * Representa o tabuleiro de xadrez com 8x8 casas.
 * Responsável por:
 * - Configurar peças iniciais
 * - Validar posições
 * - Converter entre índices e posições (linha/coluna)
 * - Desenhar o estado atual
 */
public class Tabuleiro {
    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casas[i][j] = new Casa();
            }
        }
        posicionarPecasIniciais();
    }

    // Getters e Setters
    public Casa[][] getCasas() {
        return casas;
    }

    /**
     * Obtém uma casa específica do tabuleiro
     * @param linha (1-8)
     * @param coluna (a-h)
     * @return Casa correspondente
     */
    public Casa getCasa(int linha, char coluna) {
        if (linha < 1 || linha > 8 || coluna < 'a' || coluna > 'h') {
            throw new IllegalArgumentException("Posição inválida: " + linha + coluna);
        }
        return casas[linha - 1][coluna - 'a'];
    }

    /**
     * Converte linha/coluna para índices da matriz (0-7)
     * @param linha (1-8)
     * @param coluna (a-h)
     * @return Array [índiceLinha, índiceColuna]
     */
    public int[] getIndicesByPosicao(int linha, char coluna) {
        return new int[]{linha - 1, coluna - 'a'};
    }

    /**
     * Converte índice de linha (0-7) para número (1-8)
     */
    public int getLinhaByIndex(int index) {
        return index + 1;
    }

    /**
     * Converte índice de coluna (0-7) para letra (a-h)
     */
    public char getColunaByIndex(int index) {
        return (char) ('a' + index);
    }

    /**
     * Configura as peças nas posições iniciais do jogo
     */
    public void posicionarPecasIniciais() {
        // Peças Pretas (linha 8)
        casas[7][0].setPeca(new Torre(Cor.PRETA));  // a8
        casas[7][1].setPeca(new Cavalo(Cor.PRETA)); // b8
        casas[7][2].setPeca(new Bispo(Cor.PRETA));  // c8
        casas[7][3].setPeca(new Dama(Cor.PRETA));   // d8
        casas[7][4].setPeca(new Rei(Cor.PRETA));    // e8
        casas[7][5].setPeca(new Bispo(Cor.PRETA));  // f8
        casas[7][6].setPeca(new Cavalo(Cor.PRETA)); // g8
        casas[7][7].setPeca(new Torre(Cor.PRETA));  // h8

        // Peões Pretos (linha 7)
        for (int i = 0; i < 8; i++) {
            casas[6][i].setPeca(new Peao(Cor.PRETA));
        }

        // Peças Brancas (linha 1)
        casas[0][0].setPeca(new Torre(Cor.BRANCA)); // a1
        casas[0][1].setPeca(new Cavalo(Cor.BRANCA));// b1
        casas[0][2].setPeca(new Bispo(Cor.BRANCA)); // c1
        casas[0][3].setPeca(new Dama(Cor.BRANCA));  // d1
        casas[0][4].setPeca(new Rei(Cor.BRANCA));   // e1
        casas[0][5].setPeca(new Bispo(Cor.BRANCA)); // f1
        casas[0][6].setPeca(new Cavalo(Cor.BRANCA));// g1
        casas[0][7].setPeca(new Torre(Cor.BRANCA)); // h1

        // Peões Brancos (linha 2)
        for (int i = 0; i < 8; i++) {
            casas[1][i].setPeca(new Peao(Cor.BRANCA));
        }
    }

    /**
     * Gera uma representação visual do tabuleiro
     * @return String formatada com peças e coordenadas
     */
    public String desenho() {
        StringBuilder sb = new StringBuilder();
        sb.append("   a  b  c  d  e  f  g  h\n");
        sb.append("  -------------------------\n");

        for (int i = 7; i >= 0; i--) {
            sb.append(i + 1).append(" |");
            for (int j = 0; j < 8; j++) {
                Peca p = casas[i][j].getPeca();
                if (p != null) {
                    sb.append(p.desenho()).append(" |");
                } else {
                    sb.append((i + j) % 2 == 0 ? "##|" : "  |");
                }
            }
            sb.append(" ").append(i + 1).append("\n");
            sb.append("  -------------------------\n");
        }

        sb.append("   a  b  c  d  e  f  g  h\n");
        return sb.toString();
    }

    /**
     * Verifica se uma posição está dentro dos limites do tabuleiro
     */
    public boolean noLimite(int linha, char coluna) {
        return linha >= 1 && linha <= 8 && coluna >= 'a' && coluna <= 'h';
    }
}