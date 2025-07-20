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

        while(linhaAtual != linhaD + deltaLinha || colunaAtual != colunaD + deltaColuna) {
            path.append(linhaAtual).append(colunaAtual);
            if (linhaAtual == linhaD && colunaAtual == colunaD) break;
            linhaAtual += deltaLinha;
            colunaAtual += deltaColuna;
        }
        return path.toString();
    }
}