package com.mycompany.xadrez2;

import java.io.IOException;

public class JogadaInvalidaException extends Exception {
    public JogadaInvalidaException(String mensagem) {
        super(mensagem);
    }
    
    public static void carregarJogo(String nomeBrancas, String nomePretas) throws IOException {
        if (nomeBrancas == null || nomePretas == null) {
            throw new IOException("Arquivo de jogo inválido: faltam nomes dos jogadores.");
        }
        
        
    }
}