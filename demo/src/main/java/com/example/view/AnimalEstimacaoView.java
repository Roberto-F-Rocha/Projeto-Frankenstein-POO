package com.example.view;

import java.util.Scanner;

public class AnimalEstimacaoView {
    public void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar novo animal de estimação");
        System.out.println("2. Visualizar informações dos animais de estimação");
        System.out.println("3. Editar informações de um animal de estimação");
        System.out.println("4. Listar todos os animais de estimação");
        System.out.println("5. Remover um animal de estimação");
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
