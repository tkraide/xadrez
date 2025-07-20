/**
 * Representa uma peça genérica do jogo de xadrez.
 * Esta classe é abstrata e define a estrutura base para todas as peças específicas.
 */
public abstract class Peca {

    private Cor cor;
    private boolean capturada = false;

    // Construtor para a Peça.

    public Peca(Cor cor) {
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    /**
     * Verifica se a peça foi capturada.
     * @return true se a peça foi capturada, false caso contrário.
     */
    public boolean isCapturada() {
        return capturada;
    }

    // Marca a peça como capturada.
    public void capturar() {
        this.capturada = true;
    }

    /**
     * Método abstrato que retorna a representação da peça no tabuleiro.
     * A representação diferencia peças brancas (maiúsculas) de pretas (minúsculas).
     * @return Uma String com o caractere que representa a peça.
     */
    public abstract String desenho();

    /**
     * Método abstrato que verifica se um movimento é válido para o tipo específico da peça,
     * considerando apenas a geometria do movimento.
     * @param linhaO Linha de origem (1 a 8).
     * @param colunaO Coluna de origem ('a' a 'h').
     * @param linhaD Linha de destino (1 a 8).
     * @param colunaD Coluna de destino ('a' a 'h').
     * @return true se o movimento é geometricamente válido para a peça, false caso contrário.
     */
    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    /**
     * Método abstrato que, se o movimento for válido, retorna uma String representando
     * a sequência de casas no caminho.
     * Segue mesmos parâmetros de movimentoValido
     * @return A string do caminho se o movimento for válido, ou uma string vazia caso contrário.
     */
    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);
}