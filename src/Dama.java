public class Dama extends Peca {

    public Dama(Cor cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        return (getCor() == Cor.BRANCA) ? "D" : "d";
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int deltaLinha = Math.abs(linhaD - linhaO);
        int deltaColuna = Math.abs(colunaD - colunaO);

        // Movimento de Torre (reto) ou de Bispo (diagonal)
        boolean reto = (linhaO == linhaD || colunaO == colunaD);
        boolean diagonal = (deltaLinha == deltaColuna);

        return (reto || diagonal) && (deltaLinha + deltaColuna > 0);
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
}