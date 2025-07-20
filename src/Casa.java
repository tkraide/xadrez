// Representa uma casa (quadrado) no tabuleiro de xadrez.
//Uma casa pode estar ocupada por uma peça ou estar livre.

public class Casa {
    private Peca peca;

    public Casa() {
        this.peca = null;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public boolean estaOcupada() {
        return this.peca != null;
    }
}