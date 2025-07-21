package com.mycompany.xadrez2;
public class Bispo extends Peca {

    public Bispo(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "B" : "b";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        // Movimento na diagonal
        return Math.abs(linhaD - linhaO) == Math.abs(colunaD - colunaO) && (linhaD != linhaO);
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "";
        }
        StringBuilder path = new StringBuilder();
        int deltaLinha = Integer.signum(linhaD - linhaO);
        int deltaColuna = Integer.signum(colunaD - colunaO);

        int linhaAtual = linhaO;
        char colunaAtual = colunaO;

        while (linhaAtual != linhaD || colunaAtual != colunaD) {
            path.append(linhaAtual).append(colunaAtual);
            linhaAtual += deltaLinha;
            colunaAtual += deltaColuna;
        }
        path.append(linhaD).append(colunaD);
        return path.toString();
    }

    public Caminho caminhoCaminho(Tabuleiro tabuleiro, int linhaO, char colunaO, int linhaD, char colunaD) {
        Caminho caminho = new Caminho();
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return caminho;
        }
        int deltaLinha = Integer.signum(linhaD - linhaO);
        int deltaColuna = Integer.signum(colunaD - colunaO);

        int linhaAtual = linhaO;
        char colunaAtual = colunaO;

        while (linhaAtual != linhaD || colunaAtual != colunaD) {
            caminho.adicionarCasa(tabuleiro.getCasa(linhaAtual, colunaAtual));
            linhaAtual += deltaLinha;
            colunaAtual += deltaColuna;
        }
        caminho.adicionarCasa(tabuleiro.getCasa(linhaD, colunaD));
        return caminho;
    }
}