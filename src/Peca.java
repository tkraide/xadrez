package com.mycompany.xadrez2;

/**
 * Classe abstrata que representa uma peça genérica do xadrez, contendo:
 * - Cor da peça
 * - Estado de captura
 * - Métodos abstratos para desenho e movimentação
 */
public abstract class Peca {
    private Cor cor;
    private boolean capturada = false;

    /**
     * Construtor da peça
     * @param cor Cor da peça (BRANCA ou PRETA)
     */
    public Peca(Cor cor) {
        this.cor = cor;
    }

    // Getters e Setters
    public Cor getCor() {
        return cor;
    }

    public boolean isCapturada() {
        return capturada;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    /**
     * Marca a peça como capturada
     */
    public void capturar() {
        this.capturada = true;
    }

    /**
     * Retorna a representação visual da peça
     * @return String com o símbolo da peça
     */
    public abstract String desenho();

    /**
     * Verifica se o movimento é válido para o tipo de peça
     */
    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    /**
     * Retorna o caminho percorrido pela peça
     */
    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);

    /**
     * Método abstrato para calcular o caminho da peça
     */
    public abstract Caminho caminhoCaminho(Tabuleiro tabuleiro, int linhaO, char colunaO, int linhaD, char colunaD);
}