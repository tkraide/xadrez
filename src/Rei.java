public class Rei extends Peca {

    public Rei(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "R" : "r";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int deltaLinha = Math.abs(linhaD - linhaO);
        int deltaColuna = Math.abs(colunaD - colunaO);

        // O Rei move uma casa em qualquer direção
        return deltaLinha <= 1 && deltaColuna <= 1 && (deltaLinha + deltaColuna > 0);
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return "" + linhaO + colunaO + linhaD + colunaD;
        }
        return "";
    }
}