package com.mycompany.xadrez2;
public class Torre extends Peca {

    public Torre(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "T" : "t";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        // Movimento em linha reta (horizontal ou vertical)
        return (linhaO == linhaD || colunaO == colunaD) && !(linhaO == linhaD && colunaO == colunaD);
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "";
        }

        StringBuilder path = new StringBuilder();
        if (linhaO == linhaD) { // Movimento horizontal
            int step = (colunaD > colunaO) ? 1 : -1;
            for (char c = colunaO; c != colunaD + step; c += step) {
                path.append(linhaO).append(c);
            }
        } else { // Movimento vertical
            int step = (linhaD > linhaO) ? 1 : -1;
            for (int i = linhaO; i != linhaD + step; i += step) {
                path.append(i).append(colunaO);
            }
        }
        return path.toString();
    }

    public Caminho caminhoCaminho(Tabuleiro tabuleiro, int linhaO, char colunaO, int linhaD, char colunaD) {
        Caminho caminho = new Caminho();
        if (!movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return caminho;
        }
        if (linhaO == linhaD) { // Horizontal
            int step = (colunaD > colunaO) ? 1 : -1;
            char c = colunaO;
            while (c != colunaD) {
                caminho.adicionarCasa(tabuleiro.getCasa(linhaO, c));
                c += step;
            }
            caminho.adicionarCasa(tabuleiro.getCasa(linhaD, colunaD));
        } else { // Vertical
            int step = (linhaD > linhaO) ? 1 : -1;
            int l = linhaO;
            while (l != linhaD) {
                caminho.adicionarCasa(tabuleiro.getCasa(l, colunaO));
                l += step;
            }
            caminho.adicionarCasa(tabuleiro.getCasa(linhaD, colunaD));
        }
        return caminho;
    }
}