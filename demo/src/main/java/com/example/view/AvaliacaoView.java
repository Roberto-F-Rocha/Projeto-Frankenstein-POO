package com.example.view;

import java.util.Scanner;

public class AvaliacaoView {
    public void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar nova avaliação");
        System.out.println("2. Visualizar informações da avaliação");
        System.out.println("3. Editar informações da avaliação");
        System.out.println("4. Listar todas as avaliações");
        System.out.println("5. Remover avaliação");
        System.out.println("6. Sair");
    }

    public int obterOpcao(Scanner scanner) {
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}

