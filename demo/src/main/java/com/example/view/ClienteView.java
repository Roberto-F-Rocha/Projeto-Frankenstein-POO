package com.example.view;

import java.util.Scanner;

public class ClienteView {
    public void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar novo cliente");
        System.out.println("2. Visualizar informações do cliente");
        System.out.println("3. Editar informações do cliente");
        System.out.println("4. Listar todos os clientes");
        System.out.println("5. Remover cliente");
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
