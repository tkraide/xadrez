package com.mycompany.xadrez2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private String nome;
    private Cor cor;
    private List<Peca> pecasCapturadas;
    private Scanner scanner;

    public Jogador(String nome, Cor cor) {
        this.nome = nome;
        this.cor = cor;
        this.pecasCapturadas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public String getNome() { return nome; }
    public Cor getCor() { return cor; }
    public List<Peca> getPecasCapturadas() { return pecasCapturadas; }
    public Scanner getScanner() { return scanner; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCor(Cor cor) { this.cor = cor; }
    public void setPecasCapturadas(List<Peca> pecasCapturadas) { 
        this.pecasCapturadas = pecasCapturadas; 
    }

    public String informaJogada() {
        while (true) {
            System.out.print(nome + ", informe sua jogada (ex: 2a3a) ou 'parar': ");
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("parar")) {
                return entrada;
            }
            if (entrada.matches("[1-8][a-h][1-8][a-h]")) {
                return entrada;
            }
            System.out.println("Formato inválido! Use: NúmeroLetraNúmeroLetra (ex: 2a3a)");
        }
    }

    public void adicionarPecaCapturada(Peca peca) {
        if (peca != null) {
            pecasCapturadas.add(peca);
            peca.capturar();
        }
    }

    public String pecasCapturadas() {
        StringBuilder sb = new StringBuilder();
        for (Peca peca : pecasCapturadas) {
            sb.append(peca.desenho()).append(" ");
        }
        return sb.toString().trim();
    }

    public void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
