package com.mycompany.xadrez2;

import java.util.ArrayList;
import java.util.List;

public class Jogada {
    private Jogador jogador;
    private int linhaO;
    private char colunaO;
    private int linhaD;
    private char colunaD;
    private String caminho;

    public Jogada(Jogador jogador, int linhaO, char colunaO, int linhaD, char colunaD, String caminho) {
        this.jogador = jogador;
        this.linhaO = linhaO;
        this.colunaO = colunaO;
        this.linhaD = linhaD;
        this.colunaD = colunaD;
        this.caminho = caminho;
    }

    public int getLinhaO() {
        return linhaO;
    }

    public char getColunaO() {
        return colunaO;
    }

    public int getLinhaD() {
        return linhaD;
    }

    public char getColunaD() {
        return colunaD;
    }

    public boolean ehValida(Tabuleiro tabuleiro) {
        if (linhaO < 1 || linhaO > 8 || linhaD < 1 || linhaD > 8 ||
            colunaO < 'a' || colunaO > 'h' || colunaD < 'a' || colunaD > 'h') {
            return false;
        }
        Casa casaO = tabuleiro.getCasa(linhaO, colunaO);
        Casa casaD = tabuleiro.getCasa(linhaD, colunaD);
        Peca peca = casaO.getPeca();
        if (peca == null || peca.getCor() != jogador.getCor()) {
            return false;
        }
        if (casaD.getPeca() != null && casaD.getPeca().getCor() == jogador.getCor()) {
            return false;
        }
        if (!peca.movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return false;
        }
        // Integração com Caminho
        Caminho caminho = peca.caminhoCaminho(tabuleiro, linhaO, colunaO, linhaD, colunaD);
        if (!(peca instanceof Cavalo) && !caminho.estaLivre()) {
            return false;
        }
        return true;
    }

    private boolean caminhoLivre(Tabuleiro tabuleiro) {
        List<int[]> path = caminhoIndices(tabuleiro, linhaO, colunaO, linhaD, colunaD);
        for (int i = 1; i < path.size(); i++) {
            int[] pos = path.get(i);
            Casa c = tabuleiro.getCasas()[pos[0]][pos[1]];
            if (i < path.size() - 1) {
                if (c.estaOcupada()) return false;
            }
        }
        return true;
    }

    public List<int[]> caminhoIndices(Tabuleiro tabuleiro,
            int linhaO, char colunaO, int linhaD, char colunaD) {
        List<int[]> path = new ArrayList<>();
        int[] ori = tabuleiro.getIndicesByPosicao(linhaO, colunaO);
        int[] dst = tabuleiro.getIndicesByPosicao(linhaD, colunaD);
        int dl = Integer.signum(dst[0] - ori[0]);
        int dc = Integer.signum(dst[1] - ori[1]);
        int li = ori[0], cj = ori[1];
        while (li != dst[0] || cj != dst[1]) {
            path.add(new int[]{li, cj});
            li += dl;
            cj += dc;
        }
        path.add(new int[]{dst[0], dst[1]});
        return path;
    }

    public boolean ehXeque(Tabuleiro tabuleiro) {
        int[] posRei = encontrarRei(tabuleiro,
            (jogador.getCor() == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA);
        if (posRei == null) return false;
        int reiLinha = tabuleiro.getLinhaByIndex(posRei[0]);
        char reiColuna = tabuleiro.getColunaByIndex(posRei[1]);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casa casa = tabuleiro.getCasas()[i][j];
                if (casa.estaOcupada() && casa.getPeca().getCor() == jogador.getCor()) {
                    int linha = tabuleiro.getLinhaByIndex(i);
                    char coluna = tabuleiro.getColunaByIndex(j);
                    Peca p = casa.getPeca();
                    if (p.movimentoValido(linha, coluna, reiLinha, reiColuna)) {
                        Jogada jg = new Jogada(jogador, linha, coluna,
                            reiLinha, reiColuna, p.caminho(linha, coluna, reiLinha, reiColuna));
                        if (jg.caminhoLivre(tabuleiro)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int[] encontrarRei(Tabuleiro tabuleiro, Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casa casa = tabuleiro.getCasas()[i][j];
                if (casa.estaOcupada() && casa.getPeca() instanceof Rei &&
                    casa.getPeca().getCor() == cor) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public boolean ehXequeMate(Tabuleiro tabuleiro) {
        if (!ehXeque(tabuleiro)) return false;
        Cor defensor = (jogador.getCor() == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
        for (int i = 1; i <= 8; i++) {
            for (char c = 'a'; c <= 'h'; c++) {
                Casa co = tabuleiro.getCasa(i, c);
                if (co.getPeca() != null && co.getPeca().getCor() == defensor) {
                    Peca p = co.getPeca();
                    for (int di = 1; di <= 8; di++) {
                        for (char dc = 'a'; dc <= 'h'; dc++) {
                            if (!p.movimentoValido(i, c, di, dc)) continue;
                            String cam = p.caminho(i, c, di, dc);
                            Jogada t = new Jogada(new Jogador("temp", defensor),
                                i, c, di, dc, cam);
                            if (!t.ehValida(tabuleiro)) continue;
                            Casa orig = co;
                            Casa dest = tabuleiro.getCasa(di, dc);
                            Peca cap = dest.getPeca();

                            // Simula o movimento
                            dest.setPeca(p);
                            orig.setPeca(null);

                            // Verifica se o rei ainda está em xeque
                            boolean aindaEmXeque = false;
                            for (int x = 0; x < 8; x++) {
                                for (int y = 0; y < 8; y++) {
                                    Casa casa = tabuleiro.getCasas()[x][y];
                                    if (casa.estaOcupada() && casa.getPeca().getCor() != defensor) {
                                        int linha = tabuleiro.getLinhaByIndex(x);
                                        char coluna = tabuleiro.getColunaByIndex(y);
                                        Peca atacante = casa.getPeca();
                                        int[] posRei = encontrarRei(tabuleiro, defensor);
                                        if (posRei != null) {
                                            int reiLinha = tabuleiro.getLinhaByIndex(posRei[0]);
                                            char reiColuna = tabuleiro.getColunaByIndex(posRei[1]);
                                            if (atacante.movimentoValido(linha, coluna, reiLinha, reiColuna)) {
                                                Jogada ataque = new Jogada(jogador, linha, coluna,
                                                    reiLinha, reiColuna, atacante.caminho(linha, coluna, reiLinha, reiColuna));
                                                if (ataque.caminhoLivre(tabuleiro)) {
                                                    aindaEmXeque = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            // Desfaz o movimento
                            orig.setPeca(p);
                            dest.setPeca(cap);

                            if (!aindaEmXeque) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
