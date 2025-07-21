import java.util.ArrayList;
import java.util.List;

// Representa o tabuleiro de xadrez, contendo 64 casas.
// É responsável pela configuração inicial das peças e por desenhar o estado atual do jogo.

public class Tabuleiro {
    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casas[i][j] = new Casa();
            }
        }
        posicionarPecasIniciais();
    }

    // Posiciona as 32 peças em suas casas iniciais no tabuleiro.
    public void posicionarPecasIniciais() {
        // Peças Pretas
        casas[7][0].setPeca(new Torre(Cor.PRETA));
        casas[7][1].setPeca(new Cavalo(Cor.PRETA));
        casas[7][2].setPeca(new Bispo(Cor.PRETA));
        casas[7][3].setPeca(new Dama(Cor.PRETA));
        casas[7][4].setPeca(new Rei(Cor.PRETA));
        casas[7][5].setPeca(new Bispo(Cor.PRETA));
        casas[7][6].setPeca(new Cavalo(Cor.PRETA));
        casas[7][7].setPeca(new Torre(Cor.PRETA));
        for (int i = 0; i < 8; i++) {
            casas[6][i].setPeca(new Peao(Cor.PRETA));
        }

        // Peças Brancas
        casas[0][0].setPeca(new Torre(Cor.BRANCA));
        casas[0][1].setPeca(new Cavalo(Cor.BRANCA));
        casas[0][2].setPeca(new Bispo(Cor.BRANCA));
        casas[0][3].setPeca(new Dama(Cor.BRANCA));
        casas[0][4].setPeca(new Rei(Cor.BRANCA));
        casas[0][5].setPeca(new Bispo(Cor.BRANCA));
        casas[0][6].setPeca(new Cavalo(Cor.BRANCA));
        casas[0][7].setPeca(new Torre(Cor.BRANCA));
        for (int i = 0; i < 8; i++) {
            casas[1][i].setPeca(new Peao(Cor.BRANCA));
        }
    }

    /**
     * Gera uma representação em String do tabuleiro com as peças.
     * @return String formatada do tabuleiro.
     */
    public String desenho() {
        StringBuilder sb = new StringBuilder();
        sb.append("   a  b  c  d  e  f  g  h\n");
        sb.append("  -------------------------\n");
        for (int i = 7; i >= 0; i--) {
            sb.append(i + 1).append(" |");
            for (int j = 0; j < 8; j++) {
                Peca p = casas[i][j].getPeca();
                if (p != null) {
                    sb.append(p.desenho()).append(" |");
                } else {
                    if ((i + j) % 2 == 0) {
                        sb.append("##|"); // Casa escura
                    } else {
                        sb.append("  |"); // Casa clara
                    }
                }
            }
            sb.append(" ").append(i + 1).append("\n");
            sb.append("  -------------------------\n");
        }
        sb.append("   a  b  c  d  e  f  g  h\n");
        return sb.toString();
    }

    // Retorna a Casa a partir da linha (1–8) e coluna ('a'–'h')
    public Casa getCasa(int linha, char coluna) {
        int i = linha - 1;
        int j = coluna - 'a';
        if (i >= 0 && i < 8 && j >= 0 && j < 8) {
            return casas[i][j];
        }
        return null;
    }

    // Retorna a Casa a partir de uma string como "2a"
    public Casa getCasaPorString(String posicao) {
        if (posicao.length() != 2) return null;
        int linha = Character.getNumericValue(posicao.charAt(0));
        char coluna = posicao.charAt(1);
        return getCasa(linha, coluna);
    }

    // Constrói um Caminho a partir de uma string como "2a3a4a"
    public Caminho getCaminho(String caminhoStr) {
        List<Casa> lista = new ArrayList<>();
        for (int i = 0; i < caminhoStr.length(); i += 2) {
            String pos = caminhoStr.substring(i, i + 2);
            Casa c = getCasaPorString(pos);
            if (c != null) {
                lista.add(c);
            }
        }
        return new Caminho(lista);
    }
}
