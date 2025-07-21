package com.mycompany.xadrez2;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o caminho de uma peça entre duas casas no tabuleiro.
 */
public class Caminho {
    private List<Casa> casas;

    public Caminho() {
        this.casas = new ArrayList<>();
    }

    public void adicionarCasa(Casa casa) {
        casas.add(casa);
    }

    public Casa casaInicial() {
        return casas.isEmpty() ? null : casas.get(0);
    }

    public Casa casaFinal() {
        return casas.isEmpty() ? null : casas.get(casas.size() - 1);
    }

    /**
     * Verifica se todas as casas do caminho (exceto inicial e final) estão livres.
     */
    public boolean estaLivre() {
        for (int i = 1; i < casas.size() - 1; i++) {
            if (casas.get(i).estaOcupada()) {
                return false;
            }
        }
        return true;
    }

    public List<Casa> getCasas() {
        return casas;
    }
}