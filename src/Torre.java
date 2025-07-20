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
}