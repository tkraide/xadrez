package com.mycompany.xadrez2;

public class TestesPOO {
    public static void main(String[] args) {
        // Teste Bispo
        Bispo bispo = new Bispo(Cor.BRANCA);
        System.out.println("Teste Bispo:");
        System.out.println("Desenho: " + bispo.desenho()); // Esperado: B
        System.out.println("Movimento válido (2c->5f): " + bispo.movimentoValido(2, 'c', 5, 'f')); // true
        System.out.println("Caminho (2c->5f): " + bispo.caminho(2, 'c', 5, 'f')); // 2c3d4e5f

        // Teste Torre
        Torre torre = new Torre(Cor.PRETA);
        System.out.println("\nTeste Torre:");
        System.out.println("Desenho: " + torre.desenho()); // Esperado: t
        System.out.println("Movimento válido (1a->1h): " + torre.movimentoValido(1, 'a', 1, 'h')); // true
        System.out.println("Caminho (1a->1h): " + torre.caminho(1, 'a', 1, 'h')); // 1a1b1c1d1e1f1g1h

        // Teste Cavalo
        Cavalo cavalo = new Cavalo(Cor.BRANCA);
        System.out.println("\nTeste Cavalo:");
        System.out.println("Desenho: " + cavalo.desenho()); // Esperado: C
        System.out.println("Movimento válido (2b->4c): " + cavalo.movimentoValido(2, 'b', 4, 'c')); // true
        System.out.println("Caminho (2b->4c): " + cavalo.caminho(2, 'b', 4, 'c')); // 2b4c

        // Teste Peão
        Peao peao = new Peao(Cor.BRANCA);
        System.out.println("\nTeste Peão:");
        System.out.println("Desenho: " + peao.desenho()); // Esperado: P
        System.out.println("Movimento válido (2a->3a): " + peao.movimentoValido(2, 'a', 3, 'a')); // true
        System.out.println("Caminho (2a->3a): " + peao.caminho(2, 'a', 3, 'a')); // 2a3a

        // Teste Dama
        Dama dama = new Dama(Cor.PRETA);
        System.out.println("\nTeste Dama:");
        System.out.println("Desenho: " + dama.desenho()); // Esperado: d
        System.out.println("Movimento válido (4d->7g): " + dama.movimentoValido(4, 'd', 7, 'g')); // true
        System.out.println("Caminho (4d->7g): " + dama.caminho(4, 'd', 7, 'g')); // 4d5e6f7g

        // Teste Rei
        Rei rei = new Rei(Cor.BRANCA);
        System.out.println("\nTeste Rei:");
        System.out.println("Desenho: " + rei.desenho()); // Esperado: R
        System.out.println("Movimento válido (1e->2e): " + rei.movimentoValido(1, 'e', 2, 'e')); // true
        System.out.println("Caminho (1e->2e): " + rei.caminho(1, 'e', 2, 'e')); // 1e2e

        // Teste Casa
        Casa casa = new Casa();
        System.out.println("\nTeste Casa:");
        System.out.println("Está ocupada? " + casa.estaOcupada()); // false

        // Teste Jogador
        Jogador jogador = new Jogador("Alice", Cor.BRANCA);
        System.out.println("\nTeste Jogador:");
        System.out.println("Nome: " + jogador.getNome()); // Alice
        System.out.println("Cor: " + jogador.getCor()); // BRANCA

        // Teste Tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println("\nTeste Tabuleiro:");
        System.out.println(tabuleiro.desenho());

        // Teste Caminho
        Caminho caminho = new Caminho();
        caminho.adicionarCasa(new Casa());
        caminho.adicionarCasa(new Casa());
        System.out.println("\nTeste Caminho:");
        System.out.println("Está livre? " + caminho.estaLivre()); // true

        // Teste Jogada
        Jogada jogada = new Jogada(jogador, 2, 'c', 4, 'c', "2c3c4c");
        System.out.println("\nTeste Jogada:");
        System.out.println("Jogada válida? " + jogada.ehValida(tabuleiro)); // Depende do estado do tabuleiro
    }
}