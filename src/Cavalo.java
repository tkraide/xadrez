package com.mycompany.xadrez2;
public class Cavalo extends Peca {

    public Cavalo(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "C" : "c";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int deltaLinha = Math.abs(linhaD - linhaO);
        int deltaColuna = Math.abs(colunaD - colunaO);

        // Movimento em "L"
        return (deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2);
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        // O caminho do cavalo, conforme o exemplo do projeto, pode ser interpretado como as casas que formam o "L"
        // Exemplo: 4b para 5d -> "4b5b5c5d"
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "" + linhaO + colunaO + linhaD + colunaD; // O caminho direto, pois ele pula
        }
        return "";
    }

    public Caminho caminhoCaminho(Tabuleiro tabuleiro, int linhaO, char colunaO, int linhaD, char colunaD) {
        Caminho caminho = new Caminho();
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return caminho;
        }
        caminho.adicionarCasa(tabuleiro.getCasa(linhaO, colunaO));
        caminho.adicionarCasa(tabuleiro.getCasa(linhaD, colunaD));
        return caminho;
    }
}