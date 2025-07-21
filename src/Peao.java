package com.mycompany.xadrez2;
public class Peao extends Peca {

    public Peao(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "P" : "p";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int direcao = (getCor() == Cor.BRANCA) ? 1 : -1;

        // Movimento para frente
        if (colunaO == colunaD) {
            // Movimento simples de uma casa
            if (linhaD == linhaO + direcao) {
                return true;
            }
            // Primeiro movimento, duas casas
            if ((getCor() == Cor.BRANCA && linhaO == 2) || (getCor() == Cor.PRETA && linhaO == 7)) {
                if (linhaD == linhaO + 2 * direcao) {
                    return true;
                }
            }
        }

        // Movimento de captura na diagonal
        if (Math.abs(colunaO - colunaD) == 1 && linhaD == linhaO + direcao) {
            return true;
        }

        return false;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "";
        }

        // Se mover duas casas, o caminho inclui a casa intermediária.
        if (Math.abs(linhaO - linhaD) == 2) {
            int linhaIntermediaria = (linhaO + linhaD) / 2;
            return "" + linhaO + colunaO + linhaIntermediaria + colunaO + linhaD + colunaD;
        }

        // Para outros movimentos, o caminho é apenas a origem e o destino.
        return "" + linhaO + colunaO + linhaD + colunaD;
    }

    public Caminho caminhoCaminho(Tabuleiro tabuleiro, int linhaO, char colunaO, int linhaD, char colunaD) {
        Caminho caminho = new Caminho();
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return caminho;
        }
        caminho.adicionarCasa(tabuleiro.getCasa(linhaO, colunaO));
        // Se for movimento de duas casas, inclui a intermediária
        if (Math.abs(linhaO - linhaD) == 2) {
            int linhaIntermediaria = (linhaO + linhaD) / 2;
            caminho.adicionarCasa(tabuleiro.getCasa(linhaIntermediaria, colunaO));
        }
        caminho.adicionarCasa(tabuleiro.getCasa(linhaD, colunaD));
        return caminho;
    }
}