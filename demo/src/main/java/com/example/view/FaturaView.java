package com.example.view;

import java.util.Scanner;

public class FaturaView {
    public void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Visualizar informações da fatura");
        System.out.println("2. Editar informações da fatura");
        System.out.println("3. Listar todas as faturas");
        System.out.println("4. Remover fatura");
        System.out.println("5. Sair");
    }

    public int obterOpcao(Scanner scanner) {
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}

