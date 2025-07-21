package com.mycompany.xadrez2;

/**
 * Representa uma casa no tabuleiro de xadrez, que pode:
 * - Estar ocupada por uma peça
 * - Estar vazia
 */
public class Casa {
    private Peca peca;

    /**
     * Construtor de casa vazia
     */
    public Casa() {
        this.peca = null;
    }

    // Getters e Setters
    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    /**
     * Verifica se a casa está ocupada
     * @return true se contém uma peça, false se está vazia
     */
    public boolean estaOcupada() {
        return this.peca != null;
    }
}